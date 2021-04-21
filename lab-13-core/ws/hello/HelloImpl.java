package ws.hello;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */

import javax.jws.WebService;

@WebService(endpointInterface = "ws.hello.Hello")
public class HelloImpl implements Hello {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello " + name + "!";
    }

}
