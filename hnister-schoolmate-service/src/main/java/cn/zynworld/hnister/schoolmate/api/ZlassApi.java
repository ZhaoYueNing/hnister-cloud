package cn.zynworld.hnister.schoolmate.api;

import cn.zynworld.hnister.common.domain.Zlass;
import cn.zynworld.hnister.common.mappers.ZlassMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/12.
 */
@RestController
@RequestMapping(path = "rest")
public class ZlassApi {

    @Autowired
    private ZlassMapper zlassMapper;

    @GetMapping(path = "pb/zlass")
    public List<Zlass> findAll() {
        List<Zlass> zlasses = zlassMapper.selectByExample(null);
        return zlasses;
    }



}
