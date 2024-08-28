package com.averagecalculator.Controller;

import com.averagecalculator.ResponseObject.Response;
import com.averagecalculator.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private Service service;
    @GetMapping("/numbers/{numberid}")
    public Response getNumber(@PathVariable("numberid") char numberid){
        return service.getResponse(numberid);
    }
}
