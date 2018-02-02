package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.mappers.ResourceMapper;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/1/28.
 */
@RestController
public class ResourceApi {

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional
    @RequestMapping(path = "resource",method = RequestMethod.POST)
    public ResultBean add(Resource resource){
        int result = resourceMapper.insert(resource);
        return ResultBean.create(result > 0);
    }


    @GetMapping(path = "resources")
    public List<Resource> findAll(){
        List<Resource> resourceList = resourceMapper.selectByExample(null);
        return resourceList;
    }




}
