package ws.echo;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 08.01.14
 */

import javax.jws.WebService;

@WebService(endpointInterface = "ws.echo.Echo")
public class EchoImpl implements Echo {

    @Override
    public String getEchoAsString(String text, int number) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < number; i++) {
            response.append(text);
        }
        return response.toString();
    }

}
