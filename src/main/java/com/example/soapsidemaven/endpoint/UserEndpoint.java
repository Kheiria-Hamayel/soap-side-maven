package com.example.soapsidemaven.endpoint;

import com.example.soapsidemaven.service.UserService;
import com.soap_side.GetUserRequest;
import com.soap_side.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    @Autowired
    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }
    // the below annotation is for that the namespace is annotated to the method below
    //Signifies the local part of the payload root element handled by the annotated method.
    @PayloadRoot(namespace = "http://soap-side.com",
    localPart = "getUserRequest")
    //the user request triggered by the user
    @ResponsePayload
   // is method will return a response which will need to be converted to a response XML.
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request){
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.getUser(request.getName()));
        return  response;
    }

}
