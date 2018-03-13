package cn.zynworld.hnister.common.utils;

import cn.zynworld.hnister.common.enums.account.RequestHeaderKeyEnum;
import com.google.gson.Gson;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/3/11.
 * 接入鉴权账户工具,可以直接在控制器内调用
 */
public class AccountUtils {

    private static final Gson GSON = new Gson();

    /**
     * 从当前请求内获取到用户的角色列表
     * @return
     */
    public static List<Integer> getUserRoles() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String rolesJson = request.getHeader(RequestHeaderKeyEnum.ACCOUNT_ROLES.getKey());
            List roles = GSON.fromJson(rolesJson, List.class);
            return roles;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUsername() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String usernameJson = request.getHeader(RequestHeaderKeyEnum.ACCOUNT_USERNAME.getKey());
            return GSON.fromJson(usernameJson,String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean isAdmin() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String adminJson = request.getHeader(RequestHeaderKeyEnum.ACCOUNT_ADMIN.getKey());
            Boolean isAdmin = GSON.fromJson(adminJson, Boolean.class);
            return isAdmin;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 从request获取用户的真实IP地址
     * @return
     */
    public static String getIpAddressFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
