package cn.zynworld.hnister.content.api;

import cn.zynworld.hnister.common.domain.ContentMenu;
import cn.zynworld.hnister.common.domain.ContentMenuExample;
import cn.zynworld.hnister.common.dto.content.ContentMenuDTO;
import cn.zynworld.hnister.common.mappers.ContentMenuMapper;
import cn.zynworld.hnister.common.utils.BeanUtils;
import cn.zynworld.hnister.common.utils.ResultBean;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by zhaoyuening on 2018/2/28.
 */
@RestController
public class ContentMenuApi {
    @Autowired
    private ContentMenuMapper contentMenuMapper;

    @Transactional
    @PostMapping(path = "contentMenu")
    public ResultBean add(@RequestBody ContentMenu contentMenu) {
        int result = contentMenuMapper.insert(contentMenu);
        return ResultBean.create(result > 0);
    }

    @Transactional
    @DeleteMapping(path = "contentMenu/{id}")
    public ResultBean delete(@PathVariable Integer id) {
        //删除该菜单项
        contentMenuMapper.deleteByPrimaryKey(id);

        ContentMenuExample contentMenuExample = new ContentMenuExample();
        List<ContentMenu> contentMenus = null;
        //删除该菜单 需要删除该菜单下的所有子集菜单
        //保存所有需要被删除的ContentMenu
        Stack<ContentMenu> needDeleteMenuStack = new Stack<>();

        //获取所有parentId 为该id的菜单
        contentMenuExample.createCriteria().andParentIdEqualTo(id);
        contentMenus = contentMenuMapper.selectByExample(contentMenuExample);
        needDeleteMenuStack.addAll(contentMenus);

        ContentMenu contentMenu = null;
        while(!needDeleteMenuStack.isEmpty()){
             contentMenu = needDeleteMenuStack.pop();

            contentMenuExample.clear();
            contentMenuExample.createCriteria().andParentIdEqualTo(contentMenu.getId());
            contentMenus = contentMenuMapper.selectByExample(contentMenuExample);
            needDeleteMenuStack.add(contentMenu);

            //删除
            contentMenuMapper.deleteByPrimaryKey(contentMenu.getId());
        }
        return ResultBean.success();
    }

    //将返回包括所有该节点的 子节点 传入0搜索根节点
    //TODO 当前效率低下 后期采用redis 缓存
    @GetMapping(path = "contentMenu/{id}/@/for=carryChilds")
    public ContentMenuDTO getByIdCarryChilds(@PathVariable Integer id) {
        ContentMenuDTO rootContentMenuDTO = new ContentMenuDTO();

        //获取contentMenuDTO
        ContentMenu contentMenu = contentMenuMapper.selectByPrimaryKey(id);
        if (contentMenu == null) {
            return null;
        }
        BeanUtils.copyProperties(contentMenu,rootContentMenuDTO);

        Stack<ContentMenuDTO> stack = new Stack<>();
        stack.push(rootContentMenuDTO);

        //遍历获取子节点
        ContentMenuDTO currentMenuDTO = null;
        ContentMenuExample contentMenuExample = new ContentMenuExample();
        List<ContentMenuDTO> childMenuList = null;
        while (!stack.isEmpty()) {
            contentMenuExample.clear();
            currentMenuDTO = stack.pop();

            //获取子节点
            childMenuList = contentMenuMapper.selectByExample(contentMenuExample).stream().map(menu -> {
                ContentMenuDTO menuDTO = new ContentMenuDTO();
                BeanUtils.copyProperties(menu, menuDTO);
                return menuDTO;
            }).collect(Collectors.toList());
            rootContentMenuDTO.setChilds(childMenuList);
            stack.addAll(childMenuList);
        }

        return rootContentMenuDTO;
    }



}
