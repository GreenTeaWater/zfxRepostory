package com.wechat.common.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wechat.common.annotation.XStreamCDATA;

public class MediaIdMessage {    
    @XStreamAlias("MediaId")    
    @XStreamCDATA    
    private String MediaId;    
    
    public String getMediaId() {    
        return MediaId;    
    }    
    
    public void setMediaId(String mediaId) {    
        MediaId = mediaId;    
    }    
    
}