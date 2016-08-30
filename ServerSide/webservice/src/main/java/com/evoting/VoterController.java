package com.evoting;


import com.evoting.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import voter.VoterService;

import java.util.List;

/**
 * Created by Gift on 21/08/16.
 */

@RestController
public class VoterController {

    @Autowired
    private VoterService voterService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Boolean register(@RequestBody List<Object> objects){

        System.out.println(objects);

        return false;
    }




}
