package com.wupaas.boot.core.util;

import java.util.List;

/**
 * sql语句工具类
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午1:01:54
 */
public class SqlUtil {

    /**
     * @Description 根据集合的大小，输出相应个数"?"
     * @author fengshuonan
     */
    public static String parse(List<?> list) {
        String str = "";
        if (list != null && list.size() > 0) {
            str = str + "?";
            for (int i = 1; i < list.size(); i++) {
                str = str + ",?";
            }
        }
        return str;
    }

}
