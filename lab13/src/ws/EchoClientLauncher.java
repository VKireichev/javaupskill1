package ws;

import ws.echo.Echo;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */
public class EchoClientLauncher {
    public static void main(String[] args) throws Exception {
        final Service service = Service.create(
                new URL("http://localhost:8080/ws/echo"),
                new QName("http://echo.ws/","EchoImplService"));
        final Echo echo = service.getPort(new QName("http://echo.ws/","EchoImplPort"), Echo.class);
        System.out.println(echo.getEchoAsString("Echo! ", 5));
    }
}
