package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.dto.security.UserCarryRoleDTO;
import cn.zynworld.hnister.common.mappers.UserMapper;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.common.utils.ResultBean;
import cn.zynworld.hnister.security.exception.InsertRoleUserKeyException;
import com.google.common.collect.Lists;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApiTest {

    @Autowired
    private UserApi userApi;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQueryUsersInfo() {
        PageBean<UserCarryRoleDTO> userCarryRoleDTOPageBean = userApi.findAllCarryRols(1, 10, "", -1, (short) -1, (short) -1);
        System.out.println(userCarryRoleDTOPageBean);
    }


    @Test
    public void addUser() throws InsertRoleUserKeyException {
        UserCarryRoleDTO userCarryRoleDTO = new UserCarryRoleDTO();
        userCarryRoleDTO.setUsername("testuser2");
        userCarryRoleDTO.setGender(1);
        userCarryRoleDTO.setCity("sz");
        userCarryRoleDTO.setPassword("123456342423423342");
        userCarryRoleDTO.setStatus((short)1);
        userCarryRoleDTO.setType((short)1);
        userCarryRoleDTO.setName("test");
        userCarryRoleDTO.setBirth(new Date());
        userCarryRoleDTO.setCreateTime(new Date());

        List<Integer> roleIdList = Lists.newArrayList();
        roleIdList.add(1);
        roleIdList.add(2);

        userCarryRoleDTO.setRoleIdList(roleIdList);
        
        ResultBean resultBean = userApi.addUser(userCarryRoleDTO);
        System.out.println(resultBean);


    }
}
