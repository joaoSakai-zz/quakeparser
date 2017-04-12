package com.joaosakai.api;

import com.joaosakai.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by jgenari on 4/8/17.
 */
@RestController
@RequestMapping("/game")
public class GameAPI {

    @Autowired
    private GameService service;

    @RequestMapping(method = RequestMethod.GET)
    public Set<String> getAllGameKeys() {
        return service.getAllAvailableGames();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getGameById(@PathVariable Integer id) {
        return service.getGameById(id);
    }
}
