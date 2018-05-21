package io.mendes.guides.inventory.client;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class UnknownUrlExceptionMapper
        implements ResponseExceptionMapper<UnknownUrlException> {
    Logger LOG = Logger.getLogger(UnknownUrlExceptionMapper.class.getName());

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        LOG.info("status = " + status);
        return status == 404;
    }

    @Override
    public UnknownUrlException toThrowable(Response response) {
        return new UnknownUrlException();
    }
}
