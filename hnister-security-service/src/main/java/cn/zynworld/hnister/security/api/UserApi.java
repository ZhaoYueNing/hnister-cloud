package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.RoleUserRelaExample;
import cn.zynworld.hnister.common.domain.RoleUserRelaKey;
import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.domain.UserExample;
import cn.zynworld.hnister.common.mappers.RoleUserRelaMapper;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.common.vo.UserLoginVo;
import com.google.common.collect.Lists;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by zhaoyuening on 2018/1/26.
 * 用户管理API
 */
@RestController
public class UserApi {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserRelaMapper roleUserRelaMapper;



    @RequestMapping(method = RequestMethod.GET,path = "user/admin/info")
    public User getUserInfo(@RequestParam("token") String jwt){
        CodecUtils.JwtBean jwtBean = CodecUtils.JwtBean.getJwtBean(jwt);
        if (jwtBean == null || jwtBean.getPlayload("username")==null){
            return null;
        }
        String username = (String) jwtBean.getPlayload("username");
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
     * @return 不包含密码盐值的用户列表
     */
    @GetMapping(path = "users",params = "query=user-manager")
    public List<User> findAllFor(@RequestParam String keyWord,@RequestParam Integer role,@RequestParam Short type,@RequestParam Short status){
        keyWord = "%" + keyWord + "%";
        //用户查询对象
        UserExample userExample = new UserExample();
        UserExample.Criteria usernameCriteria = userExample.or();
        UserExample.Criteria nameCriteria = userExample.or();

        usernameCriteria.andUsernameLike(keyWord);
        nameCriteria.andUsernameLike(keyWord);

        if (role > 0){
            //获取该角色的username list
            RoleUserRelaExample roleUserRelaExample = new RoleUserRelaExample();
            roleUserRelaExample.createCriteria().andRoleIdEqualTo(role);
            List<RoleUserRelaKey> roleUserRelaKeys = roleUserRelaMapper.selectByExample(roleUserRelaExample);
            List<String> usernames = roleUserRelaKeys.stream().map(roleUserRelaKey -> {
                return roleUserRelaKey.getUsername();
            }).collect(Collectors.toList());
            if (usernames == null || usernames.isEmpty()) {
                return Lists.newArrayList();
            }
            usernameCriteria.andUsernameIn(usernames);
            nameCriteria.andUsernameIn(usernames);
        }
        if (type > 0){
            usernameCriteria.andTypeEqualTo(type);
            nameCriteria.andTypeEqualTo(type);
        }
        if (status > 0){
            usernameCriteria.andStatusEqualTo(status);
            nameCriteria.andStatusEqualTo(status);
        }

        //获得结果
        List<User> users = userMapper.selectByExample(userExample).stream().map(user -> {
            //去除敏感数据
            user.setPassword(null);
            user.setSalt(null);
            return user;
        }).collect(Collectors.toList());

        return users;
    }


}
