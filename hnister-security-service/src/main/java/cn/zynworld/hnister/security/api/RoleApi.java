package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.Role;
import cn.zynworld.hnister.common.mappers.RoleMapper;
import cn.zynworld.hnister.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.proc.arm.ProcARMThread;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/2.
 */
@RestController
public class RoleApi {

    @Autowired
    private RoleMapper roleMapper;

    @GetMapping(path = "roles")
    public List<Role> findAll(){
        List<Role> roles = roleMapper.selectByExample(null);
        return roles;
    }

    @GetMapping(path = "role/{id}")
    public Role findById(@PathVariable Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @PutMapping(path = "role")
    public ResultBean update(@RequestBody Role role) {
        int result = roleMapper.updateByPrimaryKey(role);
        return ResultBean.create(result > 0);
    }

    @DeleteMapping(path = "role/{id}")
    public ResultBean deleteById(@PathVariable Integer id){
        int result = roleMapper.deleteByPrimaryKey(id);
        return ResultBean.create(result > 0);
    }

    @PostMapping(path = "role")
    public ResultBean<Integer> add(@RequestBody Role role){
        int newId = roleMapper.insert(role);
        return ResultBean.create(newId > 0).setMsg(newId);
    }


}
