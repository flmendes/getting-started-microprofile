package io.mendes.guides.system;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

@RequestScoped
@Path("/properties")
public class SystemResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getProperties(){
        return System.getProperties();
    }
}
