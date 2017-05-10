package util;

import java.util.Random;

//生成任意位数的随机数的工具类

public class NumberRandom{
	public static final String numberChar = "0123456789";
	/**
    * 产生长度为length的随机字符串（包括字母和数字）
    * @param length
    * @return
    */
   public static String generateString(int length) {
       StringBuffer sb = new StringBuffer();
       Random random = new Random();
       for (int i = 0; i < length; i++) {
           sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
       }
       return sb.toString();
   }
   
   
    
}