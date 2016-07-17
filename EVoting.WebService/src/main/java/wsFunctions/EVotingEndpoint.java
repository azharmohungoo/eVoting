package wsFunctions;

/**
 * Created by Andreas on 2016/07/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.LoginRequest;
import io.spring.guides.gs_producing_web_service.LoginResponse;

import io.spring.guides.gs_producing_web_service.SendVoteRequest;
import io.spring.guides.gs_producing_web_service.SendVoteResponse;

/*
@Endpoint registers the class with Spring WS as a potential candidate for processing incoming SOAP messages.

@PayloadRoot is then used by Spring WS to pick the handler method based on the message’s namespace and localPart.

@RequestPayload indicates that the incoming message will be mapped to the method’s request parameter.

The @ResponsePayload annotation makes Spring WS map the returned value to the response payload.
*/

@Endpoint
public class EVotingEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private EVotingRepository eVotingRepository;

    @Autowired
    public EVotingEndpoint(EVotingRepository _eVotingRepository) {
        this.eVotingRepository = _eVotingRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(eVotingRepository.login(request.getUsername(),request.getPassword()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendVoteRequest")
    @ResponsePayload
    public SendVoteResponse sendVote(@RequestPayload SendVoteRequest request) {
        SendVoteResponse response = new SendVoteResponse();
        response.setSuccess(eVotingRepository.sendVote(request.getVotingNode(),request.getPartyNode(),request.getVoteType()));

        return response;
    }
}