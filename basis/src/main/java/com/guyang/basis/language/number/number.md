#number 
包含: Byte,Short,Integer,Long,Float,Double 包装类

##byte
###基本实现
1. 占用1个字节,表示范围：-128-127, -2<<7 到 2<<7-1;
2. 内部类：ByteCache, 实际上是一个数组,用来存储byte表示范围中的256个数字
3. 构造方法：public Byte(byte value) , public Byte(String s);没有无参构造方法

###常用方法
1. public static String toString(byte b) 将byte转String
2. public static Byte valueOf(byte b) byte装箱
3. public static byte parseByte(String s, int radix) 字符串转byte,radix为进制,转换后超范围会抛异常
4. public static byte parseByte(String s) throws NumberFormatException  字符串按10进制转byte
5. public static Byte valueOf(String s, int radix) 转Byte对象
6. public static Byte valueOf(String s) throws NumberFormatException 按10进制转Byte
7. public static Byte decode(String nm) throws NumberFormatException 破译成Byte。参数decimal, hexadecimal, and octal numbers
8. public byte byteValue() 获取基本数据类型byte
9. public short shortValue()
10. public int intValue()
11. public long longValue()
12. public float floatValue()
13. public double doubleValue()
14. public String toString()
15. public int hashCode() 返回实际值
16. public boolean equals(Object obj) 比较实际值
17. public int compareTo(Byte anotherByte) 比较大小
18. public static int compare(byte x, byte y)
19. public static int toUnsignedInt(byte x) 转成int return ((int) x) & 0xff
20. public static long toUnsignedLong(byte x) 转成long return ((long) x) & 0xffL

##Short
###基本实现
1. 占用两个字节 表示范围 -32768-32767 -2<<15 到 2<<15-1
2. public Short(short value) public Short(String s)
3. 内部类 ShortCache 缓存byte表示范围的256个数字
###常用方法
同Byte类
##Integer
###基本实现
1. 占用4个字节 表示范围 -2<<31 到 2>>31-1

###常见方法 与short类同的方法忽略
1. public static int parseInt(String s, int radix) throws NumberFormatException 字符串转int，可选不同进制
2. public static Integer valueOf(String s) throws NumberFormatException 字符转Integer，10进制
3. public static int hashCode(int value)
4. public static Integer getInteger(String nm, Integer val) 获取系统变量 1:property name,2:default value
5. public static int highestOneBit(int i) 二进制最左侧的1代表的数值
6. public static int lowestOneBit(int i) 二进制最右侧的1代表的数值
7. public static int numberOfLeadingZeros(int i) 左侧填充的0的个数
8. public static int bitCount(int i) 二进制1的个数
9. public static int rotateLeft(int i, int distance) 二进制位向左翻转 如 (1011,2) = 101100
10. public static int rotateRight(int i, int distance) 二进制位向右翻转，超过最后侧位数，会到符号位
11. public static int reverse(int i) 二进制32位,全部翻转

##Long 与 Integer 基本一致

##BigInteger
###基本实现
1. signum 表示符号，实际值存储在int[]。数组长度最长为(1 << 26)
2. public BigInteger(byte[] val) 例：{1，2} = 2*256+2
   public BigInteger(String val)
   

##BigDecimal
