package com.example.springmvc_part1.web;

import com.example.springmvc_part1.Repository.PateintRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class securityController {

    @GetMapping("/403")
    public String accessFaild(){
        return "403";
    }
}
