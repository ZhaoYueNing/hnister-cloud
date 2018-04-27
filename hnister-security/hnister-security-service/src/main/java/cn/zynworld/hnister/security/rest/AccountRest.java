package cn.zynworld.hnister.security.rest;

import cn.zynworld.hnister.security.domain.RoleUserRelaExample;
import cn.zynworld.hnister.security.domain.RoleUserRelaKey;
import cn.zynworld.hnister.security.domain.User;
import cn.zynworld.hnister.common.enums.account.JwtFieldEnum;
import cn.zynworld.hnister.security.mappers.RoleUserRelaMapper;
import cn.zynworld.hnister.security.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.AccountUtils;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.JwtBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.security.convertor.UserConvertor;
import cn.zynworld.hnister.security.exception.CreateUserException;
import cn.zynworld.hnister.security.service.UserService;
import cn.zynworld.hnister.security.vo.UserLoginAdminVo;
import cn.zynworld.hnister.security.vo.UserLoginVo;
import cn.zynworld.hnister.security.utils.UserUtils;
import cn.zynworld.hnister.security.vo.UserRegisterVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/9.
 * 登录登出注册 账户相关api
 */
@RestController
@RequestMapping(path = "rest")
public class AccountRest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserRelaMapper roleUserRelaMapper;
    @Autowired
    private UserService userService;

    //token 过期时间
    @Value("${user.token.limit}")
    private Long LIMT_TIME;



    /**
     * 后台添加用户
     * @param user
     * @return
     */
    @Transactional
    @PostMapping(path = "pt/user/@/for=register" )
    public ResultBean register(@RequestBody User user){
        //判断用户是否符合注册条件
        if (!UserUtils.checkUser(user)) {
            return ResultBean.fail();
        }
        //获取salt
        String salt = CodecUtils.getSalt();
        //结合salt加密password
        user.setPassword(CodecUtils.getSaltPassword(user.getPassword(),salt));
        user.setSalt(salt);
        user.setStatus((short) 0);

        int result = userMapper.insert(user);
        return ResultBean.create(result > 0);
    }

    //============前台账户===============
    @PostMapping(path = "pb/user/login")
    public ResultBean login(@RequestBody UserLoginVo userLoginVo) {
        if (StringUtils.isBlank(userLoginVo.getUsername()) || StringUtils.isBlank(userLoginVo.getPassword())){
            return ResultBean.fail();
        }
        User user = userMapper.selectByPrimaryKey(userLoginVo.getUsername());
        //检测是否存在该用户
        if (user == null ){
            return ResultBean.fail().setMsg("用户名或密码错误!");
        }
        String encodedPassword = user.getPassword();
        String salt = user.getSalt();

        //检验
        boolean result = CodecUtils.checkUser(userLoginVo.getPassword(),salt,encodedPassword);
        if (!result) {
            return ResultBean.fail("用户名或密码不正确，登录失败！");
        }

        //创建token
        //获取用户角色
        JwtBean jwtBean = new JwtBean();
        jwtBean.addHead("typ","JWT");
        jwtBean.addHead("alg","HA256");
        //用户名
        jwtBean.addPlayload(JwtFieldEnum.USERNAME.getField(),userLoginVo.getUsername());
        //在jwt token中加入 后台管理员标识
        jwtBean.addPlayload(JwtFieldEnum.ADMIN.getField(),false);
        //存入用户访问ip
        jwtBean.addPlayload(JwtFieldEnum.IP.getField(),AccountUtils.getIpAddressFromRequest());
        //添加过期时间
        jwtBean.addPlayload(JwtFieldEnum.LIMIT.getField(), System.currentTimeMillis() + LIMT_TIME);
        return ResultBean.create(result).setMsg(jwtBean.toString());
    }

    //前台用户注册接口
    @PostMapping(path = "pb/user")
    public ResultBean register(@RequestBody UserRegisterVO registerVO) {
        User user = UserConvertor.registerVO2DO(registerVO);
        user.setStatus((short) 0);
        user.setType((short) 0);
        try {
            boolean result = userService.add(user);
            return ResultBean.create(result);
        } catch (CreateUserException e) {
            return ResultBean.fail(e.getMessage());
        }
    }

    @GetMapping(path = "pt/user/logout")
    public ResultBean logout() {
        //登出操作 暂无操作 后可以加入记录用户登陆登出情况
        return ResultBean.success();
    }


    //============后台账户===============
    //后台管理登录
    //TODO 前后台登录不能使用相同的接口 token格式区分
    @PostMapping(path = "pb/user/admin/login")
    public ResultBean loginAdmin(@RequestBody UserLoginAdminVo userLoginAdminVo) {
        if (StringUtils.isBlank(userLoginAdminVo.getUsername()) || StringUtils.isBlank(userLoginAdminVo.getPassword())){
            return ResultBean.fail();
        }
        User user = userMapper.selectByPrimaryKey(userLoginAdminVo.getUsername());
        //检测是否存在该用户 且 该用户类型为 1 管理员用户
        if (user == null || user.getType() != 1){
            return ResultBean.fail().setMsg("用户名或密码错误!");
        }
        String encodedPassword = user.getPassword();
        String salt = user.getSalt();

        //检验
        boolean result = CodecUtils.checkUser(userLoginAdminVo.getPassword(),salt,encodedPassword);
        if (!result) {
            return ResultBean.fail("用户名或密码不正确，登录失败！");
        }
        //创建token
        //获取用户角色
        RoleUserRelaExample roleUserRelaExample = new RoleUserRelaExample();
        roleUserRelaExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<RoleUserRelaKey> roles = roleUserRelaMapper.selectByExample(roleUserRelaExample);
        JwtBean jwtBean = new JwtBean();
        jwtBean.addHead("typ","JWT");
        jwtBean.addHead("alg","HA256");
        //放入角色列表
        jwtBean.addPlayload(JwtFieldEnum.ROLES.getField(),roleUserRelaKeyListToRoleIdList(roles));
        //用户名
        jwtBean.addPlayload(JwtFieldEnum.USERNAME.getField(),userLoginAdminVo.getUsername());
        //在jwt token中加入 后台管理员标识
        jwtBean.addPlayload(JwtFieldEnum.ADMIN.getField(),true);
        //存入用户访问ip
        jwtBean.addPlayload(JwtFieldEnum.IP.getField(),AccountUtils.getIpAddressFromRequest());
        //添加过期时间
        jwtBean.addPlayload(JwtFieldEnum.LIMIT.getField(), System.currentTimeMillis() + LIMT_TIME);
        return ResultBean.create(result).setMsg(jwtBean.toString());
    }


    //后台登出
    @PostMapping(path = "pt/user/admin/logout")
    public ResultBean adminLogout(@RequestHeader("token") String token){
        //登出系统靠前端移除
        JwtBean jwtBean = JwtBean.getJwtBean(token);
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
