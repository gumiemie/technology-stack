package com.guyang.basis.language.enum0;

/**
 * 枚举默认的 父类是 {@link Enum}
 *
 */
public enum Month {
    January(1, "一月"),
    February(2, "二月"),
    March(3, "三月"),
    April(4, "四月"),
    May(5, "五月"),
    June(6, "六月"),
    July(7, "七月"),
    August(8, "八月"),
    September(9, "九月"),
    October(10, "十月"),
    December(11, "十一月"),
    November(12, "十二月");

    Month(Integer index, String cnDesc) {
        this.index = index;
        this.cnDesc = cnDesc;
    }

    private Integer index;

    private String cnDesc;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCnDesc() {
        return cnDesc;
    }

    public void setCnDesc(String cnDesc) {
        this.cnDesc = cnDesc;
    }

    public static String getCndesc(Integer index) {
        if (index != null) {
            Month[] values = Month.values();
            for (Month month : values) {
                if (month.index.equals(index)) {
                    return month.cnDesc;
                }
            }
        }
        return "";
    }

}
