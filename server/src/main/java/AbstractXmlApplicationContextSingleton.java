import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractXmlApplicationContextSingleton {
    private static AbstractXmlApplicationContext ac;

    private AbstractXmlApplicationContextSingleton() {}

    public static AbstractXmlApplicationContext getInstance() {
        if(ac == null)
            ac = new ClassPathXmlApplicationContext("spring-config.xml");
        return ac;
    }
}
