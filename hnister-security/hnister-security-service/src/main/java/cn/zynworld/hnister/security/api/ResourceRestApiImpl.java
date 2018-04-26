package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.security.api.dto.ResourceDTO;
import cn.zynworld.hnister.security.convertor.ResourceConvertor;
import cn.zynworld.hnister.security.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
@RestController
@RequestMapping(path = "api")
public class ResourceRestApiImpl implements ResourceRestApi {

	@Autowired
	private ResourceService resourceService;

	@Override
	public List<ResourceDTO> findAll() {
		return resourceService.baseFindByExample(null).stream()
				.map(ResourceConvertor::do2dto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ResourceDTO> findByStatus(@PathVariable(name = "status")Integer status) {
		ResourceExample example = new ResourceExample();
		example.createCriteria().andStatusEqualTo(status);
		return resourceService.baseFindByExample(example).stream()
				.map(ResourceConvertor::do2dto)
				.collect(Collectors.toList());
	}
}
