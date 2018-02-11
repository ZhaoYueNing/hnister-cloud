package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.RoleUserRelaExample;
import cn.zynworld.hnister.common.domain.RoleUserRelaKey;
import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.mappers.RoleUserRelaMapper;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.common.vo.UserLoginVo;
import cn.zynworld.hnister.security.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/9.
 * 登录登出注册 账户相关api
 */
@RestController
public class AccountApi {

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
    @RequestMapping(path = "user",method = RequestMethod.POST,params = "from=register")
    public ResultBean register(@RequestBody User user){
        //判断用户是否符合注册条件
        if (!UserUtils.checkUser(user)) {
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
    //============前台账户===============

    //============后台账户===============
    //后台管理登录
    //TODO 前后台登录不能使用相同的接口 token格式区分
    @RequestMapping(path = "user/admin/login",method = RequestMethod.POST)
    public ResultBean login(@RequestBody UserLoginVo userLoginVo) {
        if (StringUtils.isBlank(userLoginVo.getUsername()) || StringUtils.isBlank(userLoginVo.getPassword())){
            return ResultBean.fail();
        }
        User user = userMapper.selectByPrimaryKey(userLoginVo.getUsername());
        //检测是否存在该用户 且 该用户类型为 1 管理员用户
        if (user == null || user.getType() != 1){
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
        //在jwt token中加入 后台管理员标识
        jwtBean.addPlayload("admin",true);

        return ResultBean.create(result).setMsg(jwtBean.toString());
    }


    //后台登出
    @RequestMapping(path = "user/admin/logout")
    public ResultBean logout(@RequestHeader("token") String token){
        //登出系统靠前端移除
        CodecUtils.JwtBean jwtBean = CodecUtils.JwtBean.getJwtBean(token);
        return ResultBean.create(jwtBean != null);
    }





    private List<Integer> roleUserRelaKeyListToRoleIdList(List<RoleUserRelaKey> roles){
        List<Integer> roleIdList = new ArrayList<>();
        for (RoleUserRelaKey key:
                roles) {
            roleIdList.add(key.getRoleId());
        }
        return roleIdList;
    }
}
