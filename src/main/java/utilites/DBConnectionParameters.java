package utilites;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DBConnectionParameters {

    private final String url;
    private final String user;
    private final String pass;
    private final String hsqlUrl;


}
