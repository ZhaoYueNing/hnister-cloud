package cn.zynworld.hnister.zuul.filter;

import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.zuul.manager.RoleResourceManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
@Component
public class RequestPreFilter extends ZuulFilter {

    private final String JWT_HEADER_NAME = "jwt";
    private final Logger logger = LoggerFactory.getLogger(RequestPreFilter.class);

    @Autowired
    private RoleResourceManager roleResourceManager;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();

        //检测资源状态 是否为白名单 或 被禁用
        // 0 白名单 1 被监控权限 2 禁用
        Integer resourceStatus = roleResourceManager.checkStatusForResource(method, url);
        if (resourceStatus == null || resourceStatus == -1||resourceStatus == 2){
            createResponce("request fail!",ctx);
            return null;
        }

        //使用jwtBean解码装载jwt信息
        CodecUtils.JwtBean jwtBean = CodecUtils.JwtBean.getJwtBean(request.getHeader("jwt"));
        if (jwtBean != null){
            //jwt 用户角色信息
            List<String> roleIdList = (List<String>) jwtBean.getPlayload("role");
            if (roleIdList != null && roleResourceManager.checkAuthority(method, url, roleIdList)) {
                //该检验通过 该用户具备访问资源的权限
                return null;
            }
        }
        //游客身份
        //查看是否为白名单Resource
        createResponce("request fail",ctx);
        return null;
    }

    private void createResponce(String msg,RequestContext ctx){
        ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
        ctx.setResponseStatusCode(401);// 返回错误码
        ctx.setResponseBody(msg);// 返回错误内容
        ctx.set("isSuccess", false);
    }
}
