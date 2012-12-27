package variable_arguments;

/**
 * 
 * @author Dongliang Yu
 *
 * @see <cite>[J2SE 1.5]逐渐挖掘Varargs<cite>
 * http://blog.csdn.net/shihuan830619/article/details/6045339</cite>
 */
public class Example {

	public static int sumUp(int a, int b, int... values) {
		int sum = 0;
		sum += a;
		sum += b;
		for (int value : values) {
			sum += value;
		}
		return sum;
	}

	/*
	 * We can not define the following method after <code>public static int sumUp(int a, int b, int... values)</code>
	 * is already defined, because the above one will be transformed to the following one. They are exactly the same thing.
	 * 
	 * public static int sumUp(int a, int b, int[] values) { return 0; }
	 */
	
	
	/**
	 * <code>printf</code> in C language is a classic usage of variable arguments
	 * 
	 * @param format
	 * @param args
	 */
	public static void printOut(String format, Object... args) {
		System.out.printf(format, args);
	}
	
	
	/*
	 * Why we need Variable Arguments?
	 * See the following example
	 */
	/*
	private static void testOverloading(int[] i) {
		System.out.println("A");
	}
	public static void main(String[] args) {
		testOverloading(1, 2, 3);//compiler error
	}
	 */
	
	
	//Generic
	//private static <T> void testVarargs(T... args) {}//compiler error

	//compiler OK
	private static<T> void testGeneric(T[] args) {
		for(T arg : args)
			System.out.println(arg);
	}

	public static <T> void main(String[] args) {
		int sum = sumUp(1, 2, 3, 4, 5, 6, 7);
		System.out.println(sum);
		
		printOut("sum is %d.\n", sum);
	}

}
