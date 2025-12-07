package com.diwali.Product_Feign.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductCOntroller {

    @GetMapping("/place")
    public String placeOreder() {
        return "You got placed...";
    }

    @GetMapping("/person/{name}")
    public String orderwithName(@PathVariable String name)
    {
        return "Hii, wassup  "+ name;
    }


}
