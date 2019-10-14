#CharSequence
字符串序列.包含Character,String,StringBuffer,StringBuilder,Pattern

##基础知识
###位、字节、字符、字符集、编码
1. 位：数据表示的最小单位,二进制一个0/1,就是一位。
2. 字节：存储或网络传输时使用的一种计量单位。一个字节等于8位,即8个二进制。如，1KB = 1024字节。
3. 字符：人们使用的记号,抽象意义上的一个符号。如：'1'，'中'，'$'等。
4. 字符集：字符的集合,包含若干字符,常见的字符集有：ASCII,gbk,unicode等
5. 编码: 是指字符以什么样的规则来存储或传输,如使用多少字节,每个字节怎么表示等。常见的编码有：iso-8859-1,utf-8,utf-16等

### utf-8/utf-16特点
1. utf-8长度可变,兼容ASCII编码,它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度。

> | Unicode符号范围 (十六进制) | UTF-8编码方式（二进制）| 10进制码范围 | 字节数量 |
> | --- | ---- | --- | --- |
> | 0000 0000-0000 007F | 0xxxxxxx | 0-127 | 1 |
> | 0000 0080-0000 07FF | 110xxxxx 10xxxxxx | 128-2047 | 2 |
> | 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx | 2048-65535 |3|
> | 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx | 65536 - 1114111|4|

2. utf-16不兼容ASCII编码,导致存储变大,占用2个或4个 字节。

> | 16进制编码范围 | UTF-16表示方法（二进制）| 10进制码范围 | 字节数量 |
> | --- | --- | --- | --- |
> | U+0000---U+FFFF	| xxxxxxxx xxxxxxxx yyyyyyyy yyyyyyyy |	0-65535	| 2|
> | U+10000---U+10FFFF | 110110yyyyyyyyyy 110111xxxxxxxxxx | 65536-1114111 |	4|

3. 一个汉字占几个字节？
   在Unicode字符集中,如果 使用 utf-8编码,汉字占用3个字节。使用utf-16,占用2个或4个字节。

##Character
 1. 基本类型 char 包装类, 占用内存两个字节，表示范围为unicode字符集中的0-65535.
  数字 强转char，如果数字超过65535 会与65536 取模。
  如：下面打印结果是一样的。
  ````java
  char c1 = (char) 1265533;
  char c2 = (char) 20349;
  System.out.println(c1);
  System.out.println(c2);
 ````
 2. hashCode()
 重写为：返回char在unicode字符集中的码位（codePoint）
 ````java
     @Override
     public int hashCode() {
         return Character.hashCode(value);
     }
     
     public static int hashCode(char value) {
             return (int)value;
     }
 ````
 3. equals()
 ````java
 public boolean equals(Object obj) {
         if (obj instanceof Character) {
             return value == ((Character)obj).charValue();
         }
         return false;
     }
 ````
 4. toString()
 ````java
 public String toString() {
         char buf[] = {value};
         return String.valueOf(buf);
     }
 ````

##String
###基本实现
 1. 基于char[] 实现,length代表数组长度,默认是0
 2. 构造方法 参数有多种,可以传char[],int[],String,Byte[],StringBuffer,StringBuilder等
###常用方法
 1. public int length() 返回length值
 2. public boolean isEmpty()  return length==0;
 3. public char charAt(int index)
 4. public int codePointAt(int index) 返回下标为index的char所在codePoint(Unicode码位)
 5. public int codePointBefore(int index) 前一个char的codePoint
 6. void getChars(char dst[], int dstBegin) 拷贝char到dst数组
 7. public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin) 拷贝char到dst数组
 8. public byte[] getBytes(String charsetName) throws UnsupportedEncodingException 按charsetName序列化为byte[]
 9. public byte[] getBytes(Charset charset) 同上
 10. public byte[] getBytes() 按系统默认编码序列化为byte[]
 11. public boolean equals(Object anObject) 比较value
 12. public boolean contentEquals(StringBuffer sb) 比较value
 13. public boolean contentEquals(CharSequence cs) 比较value ，参数可以是：String,StringBuffer,StringBuilder,Character
 14. public boolean equalsIgnoreCase(String anotherString) 忽略大小写比较
 15. public int compareTo(String anotherString) 如果有包含关系，返回长度差;如果没有，返回first different char的差
 16. public int compareToIgnoreCase(String str) 同上，忽略大小写
 17. public boolean regionMatches(int toffset, String other, int ooffset,int len) 判断子串是否匹配
 18. public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) 判断子串是否匹配,增加是否区分大小写
 19. public boolean startsWith(String prefix, int toffset) 下标toffset是否以...开始
 20. public boolean startsWith(String prefix) 同上
 21. public boolean endsWith(String suffix) 以...结束
 22. public int hashCode() 长度为0返回0,如果不为0，hash = 31 * hash + val[i];
 23. public int indexOf(int ch) codePoint 第一次出现的位置
 24. public int indexOf(int ch, int fromIndex) 同上
 25. public int lastIndexOf(int ch) codePoint 最后一次出现的位置,从后往前对比
 26. public int lastIndexOf(int ch, int fromIndex) 同上
 27. public int indexOf(String str) string 第一次出现的位置
 28. public int indexOf(String str, int fromIndex) 同上
 29. static int indexOf(char[] source, int sourceOffset, int sourceCount,String target, int fromIndex) char[] 中部分字符在目标String中第一次出现的位置
 30. public int lastIndexOf(String str) 最后一次出现的位置
 31. public int lastIndexOf(String str, int fromIndex) 同上
 32. public String substring(int beginIndex) 截取
 33. public String substring(int beginIndex, int endIndex) 截取
 34. public CharSequence subSequence(int beginIndex, int endIndex) 截取
 35. public String concat(String str) 连接
 36. public String replace(char oldChar, char newChar) 替换
 37. public boolean matches(String regex) 判断正则是否匹配
 38. public boolean contains(CharSequence s) 是否包含
 39. public String replaceFirst(String regex, String replacement) 替换第一个
 40. public String replaceAll(String regex, String replacement) 替换所有
 41. public String replace(CharSequence target, CharSequence replacement) 替换所有
 42. public String[] split(String regex, int limit) 拆成数组,如果匹配多个,只拆前limit个
 43. public String[] split(String regex) 全拆
 44. public static String join(CharSequence delimiter, CharSequence... elements) 拼接,每个element之间用delimiter隔开
 45. public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) 同上
 46. public String toLowerCase(Locale locale) 变小写
 47. public String toLowerCase() 变小写
 48. public String toUpperCase(Locale locale) 变大写
 49. public String toUpperCase() 变大写
 50. public String trim() 去掉前后空格
 51. public char[] toCharArray() 返回char数组
 52. public static String format(String format, Object... args) 格式化，像c语言中printf一样
 53. public static String format(Locale l, String format, Object... args) 同上 
 54. public static String valueOf(Object obj) 对象转string
 55. public static String valueOf(char data[]) char数组转string
 56. public static String valueOf(char data[], int offset, int count) 同上
 57. public static String copyValueOf(char data[], int offset, int count) 同上
 58. public static String copyValueOf(char data[]) 同上
 59. public static String valueOf(boolean b) 返回"true"或"false"
 60. public static String valueOf(char c) 返回字符string
 61. public static String valueOf(int i) 
 62. public static String valueOf(long l)
 63. public static String valueOf(float f) 
 64. public static String valueOf(double d)
 65. public native String intern() 放入字符串常量池

##StringBuffer 
###基本实现
1. 基于char[] 实现，默认容量是16.线程安全的,操作方法都有synchronized修饰
2. 有多种构造方法。参数可以为String,int（数组容量），CharSequence
###常用方法 与String相同的方法忽略
1. public synchronized int length() char数组中用到的数量
2. public synchronized int capacity()  数组长度
3. public synchronized void ensureCapacity(int minimumCapacity) 确认长度
4. public synchronized void trimToSize() 更新char[]数组的值
5. public synchronized void setLength(int newLength) 设置新长度
6. public synchronized void getChars(int srcBegin, int srcEnd, char[] dst,int dstBegin) 复制到目标数组
7. public synchronized void setCharAt(int index, char ch) 设置char[]中的值
8. public synchronized StringBuffer append() 追加
9. public synchronized StringBuffer deleteCharAt(int index)  删除char
10. public synchronized StringBuffer insert() 插入


##StringBuilder 
与StringBuffer 一致， 只是线程不安全 

##Pattern
