package cn.zynworld.hnister.common.enums.account;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/3/11.
 */
public enum RequestHeaderKeyEnum {
    ACCOUNT_ROLES("account_roles","存放当前用户的角色列表", List.class),
    ACCOUNT_USERNAME("account_username","存放当前用户名",String.class),
    ACCOUNT_ADMIN("account_admin","当前用户是否为管理员用户", Boolean.class)
    ;


    private String key;
    private String desc;
    //存储的类型
    private Class zlass;


    RequestHeaderKeyEnum(String key, String desc, Class zlass) {
        this.key = key;
        this.desc = desc;
        this.zlass = zlass;
    }

    public String getKey() {
        return key;
    }
    public String getDesc() {
        return desc;
    }

    public Class getZlass() {
        return this.zlass;
    }
}
