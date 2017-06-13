package com.study.zfx.reflect;

public class ParentObject {

	public String parentName ;
	
	static{
		System.out.println(1111);
	}
	
	public void test(){
		System.out.println("test");
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
