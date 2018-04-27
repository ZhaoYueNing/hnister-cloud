package cn.zynworld.hnister.zuul;

import cn.zynworld.hnister.zuul.manager.RoleResourceManager;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class HnisterZuulServerApplicationTests {

	@Autowired
	private RoleResourceManager roleResourceManager;


//	@Test
	public void contextLoads() {
//		roleResourceManager.init();
		System.out.println("");
	}

}
