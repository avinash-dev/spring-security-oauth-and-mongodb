package uk.ac.ebi.pride.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SampleController {

    @RequestMapping(method = RequestMethod.GET,path = "/msg")
    public String getMsg(Principal principal){
        if(principal!=null){
            System.out.println(principal.getName());
        }
        return "Hello!!!";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/secured/msg")
    public Object getSecuredMsg(Principal principal){
        if(principal!=null)
            return principal;
        return "Hello to secured world of Oauth 2.0!!!";
    }

}
