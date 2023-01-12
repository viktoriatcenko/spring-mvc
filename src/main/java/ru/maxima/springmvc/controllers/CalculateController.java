package ru.maxima.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {

    @GetMapping("/calculate")
    public String numbers(@RequestParam(value = "num1" , required = false) double num1,
                          @RequestParam(value = "num2" , required = false) double num2,
                          @RequestParam(value = "operation" , required = false) String operation,
                          Model model) {

        model.addAttribute("result", "Kolesnikov Oleg");
        return "calculate";
    }

}
