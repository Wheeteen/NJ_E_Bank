package util;

import java.util.Random;

//��������λ����������Ĺ�����

public class NumberRandom{
	public static final String numberChar = "0123456789";
	/**
    * ��������Ϊlength������ַ�����������ĸ�����֣�
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
