package org.bartoszwojcik.hydropol.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the home/root endpoint.
 * <p>
 * Provides a simple welcome message and guidance to access Swagger UI documentation.
 * </p>
 */
@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    /**
     * Handles GET requests to the root path "/".
     *
     * @return a welcome message with instruction to open Swagger UI
     */
    @GetMapping("/")
    public String home() {
        return "Witaj na mojej aplikacji! Dodaj "
                + "\"/swagger-ui/index.html\" by odpalić dokumentację w swaggerze";
    }
}
