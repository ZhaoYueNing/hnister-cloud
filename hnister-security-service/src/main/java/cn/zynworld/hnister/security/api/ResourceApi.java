package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.common.mappers.ResourceMapper;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResultBean add(@RequestBody Resource resource){
        int result = resourceMapper.insert(resource);
        return ResultBean.create(result > 0);
    }

    @Transactional
    @DeleteMapping(path = "resource/{id}")
    public ResultBean deleteById(@PathVariable("id") Integer id) {
        int result = resourceMapper.deleteByPrimaryKey(id);
        return ResultBean.create(result > 0);
    }

    @Transactional
    @PutMapping(path = "resource")
    public ResultBean update(@RequestBody Resource resource) {
        if (resource == null || resource.getId() == null) {
            return ResultBean.fail("参数异常");
        }

        int result = resourceMapper.updateByPrimaryKey(resource);
        return ResultBean.create(result > 0);
    }

    @GetMapping(path = "resources")
    public List<Resource> findAll(){
        List<Resource> resourceList = resourceMapper.selectByExample(null);
        return resourceList;
    }


    //分页 + 条件查询
    @GetMapping(path = "resources", params = {"page=true","condition=true"})
    public PageBean<Resource> findByPageAndCondition(
            //分页参数 pageSize <= 0 返回所有 不进行分页
            @PathParam("pageCount") Integer pageCount, @PathParam("pageSize") Integer pageSize,
            //查询条件 group <= 0 查询所有组       likeWord不为空匹配查询 name & url          method <g 0 查询所有
            @PathParam("group") Integer groupId,@PathParam("likeWrod") String likeWord,@PathParam( "method") Integer method
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





}