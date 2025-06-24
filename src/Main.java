import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface CustomInfo {
    String author();
    String date();
    int version() default 1;
}
@CustomInfo(author = "LeeSang", date = "1934-07-24", version = 1)
class Demo{
    @CustomInfo(author = "LeeHa", date = "1934-08-08", version = 15)
    public void display() {
        System.out.println("Display method executed.");
    }
}
public class Main {
    public static void main(String[] args) {
        Demo dm = new Demo();
        Class<?> dmClass = dm.getClass();
        if (dmClass.isAnnotationPresent(CustomInfo.class)) {
            //print 시 true 출력
            CustomInfo classInfo = dmClass.getAnnotation(CustomInfo.class);
            System.out.println("Author: " + classInfo.author());
            System.out.println("Date: " + classInfo.date());
            System.out.println("Version: " + classInfo.version());
        }
        try {
            Method m = dmClass.getMethod("display");
            if(m.isAnnotationPresent(CustomInfo.class)) {
                CustomInfo mi = m.getAnnotation(CustomInfo.class);
                System.out.println("Author: " + mi.author());
                System.out.println("Date: " + mi.date());
                System.out.println("Version: " + mi.version());
            }
        } catch(NoSuchMethodException e) {
            e.getStackTrace();
        }
    }
}