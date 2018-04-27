package cn.zynworld.hnister.security.dto;

import cn.zynworld.hnister.security.domain.Resource;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/16.
 * 携带资源列表的
 */
public class ResourceGroupCarryResourcesDTO {
    //resource group properties
    private Integer id;
    private String name;
    private String remark;

    //resources info
    private List<Resource> resourceList;

    public Integer getId() {
        return id;
    }

    public ResourceGroupCarryResourcesDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceGroupCarryResourcesDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ResourceGroupCarryResourcesDTO setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public ResourceGroupCarryResourcesDTO setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
        return this;
    }
}
