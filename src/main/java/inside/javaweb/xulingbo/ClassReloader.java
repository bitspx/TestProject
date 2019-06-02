package inside.javaweb.xulingbo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class ClassReloader extends ClassLoader {
  private String classPath;
  private String packagePath = "inside/javaweb/xulingbo/";
  private String className = "inside.javaweb.xulingbo.Yufa";

  public ClassReloader(String classPath) {
    this.classPath = classPath;
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    
    byte[] classData = getData(name);
    if (classData == null) {
      throw new ClassNotFoundException(name + " not found!");
    }
    else {
      return defineClass(className, classData, 0, classData.length);
    }

  }

  private byte[] getData(String name) {
    String path = classPath + packagePath + name;
    try {
      InputStream is = new FileInputStream(path);
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[2048];
      int num = 0;
      while( (num = is.read(buffer)) != -1) {
        outStream.write(buffer, 0, num);
      }
      is.close();
      byte[] byteArray = outStream.toByteArray();
      outStream.close();
      return byteArray;
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    try {
      String path = "E:/GitHome/TestProject/target/classes/";
      ClassReloader  reloader = new ClassReloader(path);
      Class<?> c1 = reloader.findClass("Yufa.class");
      System.out.println(c1.getCanonicalName() + " load by " + c1.getClassLoader());
      System.out.println(c1.newInstance());
      
      ClassReloader reloader2 = new ClassReloader(path);
      Class<?> c2 = reloader2.findClass("Yufa.class");
      System.out.println(c2.getCanonicalName() + " load by " + c2.getClassLoader());
      System.out.println(c2.newInstance());
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
