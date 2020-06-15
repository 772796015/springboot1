package com.example.demo.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DJ010199 on 2020/6/15.
 */
@Controller
public class FrameworkController {
     @RequestMapping("/testThymeleaf")
     public String testThymeleaf(Model model){
                model.addAttribute("username", "张三");
               //返回testThymeleaf.html
                return "testThymeleaf";
            }
}
