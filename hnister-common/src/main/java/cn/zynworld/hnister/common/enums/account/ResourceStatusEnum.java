package cn.zynworld.hnister.common.enums.account;

/**
 * Created by zhaoyuening on 2018/3/13.
 * 见readme.md文件 url规范
 */
public enum ResourceStatusEnum {
    PUBLIC(0,"开放资源无拦截","pb"),
    PROTECTED(1,"受保护资源","pt"),
    DEFAULT(2,"默认资源","df"),
    PRIVATE(3,"私有资源","pv"),
    OTHER(4,"其他资源","");

    private Integer code;
    private String desc;
    private String url;

    ResourceStatusEnum(Integer code, String desc, String url) {
        this.code = code;
        this.desc = desc;
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }
}
