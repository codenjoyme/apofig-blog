package com.codenjoy.blog.controller;

import com.codenjoy.blog.facade.PageFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("header", "List of Pages");
        model.addAttribute("pageName", "list-pages");
        return "layout";
    }

    @GetMapping("/ui/page/{path}")
    public String getPage(Model model, HttpServletRequest request, @PathVariable("path") String path) {
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