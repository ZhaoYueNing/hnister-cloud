package cn.zynworld.hnister.common.enums.content;

import cn.zynworld.hnister.common.enums.BaseCodeEnum;

/**
 * Created by zhaoyuening on 2018/2/28.
 * 菜单标识
 */
public enum ContentMenuIdMenu implements BaseCodeEnum {
    INDEX_NAV(1);

    private int code;

    ContentMenuIdMenu(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
