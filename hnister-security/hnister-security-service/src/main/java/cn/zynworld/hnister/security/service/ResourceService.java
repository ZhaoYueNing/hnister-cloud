package cn.zynworld.hnister.security.service;

import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.common.mappers.ResourceMapper;
import cn.zynworld.hnister.common.service.BaseAbstractService;
import org.springframework.stereotype.Service;

/**
 * @auther Buynow Zhao
 * @create 2018/4/26
 */
@Service
public class ResourceService extends BaseAbstractService<Resource,Integer,ResourceMapper,ResourceExample> {
}
