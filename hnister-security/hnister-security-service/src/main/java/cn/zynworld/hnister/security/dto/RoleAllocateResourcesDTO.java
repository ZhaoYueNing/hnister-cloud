package cn.zynworld.hnister.security.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/16.
 * 角色分配资源DTO
 */
public class RoleAllocateResourcesDTO implements Serializable{

    private Integer roleId;
    private List<Integer> resourceIdList;



    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public RoleAllocateResourcesDTO setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public RoleAllocateResourcesDTO setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
}
