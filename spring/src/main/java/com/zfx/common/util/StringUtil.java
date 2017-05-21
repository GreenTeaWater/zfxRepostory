package com.zfx.common.util;

import com.zfx.common.exception.SpringException;

public class StringUtil {

	public static void getString(){
		try {
			int i = 1/0 ;
		} catch (Exception e) {
			throw new SpringException("’‚æÕ «¥ÌŒÛ√Ë ˆ ");
		}
	}
}
