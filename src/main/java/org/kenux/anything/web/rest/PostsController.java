package org.kenux.anything.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostsController {

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
