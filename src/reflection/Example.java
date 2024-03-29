package reflection;

import java.lang.reflect.Field;

/**
 * Some examples about <cite>Java Reflection</cite>.
 * <p>Presented by sample codes followed with <b>results</b>.
 * <p>More details in packge {@code java.lang.reflect} such as 
 * {@link java.lang.reflect.Field},
 * {@link java.lang.reflect.Constructor},
 * {@link java.lang.reflect.Method}.
 * 
 * @author Dongliang Yu
 * @since 2012-12-12
 * @version 0.2
 * @see <cite>Java 反射机制深入研究</cite></br>
 * http://lavasoft.blog.51cto.com/62575/43218/
 *
 */
public class Example {

	public int a = 1;
	private int b = 2;
	public final int c = 3;

	public static int s = 4;

	/**
	 * @param augend0
	 *            {@code Integer}, not {@code int}
	 * @param augend1
	 * @return
	 */
	public int increaseA(Integer augend0, Integer augend1) {
		this.a += augend0;
		this.a += augend1;
		return this.a;
	}

	public static int increaseS(Integer augend0, Integer augend1) {
		s += augend0;
		s += augend1;
		return s;
	}

	public static void main(String[] args) throws Exception {

		Example eg0 = new Example();
		System.out.println(eg0.getClass());
		System.out.println(Class.forName("reflection.Example"));
		System.out.println(int.class);
		System.out.println(double[].class);
		System.out.println(String.class);
		System.out.println(String.class.getName());
		Example eg1 = (Example) Class.forName("reflection.Example").newInstance();
		System.out.println(eg1.getClass());
		int[] ints = new int[] { 1, 2, 3 };
		Class class1 = ints.getClass();
		Class class2 = class1.getComponentType();
		System.out.println(class1);
		System.out.println(class2);
		System.out.println(Class.forName("[D").getSuperclass());
		/*
		class reflection.Example
		class reflection.Example
		int
		class [D
		class java.lang.String
		java.lang.String
		class reflection.Example
		class [I
		int
		class java.lang.Object
		 */
		
		Example eg = new Example();
		Field[] fs = eg.getClass().getFields();
		for (Field f : fs) {
			System.out.println(f);
		}
		/*
		public int reflection.Example.a
		public int reflection.Example.c
		public static int reflection.Example.s
		 */
		
		fs = eg.getClass().getDeclaredFields();
		for (Field f : fs) {
			System.out.println(f);
		}
		/*
		public int reflection.Example.a
		private int reflection.Example.b
		public int reflection.Example.c
		public static int reflection.Example.s
		 */
		
		Class myclass = Class.forName("reflection.Example");
		Field field = myclass.getField("c");
		System.out.println(field);
		//public final int reflection.Example.c
		Object o = field.get(eg);
		System.out.println(o);
		//3
		System.out.println("modifier:"+ field.getModifiers());
		//17
		
		Reflection ref = new Reflection();
		o = ref.getProperty(eg, "a");
		System.out.println(o);
		// 1

		o = ref.getStaticProperty("reflection.Example", "s");
		System.out.println(o);
		// 4

		Object[] arguments = { 10, 100 };
		o = ref.invokeMethod(eg, "increaseA", arguments);
		System.out.println(o);
		// 111

		Object[] staticArguments = { 10, 100 };
		o = ref.invokeStaticMethod("reflection.Example", "increaseS", staticArguments);
		System.out.println(o);
		// 114

		Object[] arguments2 = { "I'm a String" };
		Object obj = ref.newInstance("java.lang.String", arguments2);
		System.out.println(obj);
		// "I'm a String"

		System.out.println(ref.isInstance("123", String.class));
		// true
		System.out.println(ref.isInstance(1, String.class));
		// false
		System.out.println(ref.isInstance(eg, Class.forName("reflection.Example")));
		// true

		int[] nums = { 0, 1, 2 };
		o = ref.getByArray(nums, 1);
		System.out.println(o);
		// 1
		o = ref.getByArray(nums, 2);
		System.out.println(o);
		// 2
	}
}
