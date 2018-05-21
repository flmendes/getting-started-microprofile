package io.mendes.guides.inventory;

import io.mendes.guides.inventory.client.SystemClient;
import io.mendes.guides.inventory.client.UnknownUrlException;
import io.mendes.guides.inventory.client.UnknownUrlExceptionMapper;
import io.mendes.guides.inventory.model.InventoryList;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

@ApplicationScoped
public class InventoryManager {

    private InventoryList invList = new InventoryList();
    private final String DEFAULT_PORT = System.getProperty("default.http.port");
    private final String CONTEXT_ROOT = System.getProperty("app.context.root");

    @Inject
    @RestClient
    private SystemClient defaultRestClient;

    public Properties get(String hostname) {

        Properties properties = null;
        if (hostname.equals("localhost")) {
            properties = getPropertiesWithDefaultHostName();
        } else {
            properties = getPropertiesWithGivenHostName(hostname);
        }

        if (properties != null) {
            invList.addToInventoryList(hostname, properties);
        }
        return properties;
    }

    public InventoryList list() {
        return invList;
    }

    private Properties getPropertiesWithDefaultHostName() {
        try {
            return defaultRestClient.getProperties();
        } catch (UnknownUrlException e) {
            System.err.println("The given URL is unreachable.");
        } catch (ProcessingException ex) {
            handleProcessingException(ex);
        }
        return null;
    }

    // tag::builder[]
    private Properties getPropertiesWithGivenHostName(String hostname) {
        String customURLString = "http://" + hostname + ":" + DEFAULT_PORT + "/" + CONTEXT_ROOT + "/system";
        URL customURL = null;
        try {
            customURL = new URL(customURLString);
            SystemClient customRestClient = RestClientBuilder.newBuilder()
                    .baseUrl(customURL)
                    .register(UnknownUrlExceptionMapper.class)
                    .build(SystemClient.class);
            return customRestClient.getProperties();
        } catch (ProcessingException ex) {
            handleProcessingException(ex);
        } catch (UnknownUrlException e) {
            System.err.println("The given URL is unreachable.");
        } catch (MalformedURLException e) {
            System.err.println("The given URL is not formatted correctly.");
        }
        return null;
    }
    // end::builder[]

    private void handleProcessingException(ProcessingException ex) {
        Throwable rootEx = ExceptionUtils.getRootCause(ex);
        if (rootEx != null && rootEx instanceof UnknownHostException) {
            System.err.println("The specified host is unknown.");
        } else {
            throw ex;
        }
    }

}
