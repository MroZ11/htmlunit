---
title: HtmlUnit ���׽̳�
date: 2018-06-28 09:35:57
tags: 
  - HtmlUnit
  - ����
  -	java
---
htmlunit��һ�Դ��javaҳ��������ߣ���ȡҳ��󣬿�����Ч��ʹ��htmlunit����ҳ���ϵ����ݡ���Ŀ����ģ����������У�����Ϊjava������Ŀ�Դʵ�֡����û�н����������������ٶ�Ҳ�Ƿǳ�Ѹ�ٵġ�
<!-- more -->

## ��һ��������webClient���� ���������Ӳ�����

``` java
//����һ��webclient
WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
//htmlunit ��css��javascript��֧�ֲ��ã�������ر�֮
webClient.getOptions().setJavaScriptEnabled(true);//����JS��������Ĭ��Ϊtrue  
webClient.getOptions().setThrowExceptionOnScriptError(false);// js�����׳��쳣
webClient.getOptions().setCssEnabled(false);  //����css
webClient.getOptions().setDoNotTrackEnabled(false);//�쳣����
webClient.setAjaxController(new NicelyResynchronizingAjaxController());// ����Ajax�첽
webClient.waitForBackgroundJavaScript(20000);//�ȴ�js
```

## �ڶ�������ȡҳ��  

``` java
//��ȡҳ�棨����������֤���½��ҳ,���Ϊ������վ��
HtmlPage page = webClient.getPage("http://****/account/login");
```

## ��������ץȡҳ������

``` java
String str;
//��ȡҳ���TITLE
str = page.getTitleText();
System.out.println(str);
//��ȡҳ���XML����
str = page.asXml();
System.out.println(str);
//��ȡҳ����ı�
str = page.asText();
System.out.println(str);
//��ȡ�����
HtmlInput loginIdInput = page.getHtmlElementById("loginId");
HtmlInput passwordInput = page.getHtmlElementById("password");
HtmlInput validationCode = page.getHtmlElementById("validationCode");
//��ȡ��ť
HtmlButton b = page.getHtmlElementById("submit");
//�������ֵ
loginIdInput.setValueAttribute("admin");
passwordInput.setValueAttribute("123");

//��ȡͼƬ
HtmlImage valiCodeImg = (HtmlImage) page.getElementById("validationCode_img");//��֤��ͼƬ
ImageReader imageReader = valiCodeImg.getImageReader();//��ȡͼƬ
BufferedImage bufferedImage = imageReader.read(0);

//������֤�뵯��(��������Լ�ȷ�ϣ������ðٶ�����ʶ���ʶ������)
JFrame f2 = new JFrame();
JLabel l = new JLabel();
l.setIcon(new ImageIcon(bufferedImage));
f2.getContentPane().add(l);
f2.setSize(100, 100);
f2.setLocationRelativeTo(null);
f2.setLocation(840, 340);
f2.setTitle("��֤��");
f2.setVisible(true);

String valicodeStr = JOptionPane.showInputDialog("��������֤�룺");
validationCode.setValueAttribute(valicodeStr);
f2.setVisible(false);

//ģ��������ȡ�����ҳ�棨��½�ɹ���Ҳ����ֱ�ӷ�������ҳ�棬��Ϊ�Ѿ���cookie��
HtmlPage page2  =  b.click();
System.out.println(page2.asXml());

//�ر�webclient
webClient.closeAllWindows();
```
## ����������
	1. Jar���������⣺  
       �ⲿjar����������jdk�汾�����⡣�������µ�jdk,����jar�����ϰ汾�Ϳ�����
    2. ���ûЧ������:  
        ��ЩԪ����ͨ��js���Ƶģ����Ա���Ҫ��webclient��option��������,��Ӧ��js�ű���������
        (js���ܻᱨ��ֻҪ��Ӱ��������Բ��ù�)

## �ο�����
  1. HtmlUnit���Ž̳� [http://blog.csdn.net/wxl901018/article/details/44133873](https://github.com/MroZ11/mina/)

## ��������
  1. HtmlUnit ���jar�� [https://github.com/MroZ11/htmlunit/](https://github.com/MroZ11/htmlunit/)
  2. ����Դ�� [https://github.com/MroZ11/htmlunit/](https://github.com/MroZ11/htmlunit/)
