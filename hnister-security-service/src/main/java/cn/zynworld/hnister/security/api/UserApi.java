package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.RoleUserRelaExample;
import cn.zynworld.hnister.common.domain.RoleUserRelaKey;
import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.mappers.RoleUserRelaMapper;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.common.vo.UserLoginVo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/1/26.
 * 登录、注册等账户操作API
 */
@RestController
public class UserApi {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserRelaMapper roleUserRelaMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Transactional
    @RequestMapping(path = "user",method = RequestMethod.POST)
    public ResultBean register(@RequestBody User user){
        if (user == null || user.getPassword() == null || user.getPassword().length() < 8){
            return ResultBean.fail();
        }

        //获取sale
        String sale = CodecUtils.getSale();
        //结合sale加密password
        user.setPassword(CodecUtils.getSalePassword(user.getPassword(),sale));
        user.setSalt(sale);
        user.setStatus((short) 0);

        int result = userMapper.insert(user);
        return ResultBean.create(result > 0);
    }


    //后台管理登录
    //TODO 前后台登录不能使用相同的接口 token格式区分
    @RequestMapping(path = "user/login",method = RequestMethod.POST)
    public ResultBean login(@RequestBody UserLoginVo userLoginVo) {
        if (StringUtils.isBlank(userLoginVo.getUsername()) || StringUtils.isBlank(userLoginVo.getPassword())){
            return ResultBean.fail();
        }
        User user = userMapper.selectByPrimaryKey(userLoginVo.getUsername());
        if (user == null){
            return ResultBean.fail().setMsg("用户名或密码错误!");
        }
        String encodedPassword = user.getPassword();
        String sale = user.getSalt();

        //检验
        boolean result = CodecUtils.checkUser(userLoginVo.getPassword(),sale,encodedPassword);
        //创建token
        //获取用户角色
        RoleUserRelaExample roleUserRelaExample = new RoleUserRelaExample();
        roleUserRelaExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<RoleUserRelaKey> roles = roleUserRelaMapper.selectByExample(roleUserRelaExample);
        CodecUtils.JwtBean jwtBean = new CodecUtils.JwtBean();
        jwtBean.addHead("typ","JWT");
        jwtBean.addHead("alg","HA256");
        //放入角色列表
        jwtBean.addPlayload("roles",roleUserRelaKeyListToRoleIdList(roles));
        //用户名
        jwtBean.addPlayload("username",userLoginVo.getUsername());

        return ResultBean.create(result).setMsg(jwtBean.toString());
    }

    @RequestMapping(path = "user/logout")
    public ResultBean logout(@RequestHeader("token") String token){
        //登出系统靠前端移除
        CodecUtils.JwtBean jwtBean = CodecUtils.JwtBean.getJwtBean(token);
        return ResultBean.create(jwtBean != null);
    }

    @RequestMapping(method = RequestMethod.GET,path = "user/info")
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


    private List<Integer> roleUserRelaKeyListToRoleIdList(List<RoleUserRelaKey> roles){
        List<Integer> roleIdList = new ArrayList<>();
        for (RoleUserRelaKey key:
             roles) {
            roleIdList.add(key.getRoleId());
        }
        return roleIdList;
    }


    //查询用户信息 不得暴露敏感数据
    @GetMapping(path = "users",params = "query=")
    public List<User> findAllFor(String keyWord,List<Integer> roles){
        return null;
    }


}
