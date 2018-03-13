package cn.zynworld.hnister.common;

/**
 * Created by zhaoyuening on 2018/3/11.
 */
public enum ResourceTypeEnum {
    NONE(-1,"无效来源",""),
    PUBLIC(1,"开放访问","rest/public/"),
    PROTECTED(2,"受保护的","rest/protected/"),
    PRIVATE(3,"私有外部无法访问","rest/admin/"),
    ADMIN(4,"需要管理员权限且受保护的","rest/private/")
    ;


    private String desc;
    private Integer code;
    private String url;

    ResourceTypeEnum(Integer code,String desc,String url) {
        this.desc = desc;
        this.code = code;
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }
}
