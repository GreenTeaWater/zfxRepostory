package com.study.zfx.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReflectTest {
	private static final long serialVersionUID = 4711016479975371037L;
	/**
	 * 通过一个对象获得完整的包名和类名
	 */
	@Test
	public void test1(){
		ReflectTest reflectTest = new ReflectTest();
		System.out.println(reflectTest.getClass().getName());
	}
	
	/**
	 * 实例化Class类对象的三种方式，常用第一种
	 */
	@Test
	public void test2() throws ClassNotFoundException{
		Class class1 = null ;
		Class class2 = null ;
		Class class3 = null ;
		
		class1 = Class.forName("com.reflect.zfx.ReflectTest");
		class2 = new ReflectTest().getClass();
		class3 = ReflectTest.class;
		System.out.println(class1.getName());
		System.out.println(class2.getName());
		System.out.println(class3.getName());
	}
	
	/**
	 * 获取一个对象的父类与实现的接口
	 */
	@Test
	public void test3() throws ClassNotFoundException{
		Class class1 = Class.forName("com.reflect.zfx.ReflectTest");
		//父类，java是单继承，所以是一个父类
		Class superClass = class1.getSuperclass();
		System.out.println(superClass.getName());
		//接口
		Class [] interfaceClass = class1.getInterfaces();
		for(Class c :interfaceClass){
			System.out.println(c.getName());
		}
	}
	
	/**
	 * 通过反射机制实例化一个类的对象
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void test4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class class1 = Class.forName("com.reflect.zfx.Personal" ,false  , Personal.class.getClassLoader());
		//Class class1 = Class.forName("com.reflect.zfx.Personal");
		//这种方式只支持无参数构造创建实例的方式
		Personal personal = (Personal) class1.newInstance();
		personal.setId("1");
		personal.setName("zfx");
		System.out.println(personal);
		
		Constructor<?> [] constructors = class1.getConstructors();
		for(int i = 0 ;i< constructors.length ; i++){
			System.out.println("第" + (i+1) + "构造：");
			Class [] classs = constructors[i].getParameterTypes();
			for(Class c : classs){
				System.out.print(c.getName() + ",");
			}
			System.out.println();
		}
		//构造函数实例化对象
		Personal personal2 = (Personal) constructors[1].newInstance("zfx2" , "2");
		System.out.println(personal2);
	}
	
	/**
	 * 
	 * 获取某个类的全部属性,本类和父类，包括继承和接口，private属性不能继承
	 */
	@Test
	public void test5() throws ClassNotFoundException{
		Class class1 = Class.forName("com.reflect.zfx.Personal");
		Field [] fields = class1.getDeclaredFields();
		for(Field field:fields){
			int modifier = field.getModifiers();
			String mod = Modifier.toString(modifier);
			Class c = field.getType() ;//
			String type = c.getName();
			String name = field.getName();
			System.out.println("访问修饰符：" + mod + ",属性类型："+ type + ",属性名：" +name);
		}
		
		Field [] ParentFields = class1.getFields();
		for(Field field:ParentFields){
			int modifier = field.getModifiers();
			String mod = Modifier.toString(modifier);
			Class c = field.getType() ;//
			String type = c.getName();
			String name = field.getName();
			System.out.println("父类访问修饰符：" + mod + ",父类属性类型："+ type + ",父类属性名：" +name);
		}
	}
	
	/**
	 * 获取某个类的全部方法
	 */
	@Test
	public void test6() throws ClassNotFoundException{
		Class class1 = Class.forName("com.reflect.zfx.Personal");
		
		Method [] methods = class1.getMethods();
		for(Method method :methods){
			String methodName = method.getName();
			String mod = Modifier.toString(method.getModifiers());
			Class returnClass = method.getReturnType();
			Class [] cs = method.getExceptionTypes();
			Class [] ps = method.getParameterTypes();
			System.out.print("方法名：" + methodName + " ");
			System.out.print("修饰符" + mod + " ");
			System.out.print("返回类型" + returnClass.getName() + " ");
			for(Class c : ps){
				System.out.print("方法参数：" + c.getName() + " ");
			}
			for(Class c : cs){
				System.out.print("可能异常：" + c.getName() + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 通过反射机制调用某个类的方法 ,
	 */
	@Test
	public void test7() throws Exception{
		Class class1 = Class.forName("com.reflect.zfx.Personal");
		Method method = class1.getDeclaredMethod("testExcep",new Class[]{String.class,  String.class});
		method.setAccessible(false);
		method.invoke(class1.newInstance(), new Object[]{"zfx" , "25"});
	}
	
	/**
	 * 通过反射机制操作某个类的属性
	 */
	@Test
	public void test8() throws ClassNotFoundException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		Class class1 = Class.forName("com.reflect.zfx.Personal");
		Field field = class1.getDeclaredField("name");
		Personal per = (Personal) class1.newInstance();
		field.setAccessible(true);
		field.set(per, "zfx");
		System.out.println(field.get(per));
	}
	
	/**
	 * JDK动态代理，基于反射
	 */
	@Test
	public void test9(){
		IPersonal ipersonal = reflectPersonal();
		ipersonal.reflet();
		
	}
	
	public IPersonal reflectPersonal(){
		final Personal p = new Personal();
		IPersonal personal = (IPersonal) Proxy.newProxyInstance(ReflectTest.class.getClassLoader(), p.getClass().getInterfaces(), new InvocationHandler() {
		 
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("方法执行前.......");
			method.invoke(p, args);
			System.out.println("方法执行后.......");
			return proxy;
		}
		});
		return personal;
	}
	
	/**
	 * 利用反射往泛型为Integer类型的集合中插入其他类型的值
	 */
	@Test
	public void test10() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		List<Integer> lists = new ArrayList<Integer>();
		lists.add(1);
		Class c = lists.getClass();
		Method method = c.getDeclaredMethod("add", Object.class);
		method.invoke(lists, "hahahah");
		System.out.println(lists.get(1));
	}
	
	/**
	 * 反射机制用于工厂模式
	 * @throws Exception 
	 */
	@Test
	public void test11() throws Exception{
		Personal personal = personalFactory("com.reflect.zfx.Personal");
		System.out.println(personal);
	}
	
	public Personal personalFactory(String className) throws Exception{
		return (Personal)Class.forName(className).newInstance();
	}
	
	
	
	
	
	
	
	
	private String s ;
	@Test
	public void test0() throws Exception{
		s = "9" ;
		aa(s) ;
		System.out.println(s);
	}

	private void aa(String s) throws Exception {
		Class ss= s.getClass();
		Field field = ss.getDeclaredField("value");
		field.setAccessible(true);
		Object object = field.get(s);
		char[] c = (char[]) object;
		System.out.println(c);
		field.setAccessible(true);
		
        field.set(s, "c".toCharArray());
	}
}
