package cn.zynworld.hnister.security.api.fallback;

import cn.zynworld.hnister.security.api.ResourceRestApi;
import cn.zynworld.hnister.security.api.dto.ResourceDTO;
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
public class ResourceRestApiFallback implements ResourceRestApi {
	private Logger logger = LoggerFactory.getLogger(ResourceRestApiFallback.class);


	@Override
	public List<ResourceDTO> findAll() {
		logger.error("#hnister# feign client ResourceRestApiFallback.findAll() fail");
		return Lists.newArrayList();
	}

	@Override
	public List<ResourceDTO> findByStatus(Integer status) {
		logger.error("#hnister# feign client ResourceRestApiFallback.findByStatus() fail");
		return Lists.newArrayList();
	}
}
