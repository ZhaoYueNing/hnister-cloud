package cn.zynworld.hnister.security.api.fallback;

import cn.zynworld.hnister.security.api.RoleResourceRelaRestApi;
import cn.zynworld.hnister.security.api.dto.ResourceDTO;
import cn.zynworld.hnister.security.api.dto.RoleResourceRelaKeyDTO;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
@Component
public class RoleResourceRelaRestApiFallback implements RoleResourceRelaRestApi {

	private Logger logger = LoggerFactory.getLogger(RoleResourceRelaRestApiFallback.class);

	@Override
	public List<RoleResourceRelaKeyDTO> findAll() {
		logger.error("#hnister# feign client RoleResourceRelaRestApi.findAll() fail");
		return Lists.newArrayList();
	}
}
