package org.kenux.anything.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public ModelAndView goHome() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "kenux");
        map.put("date", LocalDateTime.now());

        mv.addObject("data", map);
        return mv;
    }
}
