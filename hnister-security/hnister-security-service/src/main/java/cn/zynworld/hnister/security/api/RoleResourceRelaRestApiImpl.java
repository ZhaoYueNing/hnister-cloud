package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.security.mappers.RoleResourceRelaMapper;
import cn.zynworld.hnister.security.api.dto.RoleResourceRelaKeyDTO;
import cn.zynworld.hnister.security.convertor.RoleResourceRelaKeyConvertor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleResourceRelaRestApiImpl implements RoleResourceRelaRestApi {

	@Autowired
	private RoleResourceRelaMapper roleResourceRelaMapper;

	@Override
	public List<RoleResourceRelaKeyDTO> findAll() {
		return roleResourceRelaMapper.selectByExample(null).stream().map(RoleResourceRelaKeyConvertor::do2dto).collect(Collectors.toList());
	}
}
