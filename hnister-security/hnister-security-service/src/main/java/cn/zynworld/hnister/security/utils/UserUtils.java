package cn.zynworld.hnister.security.utils;

import cn.zynworld.hnister.security.domain.User;

/**
 * Created by zhaoyuening on 2018/2/11.
 */
public class UserUtils {

    /**
     * 检测用户注册或添加时 用户名或密码等是否满足要求
     * @param user
     * @return
     */
    public static boolean checkUser(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().length() < 8
                || user.getPassword() == null || user.getPassword().length() < 8) {
            return false;
        }
        return true;
    }
}
