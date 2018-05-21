package io.mendes.guides.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("")
public class RootEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listResources(@Context UriInfo uriInfo) {
        String healthURL = (uriInfo.getAbsolutePath() + "/health").replaceAll("(?<!http:)\\/\\/", "/");
        String example1URL = (uriInfo.getAbsolutePath() + "/v1/hello").replaceAll("(?<!http:)\\/\\/", "/");
        String example2URL = (uriInfo.getAbsolutePath() + "/v2/hello").replaceAll("(?<!http:)\\/\\/", "/");
        return Response.ok("{\"health\":\"" + healthURL + "\",\"example1\":\"" + example1URL + "\",  \"example2\":\"" + example2URL + "\"}").build();
    }
}
