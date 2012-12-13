package reflection;

import java.awt.Button;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Examples created by Jie Hou with some modifications.
 * 
 * @author Dongliang Yu
 * 
 */
public class HJExample {
	public int a;
	int b;
	private int c;
	
	private String privateStr = "I'm a private memeber";
	
	public HJExample(){}
	
	private HJExample(int a){}
	
	public HJExample(int a, double b){}
	
	public void foo0(double a){}
	
	private int foo1(){return 2;}
	
	private String fooStr(){return "I'm a private method";};
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Class c = Double.class;
		c = HJExample.class;
		
		HJExample e = new HJExample();
		Object object;
		Field privateField = c.getDeclaredField("privateStr");
		object = privateField.get(e);
		System.out.println(object);
		//I'm a private memeber
		
		Method privateMethod = c.getDeclaredMethod("fooStr", null);
		object = privateMethod.invoke(e, null);
		System.out.println(object);
		//"I'm a private method"

		System.out.println("extends:" + Double.class.getSuperclass());
		//SuperClass:class java.lang.Number
		
		System.out.println("Package:" + c.getPackage());
		System.out.println("-----------");
		
		Field[] ff = c.getDeclaredFields();
		for(Field f: ff) {
			System.out.println(f.getName());
		}
		System.out.println("-----------");
		//a
		//b
		//c
		//privateStr
		
		ff = c.getFields();
		for(Field f: ff) {
			System.out.println(f.getName());
		}
		//a
		System.out.println("-----------");
		
		Constructor[] cns = c.getDeclaredConstructors();
		for(Constructor cn: cns) {
			Class cx[] = cn.getParameterTypes();
			for(Class cs: cx) {
				System.out.print(cs + ",");
			}
			System.out.println();
		}
		//int,double,
		//int,
		
		Method[] mm = c.getDeclaredMethods();
		for(Method m: mm) {
			System.out.println(m.getName());
		}
		/*
		main
		foo0
		foo1
		fooStr
		func
		 */
		System.out.println("-----------");
		
		mm = c.getMethods();
		for(Method m: mm) {
			System.out.println(m.getName());
		}
		/*
		main
		foo0
		func
		wait
		wait
		wait
		equals
		toString
		hashCode
		getClass
		notify
		notifyAll
		 */
		System.out.println("-----------");
		
		Field mmod = c.getDeclaredField("c");
		System.out.println(Modifier.toString(mmod.getModifiers()));
		//private
		
		int mod = c.getModifiers();
		System.out.print(Modifier.toString(mod));
		if (Modifier.isInterface(mod))
			System.out.print(" ");
		else
			System.out.print(" class ");
		System.out.println(c.getName());
		//public class reflection.HJExample
		System.out.println("-----------");
		
		Map<Double, String> list = new HashMap<Double, String>();
		c = list.getClass();
		@SuppressWarnings("unchecked")
		TypeVariable<Class>[] tv = c.getTypeParameters();
		for(int i = 0; i < tv.length; i++) {
			String name = tv[i].getName();
			if(i == 0) 
				System.out.print("<" + name);
			else
				System.out.print("," + name);
			if(i == tv.length - 1)
				System.out.println(">");
		}
		//<K,V>
		System.out.println("extends " + c.getSuperclass());
		//extends class java.util.AbstractMap
		System.out.println("-----------");
		
		Class[] cc = c.getInterfaces();
		if (cc.length != 0)
			System.out.print("implements ");
		for (Class cite : cc)
			System.out.print(cite.getName()+", ");
		System.out.println();
		//implements java.util.Map, java.lang.Cloneable, java.io.Serializable, 
		System.out.println("-----------");
		
		cc = c.getDeclaredClasses();
		for(Class cite: cc)
			System.out.print(cite.getName() + ",");
		//java.util.HashMap$1,java.util.HashMap$Entry,java.util.HashMap$EntryIterator,java.util.HashMap$EntrySet,java.util.HashMap$HashIterator,java.util.HashMap$KeyIterator,java.util.HashMap$KeySet,java.util.HashMap$ValueIterator,java.util.HashMap$Values,
		System.out.println();
		Class ctmp = c.getDeclaringClass();
		if(ctmp != null)
			System.out.println(ctmp.getName());
		//        .
		System.out.println("-----------");
		
		Constructor cn[] = c.getDeclaredConstructors ();
		for (int i = 0; i < cn.length; i++) {
			int md = cn[i]. getModifiers();
			System.out.print(" " + Modifier.toString(md) + " " + cn[i].getName());
			Class cx[] = cn[i].getParameterTypes();
			System.out.print("(");
			for (int j = 0; j < cx.length; j++) {
				System.out.print(cx[j].getName());
				if (j < (cx.length - 1)) 
					System.out.print(", ");
			}
			System.out.println(")");
		}
		// public java.util.HashMap(java.util.Map)
		// public java.util.HashMap()
		// public java.util.HashMap(int)
		// public java.util.HashMap(int, float)
		
		for (int i = 0; i < cn.length; i++) {
			System.out.println(cn[i].toGenericString());
		}
		//public java.util.HashMap(java.util.Map<? extends K, ? extends V>)
		//public java.util.HashMap()
		//public java.util.HashMap(int)
		//public java.util.HashMap(int,float)
		System.out.println("-----------");
		
		c = Double.class;
		Method ms[]  = c.getDeclaredMethods();
		for (int i = 0; i < ms.length; i++) {
			System.out.println(ms[i].toGenericString());
		}
		System.out.println("-----------");
		
		Field fs[] = c.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i].toGenericString());
		}
		
		/**
		 * ABOVE about how to restore a CLASS
		 */
		
		/**
		 * BELOW about how to create new instances, invoke methods, and change fields' values.
		 */
		c = String.class;
		Object s = c.newInstance();
		System.out.println(s);
		//     .
		
		c = Double.class;
		Class[] type = {double.class};
		Constructor con = c.getConstructor(type);
		Object[] arg = {1};
		Object obj = con.newInstance(arg);
		System.out.println(obj);
		//1.0
		
		c = Class.forName("reflection.HJExample");
		Class ptypes[] = new Class[2];
		ptypes[0] = Class.forName("java.lang.String");
		ptypes[1] = Class.forName("java.util.Hashtable");
		Method m = c.getMethod("func",ptypes);
		HJExample example = new HJExample();
		Object arguments[] = new Object[2];
		arguments[0] = "Hello World!";
		arguments[1] = null;
		Object r = m. invoke(example, arguments);
		System.out.println(r);
		//func invoked
		//Hello World!
		
		Field f = c.getField("a");
		System.out.println("origin:" + f.get(example));
		f.set(example, 1);
		System.out.println("origin:" + f.get(example));
		//origin:0
		//origin:1
	}
	
	public String func(String s, Hashtable ht)
	{
		System.out.println("func invoked"); 
		return s;
	}
}
