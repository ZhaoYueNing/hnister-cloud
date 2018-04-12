package cn.zynworld.hnister.schoolmate.api.fallback;

import cn.zynworld.hnister.common.domain.Zlass;
import cn.zynworld.hnister.schoolmate.api.ZlassRestApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/12
 */
@Component
public class ZlassRestApiFallback implements ZlassRestApi {

	@Override
	public List<Zlass> findAll() {
		return null;
	}

	@Override
	public Zlass findById(Integer id) {
		return null;
	}
}
