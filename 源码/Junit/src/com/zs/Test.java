---
title: HtmlUnit 简易教程
date: 2018-06-28 09:35:57
tags: 
  - HtmlUnit
  - 爬虫
  -	java
---
htmlunit是一款开源的java页面分析工具，读取页面后，可以有效的使用htmlunit分析页面上的内容。项目可以模拟浏览器运行，被誉为java浏览器的开源实现。这个没有界面的浏览器，运行速度也是非常迅速的。
<!-- more -->

## 第一步：建立webClient连接 （配置连接参数）

``` java
//创建一个webclient
WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
//htmlunit 对css和javascript的支持不好，所以请关闭之
webClient.getOptions().setJavaScriptEnabled(true);//启用JS解释器，默认为true  
webClient.getOptions().setThrowExceptionOnScriptError(false);// js报错不抛出异常
webClient.getOptions().setCssEnabled(false);  //禁用css
webClient.getOptions().setDoNotTrackEnabled(false);//异常处理
webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步
webClient.waitForBackgroundJavaScript(20000);//等待js
```

## 第二步：获取页面  

``` java
//获取页面（试例文字验证码登陆网页,请改为随意网站）
HtmlPage page = webClient.getPage("http://****/account/login");
```

## 第三步：抓取页面内容

``` java
String str;
//获取页面的TITLE
str = page.getTitleText();
System.out.println(str);
//获取页面的XML代码
str = page.asXml();
System.out.println(str);
//获取页面的文本
str = page.asText();
System.out.println(str);
//获取输入框
HtmlInput loginIdInput = page.getHtmlElementById("loginId");
HtmlInput passwordInput = page.getHtmlElementById("password");
HtmlInput validationCode = page.getHtmlElementById("validationCode");
//获取按钮
HtmlButton b = page.getHtmlElementById("submit");
//输入框设值
loginIdInput.setValueAttribute("admin");
passwordInput.setValueAttribute("123");

//获取图片
HtmlImage valiCodeImg = (HtmlImage) page.getElementById("validationCode_img");//验证码图片
ImageReader imageReader = valiCodeImg.getImageReader();//读取图片
BufferedImage bufferedImage = imageReader.read(0);

//设置验证码弹窗(如果不想自己确认，可以用百度文字识别等识别引擎)
JFrame f2 = new JFrame();
JLabel l = new JLabel();
l.setIcon(new ImageIcon(bufferedImage));
f2.getContentPane().add(l);
f2.setSize(100, 100);
f2.setLocationRelativeTo(null);
f2.setLocation(840, 340);
f2.setTitle("验证码");
f2.setVisible(true);

String valicodeStr = JOptionPane.showInputDialog("请输入验证码：");
validationCode.setValueAttribute(valicodeStr);
f2.setVisible(false);

//模拟点击，获取点击后页面（登陆成功后，也可以直接访问其他页面，因为已经有cookie）
HtmlPage page2  =  b.click();
System.out.println(page2.asXml());

//关闭webclient
webClient.closeAllWindows();
```
## 遇到的问题
	1. Jar包报错问题：  
       外部jar包所依赖的jdk版本的问题。下载最新的jdk,或者jar包用老版本就可以了
    2. 点击没效果问题:  
        有些元素是通过js控制的，所以必须要对webclient的option进行设置,对应的js脚本才能运行
        (js可能会报错，只要不影响操作可以不用管)

## 参考资料
  1. HtmlUnit入门教程 [http://blog.csdn.net/wxl901018/article/details/44133873](https://github.com/MroZ11/mina/)

## 下载链接
  1. HtmlUnit 相关jar包 [https://github.com/MroZ11/htmlunit/](https://github.com/MroZ11/htmlunit/)
  2. 试例源码 [https://github.com/MroZ11/htmlunit/](https://github.com/MroZ11/htmlunit/)
