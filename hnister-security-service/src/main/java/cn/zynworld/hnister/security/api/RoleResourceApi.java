package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.common.domain.RoleResourceRelaExample;
import cn.zynworld.hnister.common.domain.RoleResourceRelaKey;
import cn.zynworld.hnister.common.dto.security.RoleAllocateResourcesDTO;
import cn.zynworld.hnister.common.mappers.RoleResourceRelaMapper;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyuening on 2018/2/16.
 */
@RestController
public class RoleResourceApi {

    @Autowired
    private RoleResourceRelaMapper roleResourceRelaMapper;

    //为角色分配资源
    @Transactional
    @PostMapping(path = "roleAllocateResources/@/for=allocateResources")
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
