package cn.zynworld.hnister.schoolmate.rest;

import cn.zynworld.hnister.schoolmate.api.dto.ZlassDTO;
import cn.zynworld.hnister.schoolmate.convertor.ZlassConvertor;
import cn.zynworld.hnister.schoolmate.domain.Zlass;
import cn.zynworld.hnister.schoolmate.mappers.ZlassMapper;
import cn.zynworld.hnister.schoolmate.api.ZlassRestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ZlassDTO> findAll() {
        List<ZlassDTO> zlasses = zlassMapper.selectByExample(null).stream()
                .map(ZlassConvertor::do2dto).collect(Collectors.toList());
        RestTemplate restTemplate = new RestTemplate();
        return zlasses;
    }

    @Override
    @GetMapping(path = "pb/zlass/{id}")
    public ZlassDTO findById(@PathVariable("id") Integer id) {
        return ZlassConvertor.do2dto(zlassMapper.selectByPrimaryKey(id));
    }





}
