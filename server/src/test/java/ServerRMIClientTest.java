import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ServerRMIClientTest {
    private Client client;
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        new Server();
        applicationContext = new ClassPathXmlApplicationContext("spring-config-test.xml");
        client = (Client) applicationContext.getBean("client");
    }

    @Test
    public void testRMIClient() {
        client.clientInterface.getAutoCompletionDataSnapshot();
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }
}