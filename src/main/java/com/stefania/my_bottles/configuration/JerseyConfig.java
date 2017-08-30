package com.stefania.my_bottles.configuration;

import com.stefania.my_bottles.web.BottlesController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BottlesController.class);
    }

}
