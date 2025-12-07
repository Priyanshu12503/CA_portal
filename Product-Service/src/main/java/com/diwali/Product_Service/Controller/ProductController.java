package com.diwali.Product_Service.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/place")
    public String placeOreder() {
        return "You got placed...";
    }

        @GetMapping("/person/{name}")
        public String orderwithName(@PathVariable String name)
        {
            return "Hii, wassup miss "+ name;
        }

}
