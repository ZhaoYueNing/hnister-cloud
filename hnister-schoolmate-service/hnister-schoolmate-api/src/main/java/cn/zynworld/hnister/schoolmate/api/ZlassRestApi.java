package cn.zynworld.hnister.schoolmate.api;

import cn.zynworld.hnister.common.domain.Zlass;
import cn.zynworld.hnister.common.constants.ServiceConstant;
import cn.zynworld.hnister.schoolmate.api.fallback.ZlassRestApiFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public List<Zlass> findAll();

	/**
	 * 通过id 获取zlass对象
	 * @param id
	 * @return
	 */
	@GetMapping(path = "pb/zlass/{id}")
	public Zlass findById(@PathVariable("id") Integer id);
}
