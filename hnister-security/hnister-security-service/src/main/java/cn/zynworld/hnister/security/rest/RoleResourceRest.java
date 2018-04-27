package cn.zynworld.hnister.security.rest;

import cn.zynworld.hnister.security.domain.RoleResourceRelaExample;
import cn.zynworld.hnister.security.domain.RoleResourceRelaKey;
import cn.zynworld.hnister.security.dto.RoleAllocateResourcesDTO;
import cn.zynworld.hnister.security.mappers.RoleResourceRelaMapper;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/16.
 */
@RestController
@RequestMapping(path = "rest")
public class RoleResourceRest {

    @Autowired
    private RoleResourceRelaMapper roleResourceRelaMapper;

    //为角色分配资源
    @Transactional
    @PostMapping(path = "pt/roleAllocateResources/@/for=allocateResources")
    public ResultBean roleAllocateResources(@RequestBody RoleAllocateResourcesDTO roleAllocateResourcesDTO){
        if (StringUtils.isEmpty(roleAllocateResourcesDTO.getRoleId()) || roleAllocateResourcesDTO.getResourceIdList() == null) {
            return ResultBean.fail("参数异常");
        }
        Integer roleId = roleAllocateResourcesDTO.getRoleId();
        List<Integer> resourceIdList = roleAllocateResourcesDTO.getResourceIdList();

        RoleResourceRelaExample roleResourceRelaExample = new RoleResourceRelaExample();
        roleResourceRelaExample.createCriteria().andRoleIdEqualTo(roleId);
        //移除该用户所有关系
        roleResourceRelaMapper.deleteByExample(roleResourceRelaExample);

        RoleResourceRelaKey roleResourceRelaKey = null;
        //分配新的资源
        for (Integer resourceId : resourceIdList) {
            roleResourceRelaKey = new RoleResourceRelaKey();
            roleResourceRelaKey.setResourceId(resourceId);
            roleResourceRelaKey.setRoleId(roleId);

            roleResourceRelaMapper.insert(roleResourceRelaKey);
        }

        return ResultBean.success();
    }



}
