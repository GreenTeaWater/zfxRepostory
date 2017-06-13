package com.study.zfx.reflect;

public class Personal extends ParentObject implements IPersonal{
	
	public Personal(){
		
	}
	
	public Personal(String name){
		this.name = name ;
	}
	
	public Personal(String name ,String id){
		this.name = name ;
		this.id  = id ;
	}

	private String id ;
	private String name ;
    private int age ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	@Override
//	public String toString() {
//		return "Personal [id=" + id + ", name=" + name + ", age=" + age + "]";
//	}
    
	private void testExcep(String name ,String age ) throws Exception{
		System.out.println(name  + " ,"+  age);
	}

	@Override
	public void reflet() {
	System.out.println("reflet");
		
	}
}
