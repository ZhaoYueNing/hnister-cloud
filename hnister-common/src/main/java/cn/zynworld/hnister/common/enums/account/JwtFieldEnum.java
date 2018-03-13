package cn.zynworld.hnister.common.enums.account;

/**
 * Created by zhaoyuening on 2018/3/11.
 * 保存在jwt中存储域字段
 */
public enum JwtFieldEnum {
    ROLES("roles","保存角色列表"),
    USERNAME("username","保存用户名"),
    ADMIN("admin","标识是否为管理员用户"),
    IP("ip","用户登录的ip地址"),
    ;

    private String field;
    private String desc;


    JwtFieldEnum(String field, String desc) {
        this.field = field;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getField() {
        return field;
    }
}
