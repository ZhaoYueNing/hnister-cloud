package cn.zynworld.hnister.zuul.filter;

import cn.zynworld.hnister.common.enums.account.JwtFieldEnum;
import cn.zynworld.hnister.common.enums.account.RequestHeaderKeyEnum;
import cn.zynworld.hnister.common.enums.account.ResourceStatusEnum;
import cn.zynworld.hnister.common.utils.AccountUtils;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.JwtBean;
import cn.zynworld.hnister.zuul.manager.RoleResourceManager;
import com.google.gson.Gson;
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
    private final static Gson GSON = new Gson();

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
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        if (IS_FILTER){
            logger.info("开启token过滤");
        }else{
            logger.info("未开启token过滤");
            //TODO 解决在未开启鉴权拦截时 后台无法获取用户信息
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            //使用jwtBean解码装载jwt信息
            JwtBean jwtBean = JwtBean.getJwtBean(request.getHeader(JWT_HEADER_NAME));
            if (jwtBean != null) {
                addRequestHeader(jwtBean,ctx);
            }
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
        JwtBean jwtBean = null;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();

        //通过url规范 检测url
        Integer urlType = roleResourceManager.checkUrlType(url);
        if (urlType.equals(ResourceStatusEnum.PUBLIC.getCode())) {
            return null;
        }
        if (urlType.equals(ResourceStatusEnum.OTHER.getCode()) || urlType.equals(ResourceStatusEnum.PRIVATE)) {
            createResponce("request fail",ctx);
            return null;
        }
        if (urlType.equals(ResourceStatusEnum.DEFAULT.getCode())) {
            //使用jwtBean解码装载jwt信息
            jwtBean = JwtBean.getJwtBean(request.getHeader(JWT_HEADER_NAME));
            if (jwtBean == null) {
                createResponce("request fail",ctx);
                return null;
            }
            //将信息存储到request
            addRequestHeader(jwtBean,ctx);
            return null;
        }
        if (urlType.equals(ResourceStatusEnum.PROTECTED.getCode())) {
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
            jwtBean = JwtBean.getJwtBean(request.getHeader(JWT_HEADER_NAME));
            //将信息存储到request
            addRequestHeader(jwtBean,ctx);
            try {
                //验证ip地址是否一致
                Object ipAddress = jwtBean.getPlayload(JwtFieldEnum.IP.getField());
                if (!ipAddress.equals(AccountUtils.getIpAddressFromRequest())) {
                    //IP不匹配
                    createResponce("request fail",ctx);
                    return null;
                }

                //jwt 用户角色信息
                List<String> roleIdList = (List<String>) jwtBean.getPlayload(JwtFieldEnum.ROLES.getField());
                //指定id为1 的角色为超级管理员
                if (roleIdList != null && roleIdList.contains(ROOT_ROLE)){
                    return null;
                }

                if (roleIdList != null && roleResourceManager.checkAuthority(method, url, roleIdList)) {
                    //该检验通过 该用户具备访问资源的权限
                    return null;
                }
            } catch (Exception e) {
                createResponce("request fail",ctx);
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

    /**
     * 从jwtBean获取相应信息，并加装到request内
     * @param jwtBean
     * @param ctx
     */
    private void addRequestHeader(JwtBean jwtBean, RequestContext ctx) {
        if (jwtBean == null) {
            return;
        }
        //roles
        Object roles = jwtBean.getPlayload(JwtFieldEnum.ROLES.getField());
        Object username = jwtBean.getPlayload(JwtFieldEnum.USERNAME.getField());
        Object admin = jwtBean.getPlayload(JwtFieldEnum.ADMIN.getField());
        //save info to request
        ctx.addZuulRequestHeader(RequestHeaderKeyEnum.ACCOUNT_ROLES.getKey(), GSON.toJson(roles));
        ctx.addZuulRequestHeader(RequestHeaderKeyEnum.ACCOUNT_USERNAME.getKey(), GSON.toJson(username));
        ctx.addZuulRequestHeader(RequestHeaderKeyEnum.ACCOUNT_ADMIN.getKey(), GSON.toJson(admin));
    }
}
