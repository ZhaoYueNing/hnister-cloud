package cn.zynworld.hnister.schoolmate.api;

import cn.zynworld.hnister.common.constants.ServiceConstant;
import cn.zynworld.hnister.schoolmate.api.dto.ZlassDTO;
import cn.zynworld.hnister.schoolmate.api.fallback.ZlassRestApiFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/12
 */

@FeignClient(name = ServiceConstant.HNISTER_SCHOOLMATE,fallback = ZlassRestApiFallback.class,path = "rest")
public interface ZlassRestApi {

	/**
	 * 获取全部班级
	 * @return
	 */
	@GetMapping(path = "pb/zlass")
	public List<ZlassDTO> findAll();

	/**
	 * 通过id 获取zlass对象
	 * @param id
	 * @return
	 */
	@GetMapping(path = "pb/zlass/{id}")
	public ZlassDTO findById(@PathVariable("id") Integer id);
}
