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
		String s = file.getParent();//获取文件根目录

		String name=  file.getName().substring(0,file.getName().indexOf("."));//文件名称
		
		String outfilePath = s +"\\out\\"+name;
		
		String cmd = " tesseract "+picPath+" "+outfilePath+" -l chi_sim";
		System.out.println(cmd);
		Process process = Runtime.getRuntime().exec(cmd);
		int w = process.waitFor();
		
		if(w==0){//process是异步的,用waitfor等待到结果,不然可能文件未生成就去读取了

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
