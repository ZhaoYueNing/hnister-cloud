package cn.zynworld.hnister.zuul.manager;

import cn.zynworld.hnister.common.ResourceTypeEnum;
import cn.zynworld.hnister.common.domain.Resource;
import cn.zynworld.hnister.common.domain.ResourceExample;
import cn.zynworld.hnister.common.domain.RoleResourceRelaKey;
import cn.zynworld.hnister.common.enums.account.ResourceStatusEnum;
import cn.zynworld.hnister.common.mappers.ResourceMapper;
import cn.zynworld.hnister.common.mappers.RoleResourceRelaMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by zhaoyuening on 2018/1/27.
 * 缓存role 与 resource的对应关系
 */
public class RoleResourceManager {
    @Autowired
    private RoleResourceRelaMapper roleResourceRelaMapper;
    @Autowired
    private ResourceMapper resourceMapper;


    //roleId&resourceId`s Set
    private Map<Integer, Set<Integer>> roleResourceMap = null;
    //资源path 与 资源ID 的映射
    private Map<String, Integer> resourcePathIdMap = null;
    private Map<String, Integer> httpMethodMap;
    //保存状态0的资源 不需要被监控 白名单
    private Set<String> resourceStatus0Set = null;
    //保存状态2的资源 停用 外部不得通过API 网关进行访问
    private Set<String> resourceStatus2Set = null;


    /**
     * 初始化加载 role resource 数据
     * 初始化加载 path resourceId 数据
     */
    public void init(){
        //init map
        roleResourceMap = new HashMap<>();
        resourcePathIdMap = new HashMap<>();
        //GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
        //0    1     2     3    4      5       6        7
        //将http 字符方法转换为 int
        httpMethodMap = new HashMap<>();

        resourceStatus0Set = new HashSet<>();
        resourceStatus2Set = new HashSet<>();

        List<RoleResourceRelaKey> roleResourceRelaKeys = roleResourceRelaMapper.selectByExample(null);
        for (RoleResourceRelaKey key :
                roleResourceRelaKeys) {
            if (!roleResourceRelaKeys.contains(key.getRoleId())){
                roleResourceMap.put(key.getRoleId(),new HashSet<Integer>());
            }
            roleResourceMap.get(key.getRoleId()).add(key.getResourceId());
        }

        List<Resource> resources = resourceMapper.selectByExample(null);
        for (Resource resource :
                resources) {
            //采用 httpMethod + ||| + url 的方法
            resourcePathIdMap.put(getResourceString(resource.getMethod(),resource.getUrl()),resource.getId());
        }

        httpMethodMap.put("GET",0);
        httpMethodMap.put("HEAD",1);
        httpMethodMap.put("POST",2);
        httpMethodMap.put("PUT",3);
        httpMethodMap.put("PATCH",4);
        httpMethodMap.put("DELETE",5);
        httpMethodMap.put("OPTIONS",6);
        httpMethodMap.put("TRACE",7);

        //status 0
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andStatusEqualTo(0);
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        for (Resource resource:
             resourceList) {
            resourceStatus0Set.add(getResourceString(resource.getMethod(),resource.getUrl()));
        }
        resourceExample = new ResourceExample();
        resourceExample.createCriteria().andStatusEqualTo(2);
        resourceList = resourceMapper.selectByExample(resourceExample);
        for (Resource resource:
                resourceList) {
            resourceStatus2Set.add(getResourceString(resource.getMethod(),resource.getUrl()));
        }
    }

    /**
     * 检测资源状态
     * @param httpMethod
     * @param url
     * @return -1 非法请求链接 0 白名单 1 权限监控 2 停用 or 外部禁用
     */
    public int checkStatusForResource(String httpMethod, String url) {
        Integer httpMethodId = httpMethodMap.get(httpMethod);
        if (httpMethodId == null){
            return -1;
        }
        String resourceString = getResourceString(httpMethodId, url);
        if (resourceStatus0Set.contains(resourceString)) {
            return 0;
        }
        if (resourceStatus2Set.contains(resourceString)) {
            return 2;
        }
        return 1;
    }

    /**
     * 检测url类型
     * @param url
     * @return {@link cn.zynworld.hnister.common.enums.account.ResourceStatusEnum}
     */
    public Integer checkUrlType(String url) {
        try {
            String[] split = url.split("/");
            if (split[2].equals("rest")) {
                if (split[3].equals(ResourceStatusEnum.PUBLIC.getUrl())) {
                    return ResourceStatusEnum.PUBLIC.getCode();
                }
                if (split[3].equals(ResourceStatusEnum.PROTECTED.getUrl())) {
                    return ResourceStatusEnum.PROTECTED.getCode();
                }
                if (split[3].equals(ResourceStatusEnum.DEFAULT.getUrl())) {
                    return ResourceStatusEnum.DEFAULT.getCode();
                }
                if (split[3].equals(ResourceStatusEnum.PRIVATE.getUrl())) {
                    return ResourceStatusEnum.PRIVATE.getCode();
                }
                if (split[3].equals(ResourceStatusEnum.OTHER.getUrl())) {
                    return ResourceStatusEnum.OTHER.getCode();
                }
            }

        } catch (Exception e) {
            return ResourceStatusEnum.OTHER.getCode();
        }
        return ResourceStatusEnum.OTHER.getCode();
    }


    public Integer getResourceId(String httpMethod,String url){
        httpMethod = httpMethod.toUpperCase();
        int httpMethodId = httpMethodMap.get(httpMethod);
        return this.resourcePathIdMap.get(getResourceString(httpMethodId,url));
    }

    /**
     * 提供roleIdList 及 resource 信息判断该用户是否具备访问该资源的能力
     */
    public boolean checkAuthority(String httpMethod,String url,List<String> roleIdList){
        Integer resourceId = getResourceId(httpMethod,url);
        if (resourceId == null) {
            return false;
        }
        Set<Integer> resourceSet = null;
        for (String roleId :
                roleIdList) {
            resourceSet = roleResourceMap.get(Integer.parseInt(roleId));
            if (resourceSet != null && resourceSet.contains(resourceId)){
                //该用户为可访问该资源的角色 检验结果成功
                return true;
            }
        }
        return false;
    }

    public Map<Integer, Set<Integer>> getRoleResourceMap() {
        return roleResourceMap;
    }

    public Map<String, Integer> getResourcePathIdMap() {
        return resourcePathIdMap;
    }


    public String getResourceString(Integer httpMethod,String url){
        return httpMethod + "|||" + url;
    }
}
