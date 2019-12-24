package com.guyang.basis.language.charSequence;

import org.junit.Test;

import java.math.BigDecimal;
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
        System.out.println(mobile.substring(0, 1));
        String pattern = "\\d{11}";
        System.out.println(mobile.matches(pattern));

        String replace1 = mobile.replace(new StringBuffer("0"), "1");
        System.out.println(replace1);

        String[] split = mobile.split("0", 3);
        System.out.println(Arrays.toString(split));

        String k2k2k2 = String.join(mobile, new StringBuffer("222"), "wwww", "333");
        System.out.println(k2k2k2);

        String format = String.format("my name is %s,%s,staying in ebnew %d years", "guyang", "kwkwkw", 3);
        System.out.println(format);

        Double d1 = 11111111111.222222d;
        String s = d1.toString();
        System.out.println(s);

        BigDecimal bigDecimal = new BigDecimal(d1);
        System.out.println(bigDecimal.toString());
    }

    /**
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     */
    @Test
    public void execute() {
        String name = "guyang";
        int length = name.length() - 1;
        int hash = 0;
        System.out.println(name.hashCode());
        for (char c : name.toCharArray()) {
            hash += c * 31 ^ length;
            length--;
        }

        System.out.println(hash);
    }

}
