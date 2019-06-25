package com.wechat.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.codehaus.xfire.client.Client;
import org.dom4j.Element;
import org.w3c.dom.Document;

public class weChatUtils {

	
	public static String getIp(String ip){
		String result = "" ;
		try {
			Client client = new Client(new URL("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl"));
	    	Object [] req = new Object[1];
	    	req[0] = ip ;
	    	
	    	Object[] results = client.invoke("getCountryCityByIp", req);
	    	String ips = null ,address = null ;
	    	for(Object obj:results){
	    		Document document = (Document)obj ;
	    		org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();  
	    		org.dom4j.Document document2 = xmlReader.read(document);
	    		Iterator iterator = document2.getRootElement().elementIterator();
	    		Element ele = (Element) iterator.next() ;
	    		ips = ele.getText();
	    		Element eleaddr = (Element) iterator.next() ;
	    		address = eleaddr.getText() ;
	    	}
	    	return "IP=" + ips + weChatConstant.rn
	    			+ "地址=" + address ;
		} catch (Exception e) {
				result = weChatConstant.error;
				e.printStackTrace(); 
		}
		return result;
	
	}

	public static String getweather(String str) {
		String result = "" ;
		try {									
			Client client = new Client(new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl"));
	    	Object [] req = new Object[1];
	    	req[0] = str ;
	    	Object[] results = client.invoke("getWeatherbyCityName", req);
	    	
	    	for(Object obj:results){
	    		Document document = (Document)obj ;
	    		org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();  
	    		org.dom4j.Document document2 = xmlReader.read(document);
	    		Iterator iterator = document2.getRootElement().elementIterator();
	    		while(iterator.hasNext()){
	    			Element ele = (Element) iterator.next() ;
	    			if(ele.getText().contains("今日天气实况")){
	    				result = ele.getText() ;
	    			}
	    		}
	    		
	    		
	    	}
	    	return "城市=" + str + weChatConstant.rn
	    			+ "天气=" + result ;
		} catch (Exception e) {
				result = weChatConstant.error;
				e.printStackTrace(); 
		}
		return result;
	
	}
	
	
	public static void main(String[] args) {
		System.out.println(getweather("785"));
		
	}
}
