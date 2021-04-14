package com.wupaas.boot.core.common.util;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 字符串工具
 *
 * @author Karma
 */
public class StrUtil {


    /**
     * 去除数据的空格
     *
     * @param arr
     */
    public static void trimArr(String[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = trim(arr[i]);
        }
    }

    public static void trimArr(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i));
        }
    }

    /**
     * 字符串对比相等
     *
     * @param s1 字符1
     * @param s2 字符2
     * @return 是否相等
     */
    public static boolean equals(String s1, String s2) {
        if (null == s1 || null == s2) {
            return false;
        }
        return s1.equals(s2);
    }

    /**
     * 去除字符串的空格
     *
     * @param s 字符串
     * @return 去除空格后的字符串
     */
    public static String trim(String s) {
        if (null == s) {
            return s;
        }
        return s.trim();
    }

    /**
     * 去除字符串的空格
     *
     * @param s 字符串
     * @return 去除空格后的字符串
     */
    public static String trimToEmpty(String s) {
        if (null == s) {
            return "";
        }
        return s.trim();
    }

    /**
     * 将字符串转为数组
     *
     * @param data   数据
     * @param encode 字符编码
     * @return 字节数组
     */
    public static byte[] getBytes(String data, String encode) {
        try {
            if (null == data) {
                return null;
            }
            return data.getBytes(encode);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建新的字符串
     *
     * @param buff 数组
     * @param code 字符编码
     * @return 字符串
     */
    public static String newString(byte[] buff, String code) {
        if (null == buff) {
            return null;
        }

        String res = null;
        try {
            res = new String(buff, code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * 将字符串转换为int数组
     *
     * @param str     字符串
     * @param splitor 分隔符
     * @return int数组
     */
    public static int[] transStr2IntArr(String str, String splitor) {
        String[] strArr = str.split(splitor);
        int[]    intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.valueOf(strArr[i]);
        }
        return intArr;
    }

    /**
     * 左补空格
     *
     * @param msg
     * @param length
     * @return
     */
    public static String appendLeftBlank(String msg, int length) {
        return appendLeftSymbol(msg, length, ' ', "gbk");
    }

    /**
     * 左补空格
     *
     * @param msg
     * @param length
     * @return
     */
    public static String appendLeftBlank(String msg, int length, String encoding) {
        return appendLeftSymbol(msg, length, ' ', encoding);
    }

    /**
     * 左补0
     *
     * @param msg    字符
     * @param length 长度
     * @return
     */
    public static String appendLeftZero(String msg, int length) {
        return appendLeftSymbol(msg, length, '0', "gbk");
    }

    /**
     * 左补0
     *
     * @param lenVal 长度值
     * @param length 长度
     * @return
     */
    public static String appendLeftZero(int lenVal, int length) {
        return appendLeftZero(String.valueOf(lenVal), length);
    }


    /**
     * 左补符号
     *
     * @return
     * @throws Exception
     */
    public static String appendLeftSymbol(String msg, int length, char symbol, String encoding) {
        int appendLen;
        try {
            appendLen = length - msg.getBytes(encoding).length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        if (appendLen > 0) {
            for (int i = 0; i < appendLen; i++) {
                msg = symbol + msg;
            }
            return msg;
        } else if (appendLen == 0) {
            return msg;
        } else if (appendLen < 0) {
            return msg;
        }
        return null;
    }

    /**
     * 右补空格
     *
     * @param msg
     * @param length
     * @return
     */
    public static String appendRightBlank(String msg, int length) {
        return appendRightSymbol(msg, length, ' ');
    }

    /**
     * 右补符号
     *
     * @return
     */
    public static String appendRightSymbol(String msg, int length, char symbol) {
        int appendLen = length - msg.length();
        if (appendLen > 0) {
            for (int i = 0; i < appendLen; i++) {
                msg = msg + symbol;
            }
            return msg;
        } else if (appendLen == 0) {
            return msg;
        } else if (appendLen < 0) {
            return msg;
        }
        return null;
    }

    /**
     * 去空字符串，排除null的字符串
     *
     * @param msg 输入字符串
     * @return 处理后的字符串
     */
    public static String getStringIfNull(String msg) {
        if (null == msg) {
            return "";
        }
        return msg.trim();
    }

    /**
     * 根据字符换模板和参数获取字符串
     *
     * @param result
     * @param params
     * @return
     */
    public static String getTempateString(String result, String[] params) {
        for (int i = 0; i < params.length; i++) {
            String tmp   = "{" + i + "}";
            int    index = result.indexOf(tmp);
            while (-1 < index) {
                result = result.substring(0, index) + params[i]
                        + result.substring(index + tmp.length());
                index = result.indexOf(tmp, index
                        + (null == params[i] ? 4 : params[i].length()));
            }
        }
        return result;
    }
}
