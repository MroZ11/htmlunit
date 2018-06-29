package com.zs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadPic {
	
	public static void main(String[] args) throws Exception {
		System.out.println(getPicContent("D:\\pic\\1.png"));
	}
	
	private static String getPicContent(String picPath) throws Exception {
		File file = new File(picPath);
		String s = file.getParent();//��ȡ�ļ���Ŀ¼

		String name=  file.getName().substring(0,file.getName().indexOf("."));//�ļ�����
		
		String outfilePath = s +"\\out\\"+name;
		
		String cmd = " tesseract "+picPath+" "+outfilePath+" -l chi_sim";
		System.out.println(cmd);
		Process process = Runtime.getRuntime().exec(cmd);
		int w = process.waitFor();
		
		if(w==0){//process���첽��,��waitfor�ȴ������,��Ȼ�����ļ�δ���ɾ�ȥ��ȡ��

			File outFile = new File(outfilePath+".txt");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(outFile),  
	                "UTF-8"));  
	        String str;  
	        String result="";
	        while ((str = in.readLine()) != null)  
	        {  
	        	result+= str;
	        }  
	        in.close();  
			return result;
		}
		return "ERROR";
	}
	
	
	
}
