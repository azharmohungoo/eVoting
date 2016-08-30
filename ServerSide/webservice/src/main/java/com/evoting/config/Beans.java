package com.evoting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import voter.VoterService;

/**
 * Created by Gift on 21/08/16.
 */

@Component
public class Beans {

    @Bean
    public VoterService getVoterService()
    {
        return new VoterService();

    }

}
