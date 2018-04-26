package cn.zynworld.hnister.bbs;

import cn.zynworld.hnister.bbs.domain.BBSPlate;
import cn.zynworld.hnister.bbs.domain.BBSPlateExample;
import cn.zynworld.hnister.bbs.mappers.BBSPlateMapper;
import cn.zynworld.hnister.common.utils.PageBean;
import cn.zynworld.hnister.bbs.service.BBSPlateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HnisterBbsServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private BBSPlateService bbsPlateService;

	@Test
	public void testBaseAdd() {
		BBSPlate bbsPlate = new BBSPlate();
		bbsPlate.setName("学院家庭");
		bbsPlate.setRemark("学院划分的板块");
		Integer result = bbsPlateService.baseAdd(bbsPlate);
		System.out.println(result);
		BBSPlateMapper mapper = null;
	}

	@Test
	public void testBaseDelete() throws Exception {
		BBSPlateExample example = new BBSPlateExample();
		example.createCriteria().andNameLike("%学院%");
		Integer result = bbsPlateService.baseDeleteByExample(example);
		System.out.println(result);
	}

	@Test
	public void testBaseFind() throws Exception {
		BBSPlateExample example = new BBSPlateExample();
		example.createCriteria().andNameLike("%学院%");
		List<BBSPlate> list = bbsPlateService.baseFindByExample(example);
		System.out.println(list.size());
	}

	@Test
	public void testBaseFindByPage() throws Exception {
		BBSPlateExample example = new BBSPlateExample();
		example.createCriteria().andNameLike("%学院%");
		PageBean<BBSPlate> pageBean = bbsPlateService.baseFindByExampleWithPage(example,1,10);
		System.out.println(pageBean.getTotal());
	}

	@Test
	public void testBaseEdit() throws Exception {
		BBSPlateExample example = new BBSPlateExample();
		example.createCriteria().andNameLike("%学院%");
		List<BBSPlate> list = bbsPlateService.baseFindByExample(example);
		System.out.println(list.size());

		BBSPlate bbsPlate = list.get(0);
		bbsPlate.setName("学院派");

		Integer updateResult = bbsPlateService.baseEdit(bbsPlate);
		System.out.println(updateResult);
	}

	@Test
	public void testBaseDeleteById() throws Exception {
		Integer id = 4;
		Integer result = bbsPlateService.baseDeleteByPrimaryKey(id);
		System.out.println(result);
	}

	@Test
	public void testGetExample() throws Exception {
		BBSPlateExample example = bbsPlateService.createExample();
		System.out.println(example);
	}
}
