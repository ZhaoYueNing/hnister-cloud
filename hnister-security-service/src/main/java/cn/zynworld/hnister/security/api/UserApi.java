package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.RoleUserRelaExample;
import cn.zynworld.hnister.common.domain.RoleUserRelaKey;
import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.domain.UserExample;
import cn.zynworld.hnister.common.dto.security.UserCarryRoleDTO;
import cn.zynworld.hnister.common.mappers.RoleUserRelaMapper;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.*;
import cn.zynworld.hnister.security.exception.InsertRoleUserKeyException;
import cn.zynworld.hnister.security.utils.UserUtils;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyuening on 2018/1/26.
 * 用户管理API
 */
@RestController
@RequestMapping(path = "rest")
public class UserApi {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserRelaMapper roleUserRelaMapper;

    @GetMapping(path = "df/user/admin/info")
    public User getUserInfo(){
        String username = AccountUtils.getUsername();
        if (username == null) {
            return null;
        }
        //进行查询
        User user = userMapper.selectByPrimaryKey(username);
        if (user != null){
            //敏感数据不暴露
            user.setSalt(null);
            user.setCreateTime(null);
        }
        return user;
    }

    /**
     *
     * @param keyWord 关键词 模糊匹配名字及用户名
     * @param role 用户的角色ID 不查询该参数 传 -1
     * @param type 用户的类型 普通用户0 管理员用户1 不查询 -1
     * @param status 用户状态 0：未验证 1：已经验证
     * @return 不包含密码盐值 携带role信息的用户列表
     */
    @GetMapping(path = "pt/users/@/for=page&condition")
    public PageBean<UserCarryRoleDTO> findAllCarryRols(
            //分页参数 pageSize <= 0 返回所有 不进行分页
            @RequestParam("pageCount") Integer pageCount, @RequestParam("pageSize") Integer pageSize,
            //查询条件
            @RequestParam String keyWord, @RequestParam Integer role, @RequestParam Short type, @RequestParam Short status){

        PageBean<UserCarryRoleDTO> pageBean = new PageBean<UserCarryRoleDTO>();


        keyWord = "%" + keyWord + "%";

        //查询条件对象
        UserExample userExample = new UserExample();
        RowBounds rowBounds = null;

        //配置分页
        pageBean.setPageCount(pageCount);
        pageBean.setPageSize(pageSize);
        if (pageSize >= 0){
            rowBounds = new RowBounds(pageBean.getFirstItemIndex(), pageSize);
        }

        //条件拼凑
        UserExample.Criteria usernameCriteria = userExample.or();
        UserExample.Criteria nameCriteria = userExample.or();

        usernameCriteria.andUsernameLike(keyWord);
        nameCriteria.andNameLike(keyWord);

        if (role > 0){
            //获取该角色的username list
            RoleUserRelaExample roleUserRelaExample = new RoleUserRelaExample();
            roleUserRelaExample.createCriteria().andRoleIdEqualTo(role);
            List<RoleUserRelaKey> roleUserRelaKeys = roleUserRelaMapper.selectByExample(roleUserRelaExample);
            List<String> usernames = roleUserRelaKeys.stream().map(roleUserRelaKey -> {
                return roleUserRelaKey.getUsername();
            }).collect(Collectors.toList());
            if (usernames == null || usernames.isEmpty()) {
                pageBean.setItems(Lists.newArrayList());
                return pageBean;
            }
            usernameCriteria.andUsernameIn(usernames);
            nameCriteria.andUsernameIn(usernames);
        }
        if (type >= 0){
            usernameCriteria.andTypeEqualTo(type);
            nameCriteria.andTypeEqualTo(type);
        }
        if (status >= 0){
            usernameCriteria.andStatusEqualTo(status);
            nameCriteria.andStatusEqualTo(status);
        }

        //获得结果
        List<User> users = userMapper.selectByExampleWithRowbounds(userExample,rowBounds).stream().map(user -> {
            //去除敏感数据
            user.setPassword(null);
            user.setSalt(null);
            return user;
        }).collect(Collectors.toList());

        List<UserCarryRoleDTO> userCarryRoleDTOList = Lists.newArrayList();
        UserCarryRoleDTO userCarryRoleDTO = null;
        RoleUserRelaExample roleUserRelaExample = null;
        for (User user :
             users) {
            userCarryRoleDTO = new UserCarryRoleDTO();
            BeanUtils.copyProperties(user, userCarryRoleDTO);
            //查询该user的role信息
            roleUserRelaExample = new RoleUserRelaExample();
            roleUserRelaExample.createCriteria().andUsernameEqualTo(user.getUsername());
            List<Integer> roleIdList = roleUserRelaMapper.selectByExample(roleUserRelaExample).stream().map(roleUserRelaKey -> {
                return roleUserRelaKey.getRoleId();
            }).collect(Collectors.toList());

            userCarryRoleDTO.setRoleIdList(roleIdList);
            userCarryRoleDTOList.add(userCarryRoleDTO);
        }

        //获取总数
        int total = userMapper.countByExample(userExample);

        //封装结果
        pageBean.setItems(userCarryRoleDTOList);
        pageBean.setTotal((long) total);


        return pageBean;
    }

    @GetMapping(path = "pt/user/{username}/@/for=admin")
    public UserCarryRoleDTO findByUsernameCarryRols(@PathVariable String username){
        UserCarryRoleDTO userCarryRoleDTO = new UserCarryRoleDTO();
        //query user info
        User user = userMapper.selectByPrimaryKey(username);
        if (user == null) {
            return null;
        }
        //wipe out password&sale info
        user.setPassword("");
        user.setSalt("");

        //query user`s role info
        RoleUserRelaExample roleUserRelaExample = new RoleUserRelaExample();
        roleUserRelaExample.createCriteria().andUsernameEqualTo(username);
        List<Integer> roleIdList = roleUserRelaMapper.selectByExample(roleUserRelaExample)
                .stream().map(roleUserRelaKey -> {
                    return roleUserRelaKey.getRoleId();
                }).collect(Collectors.toList());

        //packaging bean
        BeanUtils.copyProperties(user,userCarryRoleDTO);
        userCarryRoleDTO.setRoleIdList(roleIdList);

        return userCarryRoleDTO;
    }

    /**
     * 添加用户并设置用户的角色信息
     * @param userCarryRoleDTO
     * @return
     */
    @Transactional
    @PostMapping(path = "pt/user")
    public ResultBean addUser(@RequestBody UserCarryRoleDTO userCarryRoleDTO) throws InsertRoleUserKeyException {
        User user = new User();
        List<Integer> roleIdList = userCarryRoleDTO.getRoleIdList();

        //copy properties
        BeanUtils.copyProperties(userCarryRoleDTO,user);
        //检测用户的用户名密码等长度是否符合要求
        if (!UserUtils.checkUser(user)) {
            return ResultBean.fail("用户名或密码长度不符合要求");
        }

        //添加用户
        //加密
        //获取sale
        String sale = CodecUtils.getSale();
        //结合sale加密password
        user.setPassword(CodecUtils.getSalePassword(user.getPassword(),sale));
        user.setSalt(sale);

        int result = userMapper.insert(user);

        if (result <= 0) {
            //insert失败
            return ResultBean.fail("添加用户失败");
        }

        if (roleIdList == null || roleIdList.isEmpty()) {
            return ResultBean.success();
        }

        RoleUserRelaKey roleUserRelaKey = null;
        int roleInsertResult = 0;
        //为用户添加角色信息
        for (Integer roleId :
                roleIdList) {
            roleUserRelaKey = new RoleUserRelaKey();
            roleUserRelaKey.setRoleId(roleId);
            roleUserRelaKey.setUsername(user.getUsername());
            roleInsertResult = roleUserRelaMapper.insert(roleUserRelaKey);
            //添加角色联系失败
            if (roleInsertResult <= 0) {
                //抛异常回滚
                throw new InsertRoleUserKeyException();
            }
        }
        return ResultBean.success();

    }

    /**
     * 编辑用户及其角色信息
     * @param userCarryRoleDTO
     * @return
     */
    @Transactional
    @PutMapping(path = "pt/user/@/for=admin")
    public ResultBean update(@RequestBody UserCarryRoleDTO userCarryRoleDTO) throws InsertRoleUserKeyException {
        User user = new User();
        List<Integer> roleIdList = userCarryRoleDTO.getRoleIdList();

        //copy properties
        BeanUtils.copyProperties(userCarryRoleDTO,user);

        //添加用户
        //是否需要改密码
        if (!StringUtils.isEmpty(user.getPassword())) {
            //检测用户的用户名密码等长度是否符合要求
            if (!UserUtils.checkUser(user)) {
                return ResultBean.fail("用户名或密码长度不符合要求");
            }
            //加密
            //获取sale
            String sale = CodecUtils.getSale();
            //结合sale加密password
            user.setPassword(CodecUtils.getSalePassword(user.getPassword(),sale));
            user.setSalt(sale);
        }else {
            user.setSalt(null);
            user.setPassword(null);
        }


        int result = userMapper.updateByPrimaryKeySelective(user);

        if (result <= 0) {
            //insert失败
            return ResultBean.fail("编辑用户失败");
        }

        //解除与所有role的绑定
        RoleUserRelaExample roleUserRelaExample = new RoleUserRelaExample();
        roleUserRelaExample.createCriteria().andUsernameEqualTo(user.getUsername());
        roleUserRelaMapper.deleteByExample(roleUserRelaExample);


        if (roleIdList == null || roleIdList.isEmpty()) {
            return ResultBean.success();
        }

        //添加新的与role的关系
        RoleUserRelaKey roleUserRelaKey = null;
        int roleInsertResult = 0;
        //为用户添加角色信息
        for (Integer roleId :
                roleIdList) {
            roleUserRelaKey = new RoleUserRelaKey();
            roleUserRelaKey.setRoleId(roleId);
            roleUserRelaKey.setUsername(user.getUsername());
            roleInsertResult = roleUserRelaMapper.insert(roleUserRelaKey);
            //添加角色联系失败
            if (roleInsertResult <= 0) {
                //回滚
                throw new InsertRoleUserKeyException();
            }
        }
        return ResultBean.success();

    }

    @Transactional
    @DeleteMapping(path = "pt/user/{username}")
    public ResultBean remove(@PathVariable String username) {
        int result = userMapper.deleteByPrimaryKey(username);
        return ResultBean.create(result > 0);
    }


}
