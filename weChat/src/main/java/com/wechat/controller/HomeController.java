package com.wechat.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thoughtworks.xstream.XStream;
import com.wechat.common.util.ImageMessage;
import com.wechat.common.util.InputMessage;
import com.wechat.common.util.MsgType;
import com.wechat.common.util.OutputMessage;
import com.wechat.common.util.SerializeXmlUtil;
import com.wechat.service.TextService;

@Controller
public class HomeController {

	@Autowired
	@Qualifier("textService")
	private TextService textService ;
	
	@RequestMapping(value="/home")
	public void home(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println(1);
		acceptMessage(request, response);
	}
	
	
	
	private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {    
        // 处理接收消息    
        ServletInputStream in = request.getInputStream();    
        // 将POST流转换为XStream对象    
        XStream xs = SerializeXmlUtil.createXstream();
        
        xs.processAnnotations(InputMessage.class);    
        xs.processAnnotations(OutputMessage.class);    
        // 将指定节点下的xml节点数据映射为对象    
        xs.alias("xml", InputMessage.class);    
        // 将流转换为字符串    
        StringBuilder xmlMsg = new StringBuilder();    
        byte[] bytes = new byte[4096];    
        int len = -1 ;
        while((len = in.read(bytes)) != -1){
        	xmlMsg.append(new String(bytes, 0 , len));
        }
        // 将xml内容转换为InputMessage对象    
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());    
    
        String servername = inputMsg.getToUserName();// 服务端    
        String custermname = inputMsg.getFromUserName();// 客户端    
        long createTime = inputMsg.getCreateTime();// 接收时间    
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间    
    
        // 取得消息类型    
        String msgType = inputMsg.getMsgType();    
        // 根据消息类型获取对应的消息内容    
        if (msgType.equals(MsgType.Text.toString())) {
            // 文本消息    
            System.out.println("开发者微信号：" + inputMsg.getToUserName());    
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());    
            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));    
            System.out.println("消息内容：" + inputMsg.getContent());    
            System.out.println("消息Id：" + inputMsg.getMsgId());    
            
            String respMsg = textService.parse(inputMsg.getContent());
    
            StringBuffer str = new StringBuffer();    
            str.append("<xml>");    
            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");    
            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");    
            str.append("<CreateTime>" + returnTime + "</CreateTime>");    
            str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");    
            str.append("<Content><![CDATA["+ respMsg +"]]></Content>");    
            str.append("</xml>");    
            System.out.println(str.toString());    
            response.getWriter().write(str.toString());    
        }else if (msgType.equals(MsgType.Image.toString())) {     // 获取并返回多图片消息    
            System.out.println("获取多媒体信息");    
            System.out.println("多媒体文件id：" + inputMsg.getMediaId());    
            System.out.println("图片链接：" + inputMsg.getPicUrl());    
            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());    
    
            OutputMessage outputMsg = new OutputMessage();    
            outputMsg.setFromUserName(servername);    
            outputMsg.setToUserName(custermname);    
            outputMsg.setCreateTime(returnTime);    
            outputMsg.setMsgType(msgType);    
            ImageMessage images = new ImageMessage();    
            images.setMediaId(inputMsg.getMediaId());    
            outputMsg.setImage(images);    
            System.out.println("xml转换：/n" + xs.toXML(outputMsg));    
            response.getWriter().write(xs.toXML(outputMsg));    
    
        }    
    }    
}
