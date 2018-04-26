package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.constants.ServiceConstant;
import cn.zynworld.hnister.security.api.dto.ResourceDTO;
import cn.zynworld.hnister.security.api.fallback.ResourceRestApiFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = ServiceConstant.HNISTER_SECURITY,fallback = ResourceRestApiFallback.class,path = "api")
public interface ResourceRestApi {
	@GetMapping(path = "resources")
	List<ResourceDTO> findAll();

	@GetMapping(path = "resources/status/{status}")
	List<ResourceDTO> findByStatus(@PathVariable(name = "status") Integer status);
}
