package com.evoting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import voter.VoterService;

@Component
public class Beans
{
    @Bean
    public VoterService getVoterService()
    {
        return new VoterService();
    }
}
