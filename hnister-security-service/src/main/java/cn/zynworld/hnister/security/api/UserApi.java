package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.common.vo.UserLoginVo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhaoyuening on 2018/1/26.
 * 登录、注册等账户操作API
 */
@RestController
public class UserApi {

    @Autowired
    private UserMapper userMapper;

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

    @RequestMapping(path = "user",method = RequestMethod.POST,params = "login=true")
    public ResultBean login(@RequestBody UserLoginVo userLoginVo) {
        if (StringUtils.isBlank(userLoginVo.getUsername()) || StringUtils.isBlank(userLoginVo.getPassword())){
            return ResultBean.fail();
        }
        User user = userMapper.selectByPrimaryKey(userLoginVo.getUsername());
        String encodedPassword = user.getPassword();
        String sale = user.getSalt();

        //检验
        boolean result = CodecUtils.checkUser(userLoginVo.getPassword(),sale,encodedPassword);
        return ResultBean.create(result);
    }



}
