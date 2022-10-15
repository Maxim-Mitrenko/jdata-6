package com.example.homework1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class MyController {

    private final Random random = new Random();
    private int bound = 100;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/random")
    public String random() {
        return "Ваше счастливое число: " + random.nextInt(bound);
    }

    @GetMapping("/randomList")
    public List<Integer> randomList() {
        return random.ints(10, 0, bound)
                .boxed()
                .toList();
    }

    @GetMapping("/setBound")
    public String setBound(@RequestParam int bound) {
        this.bound = bound;
        return "Число " + bound + " успешно установлено в качестве максимального числа для рандома!";
    }
}
