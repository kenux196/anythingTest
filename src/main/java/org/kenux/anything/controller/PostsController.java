package org.kenux.anything.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostsController {

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
