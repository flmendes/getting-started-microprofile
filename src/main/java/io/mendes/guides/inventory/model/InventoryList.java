package io.mendes.guides.inventory.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InventoryList {

    private List<System> systems = new ArrayList<System>();

    public List<System> getSystems() {
        return systems;
    }

    public int getTotal() {
        return systems.size();
    }

    public void addToInventoryList(String hostname, Properties systemProps) {
        Properties props = new Properties();
        props.setProperty("os.name", systemProps.getProperty("os.name"));
        props.setProperty("user.name", systemProps.getProperty("user.name"));

        System host = new System(hostname, props);
        if (!systems.contains(host))
            systems.add(host);
    }

    class System {

        private final String hostname;
        private final Properties properties;

        public System(String hostname, Properties properties) {
            this.hostname = hostname;
            this.properties = properties;
        }

        public String getHostname() {
            return hostname;
        }

        public Properties getProperties() {
            return properties;
        }

        @Override
        public boolean equals(Object host) {
            if (host instanceof System) {
                return hostname.equals(((System) host).getHostname());
            }
            return false;
        }
    }
}