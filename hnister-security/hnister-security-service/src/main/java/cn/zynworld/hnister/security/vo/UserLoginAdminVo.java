package cn.zynworld.hnister.security.vo;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
public class UserLoginAdminVo {

    private String username;
    private String password;

    public UserLoginAdminVo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginAdminVo() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginAdminVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginAdminVo setPassword(String password) {
        this.password = password;
        return this;
    }
}
