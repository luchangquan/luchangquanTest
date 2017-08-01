package com.renke.rdbao.util;

public  class TransferTime {
	
	public static String transferTime(Integer duration){
		String durationStr="";
		 // 把秒计算成时分秒
		
		 int h=(duration/3600);
		 int m=((duration%3600)/60);
		 int s=((duration%3600)%60);
		 if(h>0){
			 durationStr=h+"时"+m+"分"+s+"秒" ;
		 }else if(m>0){
			 durationStr= m+"分"+s+"秒" ;
		 }else{
			 durationStr= s+"秒" ;
		 }
		return durationStr;
	}

}
