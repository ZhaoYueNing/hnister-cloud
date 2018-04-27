package cn.zynworld.hnister.security.api;

import cn.zynworld.hnister.common.constants.ServiceConstant;
import cn.zynworld.hnister.security.api.dto.RoleResourceRelaKeyDTO;
import cn.zynworld.hnister.security.api.fallback.RoleResourceRelaRestApiFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = ServiceConstant.HNISTER_SECURITY,fallback = RoleResourceRelaRestApiFallback.class,path = "api")
public interface RoleResourceRelaRestApi {

	@GetMapping(path = "roleResourceRelas")
	List<RoleResourceRelaKeyDTO> findAll();
}
