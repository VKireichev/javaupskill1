package ws.hello;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface Hello {
    @WebMethod String getHelloWorldAsString(String name);
}
