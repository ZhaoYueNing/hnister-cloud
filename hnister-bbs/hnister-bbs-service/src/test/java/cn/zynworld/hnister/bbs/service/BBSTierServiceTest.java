package cn.zynworld.hnister.bbs.service;

import cn.zynworld.hnister.bbs.dto.BBSTierPostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther Buynow Zhao
 * @create 2018/4/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BBSTierServiceTest {

	@Autowired
	private BBSTierService tierService;

	@Test
	public void testPostTierToTopic() throws Exception {
		BBSTierPostDTO tierPostDTO = new BBSTierPostDTO();
		tierPostDTO.setContent("test content");
		tierPostDTO.setTopicId(11L);
		tierPostDTO.setUsername("buynow");

		Long tierId = tierService.postTierToTopic(tierPostDTO);
		System.out.println(tierId);
	}
}
