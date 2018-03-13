package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.common.domain.RoleResourceRelaExample;
import cn.zynworld.hnister.common.mappers.ResourceMapper;
import cn.zynworld.hnister.common.mappers.RoleResourceRelaMapper;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyuening on 2018/1/28.
 */
@RestController
@RequestMapping(path = "rest")
public class ResourceApi {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceRelaMapper roleResourceRelaMapper;

    @Transactional
    @PostMapping(path = "pt/resource")
    public ResultBean add(@RequestBody Resource resource){
        int result = resourceMapper.insert(resource);
        return ResultBean.create(result > 0);
    }

    @Transactional
    @DeleteMapping(path = "pt/resource/{id}")
    public ResultBean deleteById(@PathVariable("id") Integer id) {
        int result = resourceMapper.deleteByPrimaryKey(id);
        return ResultBean.create(result > 0);
    }

    @Transactional
    @PutMapping(path = "pt/resource")
    public ResultBean update(@RequestBody Resource resource) {
        if (resource == null || resource.getId() == null) {
            return ResultBean.fail("参数异常");
        }

        int result = resourceMapper.updateByPrimaryKey(resource);
        return ResultBean.create(result > 0);
    }

    @GetMapping(path = "pt/resources")
    public List<Resource> findAll(){
        List<Resource> resourceList = resourceMapper.selectByExample(null);
        return resourceList;
    }

    //通过resource id获取
    @GetMapping(path = "pt/resource/{resourceId}")
    public Resource findById(@PathVariable Integer resourceId) {
        Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
        return resource;
    }

    //分页 + 条件查询
    @GetMapping(path = "pt/resources/@/for=page&condition")
    public PageBean<Resource> findByPageAndCondition(
            //分页参数 pageSize <= 0 返回所有 不进行分页
            @PathParam("pageCount") Integer pageCount, @PathParam("pageSize") Integer pageSize,
            //查询条件 group < 0 查询所有组 group = 0 查询null      likeWord不为空匹配查询 name & url          method <g 0 查询所有
            @PathParam("groupId") Integer groupId,@PathParam("likeWrod") String likeWord,@PathParam( "method") Integer method
    ) {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setPageCount(pageCount);

        ResourceExample example = new ResourceExample();
        //配置基于URL 排序
        example.setOrderByClause("'url' ASC, 'id' ASC");

        ResourceExample.Criteria nameCriteria = example.or();
        ResourceExample.Criteria urlCriteria = example.or();
        RowBounds rowBounds = null;
        //条件的拼凑
        if (groupId != null && groupId > 0) {
            nameCriteria.andGroupIdEqualTo(groupId);
            urlCriteria.andGroupIdEqualTo(groupId);
        }
        if (groupId != null && groupId == 0) {
            //查询group 为空
            nameCriteria.andGroupIdIsNull();
            urlCriteria.andGroupIdIsNull();
        }
        if (method != null && method >= 0) {
            nameCriteria.andMethodEqualTo(method);
            urlCriteria.andMethodEqualTo(method);
        }

        if (!StringUtils.isEmpty(likeWord)) {
            likeWord = "%" + likeWord + "%";
            nameCriteria.andNameLike(likeWord);
            urlCriteria.andUrlLike(likeWord);
        }
        //分页
        if (pageSize != null && pageSize >= 0) {
            int offset = pageBean.getFirstItemIndex();
            rowBounds = new RowBounds(offset, pageSize);
        } else {
            rowBounds = new RowBounds();
        }

        List<Resource> resources = resourceMapper.selectByExampleWithRowbounds(example, rowBounds);
        //获取总数
        int total = resourceMapper.countByExample(example);

        pageBean.setItems(resources);
        pageBean.setTotal((long) total);

        return pageBean;
    }

    //获取该角色所拥有的资源ID列表
    @GetMapping(path = "pt/resources/roleId/{roleId}")
    public List<Integer> findByRoleId(@PathVariable("roleId") Integer roleId){
        RoleResourceRelaExample roleResourceRelaExample = new RoleResourceRelaExample();
        roleResourceRelaExample.createCriteria().andRoleIdEqualTo(roleId);

        List<Integer> resourceIdList = roleResourceRelaMapper.selectByExample(roleResourceRelaExample).stream()
                .map(roleResourceRelaKey -> {
                    return roleResourceRelaKey.getResourceId();
                }).collect(Collectors.toList());
        return resourceIdList;
    }

}
