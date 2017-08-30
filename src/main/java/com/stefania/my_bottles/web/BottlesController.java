package com.stefania.my_bottles.web;

import com.google.common.collect.Lists;
import com.stefania.my_bottles.domain.Bottle;
import com.stefania.my_bottles.exceptions.UserNotFoundException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("bottles")
@Produces(MediaType.APPLICATION_JSON)
public class BottlesController {

    private final Map<String, List<Bottle>> bottles;

    public BottlesController() {
        bottles = new HashMap<>();
        Bottle bottle = new Bottle(1, "Prosecco", "Martini");
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

    @POST
    @Path("/{username}")
    public Bottle createBottle(@PathParam("username") String username,
                               @NotNull @Valid Bottle bottle) throws UserNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        bottles.get(username).add(bottle);

        return bottle;
    }
}
