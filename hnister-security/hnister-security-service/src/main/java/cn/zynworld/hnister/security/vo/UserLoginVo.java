package cn.zynworld.hnister.security.vo;

/**
 * Created by zhaoyuening on 2018/1/26.
 */
public class UserLoginVo {

    private String username;
    private String password;

    public UserLoginVo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginVo() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginVo setPassword(String password) {
        this.password = password;
        return this;
    }
}
