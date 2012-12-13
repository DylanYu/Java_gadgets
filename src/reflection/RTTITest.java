package reflection;

/**
 * RTTI(Run-Time Type Identification) sample code
 * 
 * @author notMe
 * @see http://www.linuxtopia.org/online_books/programming_books/thinking_in_java/TIJ312_003.htm
 */

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
class Toy {
  // Comment out the following default constructor
  // to see NoSuchMethodError from (*1*)
  Toy() {}
  Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
  FancyToy() { super(1); }
}

public class RTTITest {
  static void printInfo(Class cc) {
    System.out.println("Class name: " + cc.getName() +
      " is interface? [" + cc.isInterface() + "]");
  }
  public static void main(String[] args) {
    Class c = null;
    try {
      c = Class.forName("reflection.FancyToy");
    } catch(ClassNotFoundException e) {
      System.out.println("Can't find FancyToy");
      System.exit(1);
    }
    printInfo(c);
    Class[] faces = c.getInterfaces();
    for(int i = 0; i < faces.length; i++)
      printInfo(faces[i]);
    Class cy = c.getSuperclass();
    Object o = null;
    try {
      // Requires default constructor:
      o = cy.newInstance(); // (*1*)
    } catch(InstantiationException e) {
      System.out.println("Cannot instantiate");
      System.exit(1);
    } catch(IllegalAccessException e) {
      System.out.println("Cannot access");
      System.exit(1);
    }
    printInfo(o.getClass());
    //Class name: reflection.FancyToy is interface? [false]
    //Class name: reflection.HasBatteries is interface? [true]
    //Class name: reflection.Waterproof is interface? [true]
    //Class name: reflection.Shoots is interface? [true]
    //Class name: reflection.Toy is interface? [false]
  }
}