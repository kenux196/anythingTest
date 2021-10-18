package org.kenux.anything.web;

import lombok.RequiredArgsConstructor;
import org.kenux.anything.web.dto.PostsListResponseDto;
import org.kenux.anything.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostsService postsService;

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

    @GetMapping
    public String getIndex(Model model) {
        List<PostsListResponseDto> allPosts = postsService.findAllPosts();
        model.addAttribute("posts", allPosts);
        return "index";
    }
}
