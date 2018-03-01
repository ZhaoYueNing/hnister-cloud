package cn.zynworld.hnister.zuul.filter;

import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.zuul.manager.RoleResourceManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
@Component
public class TokenCheckFilter extends ZuulFilter {

    private final String JWT_HEADER_NAME = "token";
    private final Logger logger = LoggerFactory.getLogger(TokenCheckFilter.class);

    @Value("${token.filter}")
    private boolean IS_FILTER;

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
        if (IS_FILTER){
            logger.info("开启token过滤");
        }else{
            logger.info("未开启token过滤");
        }
        return IS_FILTER;
    }

    //超级管理员角色
    @Value("${root.role}")
    private String ROOT_ROLE;


    @Override
    //TODO 目前每次修改鉴权权限等信息 需要重启
    //后续版本修改后采用mq 自动更新缓存
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
        } else if (resourceStatus == 0){
            //白名单 放行
            return null;
        }

        //使用jwtBean解码装载jwt信息
        CodecUtils.JwtBean jwtBean = CodecUtils.JwtBean.getJwtBean(request.getHeader(JWT_HEADER_NAME));
        if (jwtBean != null){
            //jwt 用户角色信息
            List<String> roleIdList = (List<String>) jwtBean.getPlayload("roles");
            //指定id为1 的角色为超级管理员
            if (roleIdList != null && roleIdList.contains(ROOT_ROLE)){
                return null;
            }

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

    /**
     * 请求失败 调用
     * @param msg
     * @param ctx
     */
    private void createResponce(String msg,RequestContext ctx){
        // 过滤该请求，不对其进行路由
        ctx.setSendZuulResponse(false);
        // 返回无权限错误码
        ctx.setResponseStatusCode(50008);
        // 返回错误内容
        ctx.setResponseBody(msg);
        ctx.set("isSuccess", false);
    }
}
