package com.wupaas.boot.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 编码工具
 * @author lcb
 */
public class CodeUtils {
	
	/**
	 * 将byte数组转换为16进制字符串
	 * 优化 by lcb 2014-1-16
	 * @param srcBytesArr 输出的bytes数组
	 * @return 16进制字符串
	 */
   public static String bytes2HexString(byte [] srcBytesArr){
	   //检查输入参数是否为空
       if (srcBytesArr == null || srcBytesArr.length == 0) {
           return null;
       }
       //初始化长度为数组的2倍
       StringBuilder stringBuilder = new StringBuilder(srcBytesArr.length*2);
       for (int i = 0; i < srcBytesArr.length; i++) {
           int v = srcBytesArr[i] & 0xFF;
           String hv = Integer.toHexString(v);
           if (hv.length() < 2) {
               stringBuilder.append(0);
           }
           stringBuilder.append(hv.toUpperCase());
       }
       
       return stringBuilder.toString();
   }
   
   /**
    * 将16进制字符串转换为byte数组
    * 优化 by lcb 2014-1-16
    * @param  hexString 16进制字符串
    * @return byte数组
    */
   public static byte[] hexString2Bytes(String hexString) {
       if (StringUtils.isBlank(hexString)) {
           return null;
       }
       hexString = hexString.toUpperCase();
       int length = hexString.length() / 2;
       //输出数组
       byte[] outputByteArr = new byte[length];
       for (int i = 0; i < length; i++) {
           int pos = i * 2;
           outputByteArr[i] = (byte) (charToByte(hexString.charAt(pos)) << 4 | charToByte(hexString.charAt(pos + 1)));
       }
       return outputByteArr;
   }
   
   public static final String CHAR_SYMBOL_STRING="0123456789ABCDEF";
   
   /**
    * 将char装换为byte
    * @param c char
    * @return byte
    */
   private static byte charToByte(char c) {
       return (byte)CHAR_SYMBOL_STRING.indexOf(c);
   }
   
	
	/**
	 * 合并两个Bytes数组
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static byte [] gatherBytes(byte [] b1,byte [] b2){
		byte [] output=new byte[b1.length+b2.length];
		System.arraycopy(b1, 0, output, 0, b1.length);
		
		System.arraycopy(b2, 0, output, b1.length,b2.length);
		return output;
	}
	
	public static byte [] substrBytes(byte [] b,int length){
		if(b.length == length){
			return b;
		}
		
		byte [] output=new byte[length];
		System.arraycopy(b, 0, output, 0, output.length);
		return output;
	}
	
	public static byte [] substrBytes(byte [] b,int start,int end){
		byte [] output=new byte[end-start];
		System.arraycopy(b,start, output, 0, output.length);
		return output;
	}
	
	/**
	 * 将整形转为16进制字符串
	 * @param i
	 * @return
	 */
	public static String integer2HexString(int i){
		String hex=Integer.toHexString(i);
		if(hex.length()%2 == 1){
			return "0"+hex;
		}else{
			return hex;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(integer2HexString(0x2));
	}
	
	public static String toBinaryString(int i){
		String bin=Integer.toBinaryString(i);
		while(bin.length()<8){
			bin="0"+bin;
		}
		return bin;
	}
	
	public static String hexString2binaryString(String hexString) {
	    if (hexString == null || hexString.length() % 2 != 0)
	      return null;
	    String bString = "", tmp;
	    for (int i = 0; i < hexString.length(); i++) {
	      tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
	      bString += tmp.substring(tmp.length() - 4);
	    }
	    return bString;
	  }
	
	/**
	 * 获取整数的数组
	 * @param num
	 * @param length
	 * @return
	 * @throws Exception 
	 */
	public static byte [] getIntegerBytes(Integer num,Integer length) throws Exception{
		String lengthStr= StrUtil.appendLeftBlank(String.valueOf(num), 8);
		return lengthStr.getBytes();
	}
	
	
	/**
	 * 将16进制数组转化为10进制
	 * @param arr
	 * @return
	 */
	public static Integer bytesHex2Ten(byte [] arr){
		return Integer.valueOf(bytes2HexString(arr),16);
	}
	
	
	/**
	 * 将16进制数组转化为10进制
	 * @param b
	 * @return
	 */
	public static Integer byteHex2Ten(byte b){
		return bytesHex2Ten(new byte[]{b});
	}
	

	   
	/**
	* 将16进制数组转化为10进制
	* @param hexString
	* @return
	*/
	public static Integer byteHex2TenStr(String hexString){
		return bytesHex2Ten(hexString2Bytes(hexString));
	}
	   
	
	/**
	 * 获取反转的数字
	 * @param bb
	 * @return
	 */
	public static Integer byteHex2TenReverse(byte [] bb){
		int len=0;
		for(int i=bb.length-1;i>=0;i--){
			if(bb[i] !=0x00){
				break;
			}
			len++;
		}
		byte [] real=new byte[bb.length-len];
		for(int i=bb.length-1,j=0;i>=0;i--){
			if(bb[i] ==0x00){
				continue;
			}
			real[j]=bb[i];
			j++;
		}
		
		return bytesHex2Ten(real);
	}
	
	
   
   public static String bytes2HexStringFormat(byte[] src){
       StringBuilder stringBuilder = new StringBuilder("");
       if (src == null || src.length <= 0) {
           return null;
       }
       for (int i = 0; i < src.length; i++) {
           int v = src[i] & 0xFF;
           String hv = Integer.toHexString(v);
           if (hv.length() < 2) {
               stringBuilder.append(0);
           }
           stringBuilder.append(hv);
           stringBuilder.append(" ");
           if((i+1)%16 == 0){
        	   stringBuilder.append("\n");
           }
       }
       return stringBuilder.toString();
   }
   
  
   /**
    * 翻转byte数组
    * @param bb
    * @return
    */
   public static byte [] reverseByte(byte []  bb){
	   int len=0;
		for(int i=bb.length-1;i>=0;i--){
			if(bb[i] !=0x00){
				break;
			}
			len++;
		}
		byte [] real=new byte[bb.length-len];
		for(int i=bb.length-1,j=0;i>=0;i--){
			if(bb[i] ==0x00){
				continue;
			}
			real[j]=bb[i];
			j++;
		}
	   return real;
   }
   
   /**
    * 打印map数据
    * @param mapdata
    */
   public static void printMapData(Map<String,Object> mapdata){
	  for(String key: mapdata.keySet()){
		  Object value= mapdata.get(key);
		  String pntVal=getObjectString(value);
		  System.out.println(key+":"+pntVal);
	  }
   }
   
   /**
    * 获取Object的String
    * @param value
    * @return
    */
   @SuppressWarnings("rawtypes")
   public static String getObjectString(Object value){
	   String pntVal="";
	   if(value instanceof byte []){
			  pntVal=CodeUtils.bytes2HexString((byte [])value);
		}else if(value instanceof List){
			List list=(List)value;
			for(Object object:list){
				pntVal+="\n"+getObjectString(object);
			}
		}else if(value instanceof Map){
			Map map=(Map)value;
			for(Object key: map.keySet()){
				  Object val= map.get(key);
				  String pn=getObjectString(val);
				  pntVal+=key+"="+pn+",";
				  //System.out.println(key+":"+pntVal);
			}
		}
		else{
			  pntVal=String.valueOf(value);
		}
	   return pntVal;
   }
   
  
   /**
    * 获取过滤好的buff
    * @return
    */
   public static byte [] getTrimArr(byte [] buff){
	   int leftIndex=0;
	   int rightIndex=0;
	   boolean needRight=false;
	   for(int i=0;i<buff.length;i++){
		   if(buff[i]!=0x00){
			   needRight=true;
			   break;
		   }
		   leftIndex++;
	   }
	   
	   if(needRight){
		   for(int i=buff.length-1;i>=0;i--){
			   if(buff[i]!=0x00){
				   break;
			   }
			   rightIndex++;
		   }
	   }
	   
	   byte [] output=new byte[buff.length-leftIndex-rightIndex];
	   if(output.length == 0 ){
		   return buff;
	   }
	   int j=0;
	   for(int i=leftIndex;i<buff.length-rightIndex;i++){
		   output[j++]=buff[i];
	   }
	   return output;
   }
   

}
