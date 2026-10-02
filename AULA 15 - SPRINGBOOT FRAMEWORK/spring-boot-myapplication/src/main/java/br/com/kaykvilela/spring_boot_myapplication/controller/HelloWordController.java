package br.com.kaykvilela.spring_boot_myapplication.controller;

import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/hello")
public class HelloWordController {

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String get1 (@PathVariable String id) {
        return "Hello word " +id;
    }
    public String get2(@RequestParam ("name") String name) {
        return "Hello Word "+name+" !";
    }
//    @PostMapping(@ String name)
//    @ResponseStatus(HttpStatus.CREATED)
//    public String post(RequestBody String name) {
//        return "Hello word "+ name;
    }

