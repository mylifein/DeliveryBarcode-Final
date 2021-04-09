package com.chenbro.deliverybarcode.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConstantsUtil
 * @Description TODO
 * @Author c8777
 * @Date 2020/3/1 18:25
 * @Version 1.0
 **/

public class ConstantsUtil {

    /**
     * @Description //TODO   权限类型 1为菜单 2为功能  3为API
     * @Date 2020/3/1 18:25
     * @return
     **/
    public static final int PY_MENU = 1;
    public static final int PY_POINT = 2;
    public static final int PY_API = 3;

    /**
     * @Description //TODO  将流水号 数字转换为浪潮规定的字符
     * @Date 2020/12/10 18:52
     * @return
     **/
    public static final Map INTTOCODEINSPUR = new HashMap<Integer, String>();
    public static final Map CODETOINTINSPUR = new HashMap<String, Integer>();

    static {
        INTTOCODEINSPUR.put(1, "1");
        INTTOCODEINSPUR.put(2, "2");
        INTTOCODEINSPUR.put(3, "3");
        INTTOCODEINSPUR.put(4, "4");
        INTTOCODEINSPUR.put(5, "5");
        INTTOCODEINSPUR.put(6, "6");
        INTTOCODEINSPUR.put(7, "7");
        INTTOCODEINSPUR.put(8, "8");
        INTTOCODEINSPUR.put(9, "9");
        INTTOCODEINSPUR.put(10, "A");
        INTTOCODEINSPUR.put(11, "B");
        INTTOCODEINSPUR.put(12, "C");
        INTTOCODEINSPUR.put(13, "D");
        INTTOCODEINSPUR.put(14, "E");
        INTTOCODEINSPUR.put(15, "F");
        INTTOCODEINSPUR.put(16, "G");
        INTTOCODEINSPUR.put(17, "H");
        INTTOCODEINSPUR.put(18, "J");
        INTTOCODEINSPUR.put(19, "K");
        INTTOCODEINSPUR.put(20, "L");
        INTTOCODEINSPUR.put(21, "M");
        INTTOCODEINSPUR.put(22, "N");
        INTTOCODEINSPUR.put(23, "P");
        INTTOCODEINSPUR.put(24, "Q");
        INTTOCODEINSPUR.put(25, "R");
        INTTOCODEINSPUR.put(26, "S");
        INTTOCODEINSPUR.put(27, "T");
        INTTOCODEINSPUR.put(28, "U");
        INTTOCODEINSPUR.put(29, "V");
        INTTOCODEINSPUR.put(30, "W");
        INTTOCODEINSPUR.put(31, "X");
        INTTOCODEINSPUR.put(32, "Y");
        INTTOCODEINSPUR.put(33, "Z");


        CODETOINTINSPUR.put("1", 1);
        CODETOINTINSPUR.put("2", 2);
        CODETOINTINSPUR.put("3", 3);
        CODETOINTINSPUR.put("4", 4);
        CODETOINTINSPUR.put("5", 5);
        CODETOINTINSPUR.put("6", 6);
        CODETOINTINSPUR.put("7", 7);
        CODETOINTINSPUR.put("8", 8);
        CODETOINTINSPUR.put("9", 9);
        CODETOINTINSPUR.put("A", 10);
        CODETOINTINSPUR.put("B", 11);
        CODETOINTINSPUR.put("C", 12);
        CODETOINTINSPUR.put("D", 13);
        CODETOINTINSPUR.put("E", 14);
        CODETOINTINSPUR.put("F", 15);
        CODETOINTINSPUR.put("G", 16);
        CODETOINTINSPUR.put("H", 17);
        CODETOINTINSPUR.put("J", 18);
        CODETOINTINSPUR.put("K", 19);
        CODETOINTINSPUR.put("L", 20);
        CODETOINTINSPUR.put("M", 21);
        CODETOINTINSPUR.put("N", 22);
        CODETOINTINSPUR.put("P", 23);
        CODETOINTINSPUR.put("Q", 24);
        CODETOINTINSPUR.put("R", 25);
        CODETOINTINSPUR.put("S", 26);
        CODETOINTINSPUR.put("T", 27);
        CODETOINTINSPUR.put("U", 28);
        CODETOINTINSPUR.put("V", 29);
        CODETOINTINSPUR.put("W", 30);
        CODETOINTINSPUR.put("X", 31);
        CODETOINTINSPUR.put("Y", 32);
        CODETOINTINSPUR.put("Z", 33);


    }

}
