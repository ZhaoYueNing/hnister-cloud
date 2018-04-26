package cn.zynworld.hnister.security.api.fallback;

import cn.zynworld.hnister.security.api.ResourceRestApi;
import cn.zynworld.hnister.security.api.dto.ResourceDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
@Component
public class ResourceRestApiFallback implements ResourceRestApi {
	@Override
	public List<ResourceDTO> findAll() {
		return null;
	}

	@Override
	public List<ResourceDTO> findByStatus(Integer status) {
		return null;
	}
}
