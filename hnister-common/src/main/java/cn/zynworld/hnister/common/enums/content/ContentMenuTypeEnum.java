package cn.zynworld.hnister.common.enums.content;

import cn.zynworld.hnister.common.enums.BaseCodeEnum;

/**
 * Created by zhaoyuening on 2018/2/28.
 * 菜单类型
 */
public enum ContentMenuTypeEnum implements BaseCodeEnum {
    //无跳转菜单
    NO_URL_TYPE(1),
    //跳转到内部菜单
    INSERT_URL_TYPE(2),
    //跳转到外部菜单
    EXTERNAL_URL_TYPE(3);

    private int code;

    ContentMenuTypeEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
