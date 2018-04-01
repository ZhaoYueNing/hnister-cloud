package cn.zynworld.hnister.zuul;

import cn.zynworld.hnister.common.mappers.RoleResourceRelaMapper;
import cn.zynworld.hnister.zuul.manager.RoleResourceManager;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class HnisterZuulServerApplicationTests {

	@Autowired
	private RoleResourceManager roleResourceManager;

	@Autowired
	private RoleResourceRelaMapper roleResourceRelaMapper;

//	@Test
	public void contextLoads() {
//		roleResourceManager.init();
		System.out.println("");
	}

}
