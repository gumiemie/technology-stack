package com.guyang.basis.language.charSequence;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description String test
 * @date 2019-08-26 15:52
 */
public class StringTest {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{100, 123};
        try {
            String s = new String(bytes, "GBK");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s1 = "aaajjekleke";
        String replace = s1.replace('a', 'b');
        System.out.println(replace);
        System.out.println(s1.concat("kwkw"));

        String mobile = "18500047710";
        String pattern = "\\d{11}";
        System.out.println(mobile.matches(pattern));

        String replace1 = mobile.replace(new StringBuffer("0"), "1");
        System.out.println(replace1);

        String[] split = mobile.split("0", 3);
        System.out.println(Arrays.toString(split));

        String k2k2k2 = String.join(mobile, new StringBuffer("222"), "wwww", "333");
        System.out.println(k2k2k2);

        String format = String.format("my name is %s,%s,staying in ebnew %d years", "guyang","kwkwkw", 3);
        System.out.println(format);
    }
}
