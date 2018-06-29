package com.zs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class ValiCodeRead {
	
	//设置APPID/AK/SK
    public static final String APP_ID = "10720996";
    public static final String API_KEY = "kvALFLmrWqGG5HrLQLRNkWGV";
    public static final String SECRET_KEY = "Cj2qS3H0APTdh1CspZf1LRdDwZIODM3s";

    public static void main(String[] args) {
    	HashMap<String, String> options = new HashMap<String, String>();
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
       // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "D:\\2.jpg";
        JSONObject res = client.basicAccurateGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }
	
	
}
