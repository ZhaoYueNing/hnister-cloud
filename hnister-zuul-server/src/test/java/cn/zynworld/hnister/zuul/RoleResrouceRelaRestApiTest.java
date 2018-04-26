package cn.zynworld.hnister.zuul;

import cn.zynworld.hnister.security.api.RoleResourceRelaRestApi;
import cn.zynworld.hnister.security.api.dto.RoleResourceRelaKeyDTO;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleResrouceRelaRestApiTest {

	@Autowired
	private RoleResourceRelaRestApi roleResourceRelaRestApi;

	@Test
	public void test() throws Exception {
		List<RoleResourceRelaKeyDTO> roleResourceRelaKeyDTOS = roleResourceRelaRestApi.findAll();
		System.out.println(roleResourceRelaKeyDTOS);
	}
}
