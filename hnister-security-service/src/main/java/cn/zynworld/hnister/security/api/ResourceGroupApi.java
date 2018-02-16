package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.common.domain.ResourceGroup;
import cn.zynworld.hnister.common.dto.security.ResourceGroupCarryResourcesDTO;
import cn.zynworld.hnister.common.mappers.ResourceGroupMapper;
import cn.zynworld.hnister.common.mappers.ResourceMapper;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/1/29.
 */
@RestController
public class ResourceGroupApi {

    @Autowired
    private ResourceGroupMapper resourceGroupMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @GetMapping(path = "resourceGroups")
    public List<ResourceGroup> findAll(){
        List<ResourceGroup> resourceGroups = resourceGroupMapper.selectByExample(null);
        return resourceGroups;
    }

    //返回所有resourceGroup 并携带resource
    @GetMapping(path = "resourceGroups/@/for=carryResource")
    public List<ResourceGroupCarryResourcesDTO> findAllCarryResource() {
        //result
        List<ResourceGroupCarryResourcesDTO> resourceGroupCarryResourcesDTOList = Lists.newArrayList();
        //query all group
        List<ResourceGroup> resourceGroupList = resourceGroupMapper.selectByExample(null);

        ResourceGroupCarryResourcesDTO resourceGroupCarryResourcesDTO = null;
        List<Resource> resourceList = null;
        ResourceExample resourceExample = new ResourceExample();
        for (ResourceGroup group :
                resourceGroupList) {
            resourceGroupCarryResourcesDTO = new ResourceGroupCarryResourcesDTO();
            resourceExample.clear();
            //query resource for group
            resourceExample.createCriteria().andGroupIdEqualTo(group.getId());
            resourceList = resourceMapper.selectByExample(resourceExample);

            //packaging bean
            BeanUtils.copyProperties(group,resourceGroupCarryResourcesDTO);
            resourceGroupCarryResourcesDTO.setResourceList(resourceList);
            //add to result
            resourceGroupCarryResourcesDTOList.add(resourceGroupCarryResourcesDTO);
        }

        return resourceGroupCarryResourcesDTOList;
    }

    @GetMapping(path = "resourceGroup/{id}")
    public ResourceGroup findById(@PathVariable Integer id) {
        if (id == null) {
            return null;
        }
        return resourceGroupMapper.selectByPrimaryKey(id);
    }


    @PutMapping(path = "resourceGroup")
    public ResultBean updateResourceGroup(@RequestBody ResourceGroup resourceGroup) {
        if (resourceGroup == null || resourceGroup.getId() == null) {
            return ResultBean.fail("参数 或 id 不得为空");
        }
        int result = resourceGroupMapper.updateByPrimaryKey(resourceGroup);
        return ResultBean.create(result > 0);
    }

    @DeleteMapping(path = "resourceGroup/{id}")
    public ResultBean deleteById(@PathVariable Integer id) {

        if (id == null) {
            return ResultBean.fail("ID 不得为空");
        }
        int result = resourceGroupMapper.deleteByPrimaryKey(id);
        return ResultBean.create(result > 0);
    }

    @PostMapping(path = "resourceGroup")
    public ResultBean add(@RequestBody ResourceGroup resourceGroup) {
        int result = resourceGroupMapper.insert(resourceGroup);
        return ResultBean.create(result > 0);
    }


}
