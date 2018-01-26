package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.CodecUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultBean register(User user){
        if (user == null || user.getPassword() == null || user.getPassword().length() < 8){
            return ResultBean.fail();
        }

        //获取sale
        String sale = CodecUtils.getSale();
        //结合sale加密password
        user.setPassword(CodecUtils.getSalePassword(user.getPassword(),sale));


        int result = userMapper.insert(user);
        return ResultBean.create(result > 0);
    }
}
