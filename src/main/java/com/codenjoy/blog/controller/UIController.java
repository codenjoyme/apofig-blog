package com.codenjoy.blog.controller;

import com.codenjoy.blog.facade.PageFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@AllArgsConstructor
public class UIController {

    private final PageFacade pages;

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

    @GetMapping("/ui/page")
    public String getPage(Model model, HttpServletRequest request, @RequestParam("path") String path) {
        String content = pages.content(request.getContextPath(), path);
        addAttribute(model, "content", content);

        model.addAttribute("pageName", "content");
        model.addAttribute("reason", "markdown");
        return "layout";
    }

    private void addAttribute(Model model, String name, String content) {
        model.addAttribute(name,
                content == null
                        ? "There's nothing here..."
                        : content);
    }
}