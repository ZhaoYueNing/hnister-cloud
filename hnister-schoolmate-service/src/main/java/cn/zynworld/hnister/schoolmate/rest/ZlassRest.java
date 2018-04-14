package cn.zynworld.hnister.schoolmate.rest;

import cn.zynworld.hnister.common.domain.Zlass;
import cn.zynworld.hnister.common.mappers.ZlassMapper;
import cn.zynworld.hnister.schoolmate.api.ZlassRestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/12.
 */
@RestController
@RequestMapping(path = "rest")
public class ZlassRest implements ZlassRestApi{

    @Autowired
    private ZlassMapper zlassMapper;

    @Override
    @GetMapping(path = "pb/zlass")
    public List<Zlass> findAll() {
        List<Zlass> zlasses = zlassMapper.selectByExample(null);
        RestTemplate restTemplate = new RestTemplate();
        return zlasses;
    }

    @Override
    @GetMapping(path = "pb/zlass/{id}")
    public Zlass findById(@PathVariable("id") Integer id) {
        return zlassMapper.selectByPrimaryKey(id);
    }





}
