package cn.zynworld.hnister.security.rest;

import cn.zynworld.hnister.common.domain.Role;
import cn.zynworld.hnister.common.mappers.RoleMapper;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/2.
 */
@RestController
@RequestMapping(path = "rest")
public class RoleRest {

    @Autowired
    private RoleMapper roleMapper;

    @GetMapping(path = "pt/roles")
    public List<Role> findAll(){
        List<Role> roles = roleMapper.selectByExample(null);
        return roles;
    }

    @GetMapping(path = "pt/role/{id}")
    public Role findById(@PathVariable Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @PutMapping(path = "pt/role")
    public ResultBean update(@RequestBody Role role) {
        int result = roleMapper.updateByPrimaryKey(role);
        return ResultBean.create(result > 0);
    }

    @DeleteMapping(path = "pt/role/{id}")
    public ResultBean deleteById(@PathVariable Integer id){
        //固定ID 1 为超级管理员 不得删除
        if (id == 1){
            return ResultBean.fail("超级管理员角色不可以删除");
        }
        int result = roleMapper.deleteByPrimaryKey(id);
        return ResultBean.create(result > 0);
    }

    @PostMapping(path = "pt/role")
    public ResultBean<Integer> add(@RequestBody Role role){
        int newId = roleMapper.insert(role);
        return ResultBean.create(newId > 0).setMsg(newId);
    }


}
