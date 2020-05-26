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


    @Test
    public void subTest() {
        String s1 = "打捆机,缠绕膜机,包装机,包装流水线,打包机,印刷包装,压块机,有色金属,全自动打捆机,全自动拆捆机,包装线,冷轧钢卷,热轧钢卷,电解铜板,裹膜机,冷拉伸膜机,钢带打捆机,塑带打捆机,拆捆机,打捆机,缠绕膜机,包装机,包装流水线,打包机,印刷包装,压块机,有色金属,全自动打捆机,全自动拆捆机,包装线,冷轧钢卷,热轧钢卷,电解铜板,裹膜机,冷拉伸膜机,钢带打捆机,塑带打捆机,拆捆机";
        processKeywords(s1);
    }

    public void processKeywords(String keywords) {
        if (keywords.length() > 100) {
            for (int i = 99; i > 0; i--) {
                if (keywords.charAt(i) == ',') {
                    keywords = keywords.substring(0, i);
                    break;
                }
            }
        }

        System.out.println(keywords);
    }


}
