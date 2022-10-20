package com.example.homework1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Random;

@RestController
public class MyController {

    private final Random random = new Random();
    private int bound = 100;

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('random', 'randomList')")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/hi")
    @PreAuthorize("#username==authentication.principal.username")
    public String hi(@RequestParam String username) {
        return "Hi, " + username + "!";
    }

    @GetMapping("/random")
    @Secured("ROLE_random")
    public String random() {
        return "Ваше счастливое число: " + random.nextInt(bound);
    }

    @GetMapping("/randomList")
    @RolesAllowed("ROLE_randomList")
    public List<Integer> randomList() {
        return random.ints(10, 0, bound)
                .boxed()
                .toList();
    }

    @GetMapping("/setBound")
    @Secured("ROLE_admin")
    public String setBound(@RequestParam int bound) {
        this.bound = bound;
        return "Число " + bound + " успешно установлено в качестве максимального числа для рандома!";
    }
}
