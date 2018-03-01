package cn.zynworld.hnister.common.dto.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyuening on 2018/2/28.
 */
public class ContentMenuDTO {
    private Integer id;

    private String name;

    private String url;
    //ContentMenuTypeEnum.getCode()
    private Integer type;

    private Integer parentid;

    private Integer order;

    //标识为哪个菜单
    //ContentMenuIdMenu.getCode()
    private Integer menuid;


    private List<ContentMenuDTO> childs = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    public void setChilds(List<ContentMenuDTO> childs) {
        this.childs = childs;
    }

    public List<ContentMenuDTO> getChilds() {
        return childs;
    }

    /**
     * 添加子菜单
     * @param contentMenuDTO
     */
    public void addChild(ContentMenuDTO contentMenuDTO) {
        if (this.getChilds() == null) {
            this.setChilds(new ArrayList<ContentMenuDTO>());
        }
        this.getChilds().add(contentMenuDTO);
    }
}
