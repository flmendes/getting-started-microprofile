package io.mendes.guides.example.v1.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("v1/hello")
public class HelloWorld {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response congratulations() {
        List<String> list = new ArrayList<>();

        list.add("Congratulations, your application is up and running");
        return Response.ok(list.toString()).build();
    }

}
