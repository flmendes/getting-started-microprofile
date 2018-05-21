package io.mendes.guides.example.v2.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("v2/hello")
public class OlaMundo {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response congratulations() {
        List<String> list = new ArrayList<>();

        list.add("Parabens, sua aplicacao esta online e em execucao");
        return Response.ok(list.toString()).build();
    }
}
