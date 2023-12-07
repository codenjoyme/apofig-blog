package com.codenjoy.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class UIController {

    @GetMapping()
    public String main() {
        return "redirect:/ui/pages";
    }

    @GetMapping("/ui/pages")
    public String listPages(Model model) {
        model.addAttribute("header", "Table of Contents");
        model.addAttribute("pageName", "list-pages");
        return "layout";
    }

    @GetMapping("/ui/pages/tags")
    public String listTags(Model model) {
        model.addAttribute("header", "Tags");
        model.addAttribute("pageName", "list-tags");
        return "layout";
    }

    @GetMapping("/ui/page")
    public String getPage(Model model) {
        model.addAttribute("pageName", "content");
        model.addAttribute("reason", "markdown");
        return "layout";
    }
}