package org.bartoszwojcik.hydropol.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Witaj na mojej aplikacji! Dodaj "
                + "\"/swagger-ui/index.html\" by odpalić dokumentację w swagerze";
    }
}
