package cn.zynworld.hnister.common.domain;

public class NewsModule {
    private Integer id;

    private String name;

    private String remark;

    private Long number;

    public Integer getId() {
        return id;
    }

    public NewsModule setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public NewsModule setName(String name) {
        this.name = name;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public NewsModule setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Long getNumber() {
        return number;
    }

    public NewsModule setNumber(Long number) {
        this.number = number;
        return this;
    }
}