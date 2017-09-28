package com.wechat.service;

import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.springframework.stereotype.Service;

@Service("textService")
public class TextServiceImpl implements TextService{

	private static String result;

	@Override
	public String parse(String content) {
		return "靜怡吃屁";
	}
	
	
	public static String [] webService() throws ServiceException, RemoteException{
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();  //new 一个服务  
		 String endpoint = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx";  
         // 直接引用远程的wsdl文件  
         // 以下都是套路  
		 
         Call call = (Call) service.createCall();  
         call.setTargetEndpointAddress(endpoint);  
//         call.setOperationName(new QName("www.webxml.com.cn", "getCountryCityByIp"));
//         call.setUseSOAPAction(true);
         call.setSOAPActionURI("http://WebXml.com.cn/getCountryCityByIp");
         call.addParameter(new QName("www.webxml.com.cn", "theIpAddress"),
        		 org.apache.axis.encoding.XMLType.XSD_STRING,
        		 javax.xml.rpc.ParameterMode.IN);
//         call.setReturnType(new QName("www.webxml.com.cn", "getCountryCityByIp"),String[].class);
         
         String str[] = (String []) call.invoke("etCountryCityByIp " , new Object[] { "127.0.0.1" });
		return str;  
		
	}

	public static void main(String[] args) throws RemoteException, ServiceException {
		String str [] = webService() ;
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
			}
		
	}
	
}
