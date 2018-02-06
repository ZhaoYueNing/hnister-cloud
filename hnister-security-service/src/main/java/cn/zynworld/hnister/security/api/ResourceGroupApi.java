package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.ResourceGroup;
import cn.zynworld.hnister.common.mappers.ResourceGroupMapper;
import cn.zynworld.hnister.common.utils.ResultBean;
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

    @GetMapping(path = "resourceGroups")
    public List<ResourceGroup> findAll(){
        List<ResourceGroup> resourceGroups = resourceGroupMapper.selectByExample(null);
        return resourceGroups;
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
