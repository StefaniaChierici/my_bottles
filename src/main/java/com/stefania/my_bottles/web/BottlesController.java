package com.stefania.my_bottles.web;

import com.google.common.collect.Lists;
import com.stefania.my_bottles.domain.Bottle;
import com.stefania.my_bottles.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Path("bottles")
@Produces(MediaType.APPLICATION_JSON)
public class BottlesController {

    private final Logger logger = LoggerFactory.getLogger(BottlesController.class);

    private final Map<String, List<Bottle>> bottles;

    public BottlesController() {
        bottles = new HashMap<>();
        Bottle bottle = new Bottle("Prosecco", "Martini");
        bottles.put("stefania", Lists.newArrayList(bottle));
    }

    @GET
    @Path("/{username}")
    public List<Bottle> searchBottles(@PathParam("username") String username) throws UserNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        return bottles.get(username);
    }

    @GET
    @Path("/{username}/{bottleId}")
    public Bottle getBottle(@PathParam("username") String username,
                            @PathParam("bottleId") int bottleId) throws UserNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        return bottles.get(username).get(bottleId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public Bottle createBottle(@PathParam("username") String username,
                               @NotNull @Valid Bottle bottle) throws UserNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        bottles.get(username).add(bottle);
        bottle.setId(bottles.get(username).lastIndexOf(bottle));

        return bottle;
    }
}
