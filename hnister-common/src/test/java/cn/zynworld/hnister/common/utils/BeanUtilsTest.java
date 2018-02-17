package cn.zynworld.hnister.common.utils;

import cn.zynworld.hnister.common.domain.User;
import cn.zynworld.hnister.common.dto.security.UserCarryRoleDTO;
import org.junit.Test;

import java.util.Date;

/**
 * Created by zhaoyuening on 2018/2/10.
 */
public class BeanUtilsTest {
    @Test
    public void testFS() throws Exception {
        User sourceUser = new User();
        sourceUser.setCreateTime(new Date());
        sourceUser.setSalt("jsfdkljflkdsj");
        sourceUser.setStatus((short) 2);
        sourceUser.setPassword("fdsjklfjlsdk");
        sourceUser.setAvatar("fsdjlkfjklsd");
        sourceUser.setCity("gfjsdkljflk");
        sourceUser.setGender(1);
        sourceUser.setType((short) 2);
        sourceUser.setBirth(new Date());

        UserCarryRoleDTO targetUser = new UserCarryRoleDTO();

        long timeCounter1 = System.currentTimeMillis();

        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);
        BeanUtils.copyProperties(sourceUser,targetUser);

        timeCounter1 = System.currentTimeMillis() - timeCounter1;


        long timeCounter2 = System.currentTimeMillis();

        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);
        org.springframework.beans.BeanUtils.copyProperties(sourceUser,targetUser);

        timeCounter2 = System.currentTimeMillis() - timeCounter2;

        System.out.println("自实现 copy time :" + timeCounter1);
        System.out.println("springUtils copy time :" + timeCounter2);
    }


}
