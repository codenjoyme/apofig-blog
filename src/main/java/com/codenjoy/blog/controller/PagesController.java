package com.codenjoy.blog.controller;


import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.facade.PageFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.codenjoy.blog.controller.PagesController.REST_URL;
import static com.codenjoy.blog.controller.Samples.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.TEXT_PLAIN_VALUE;

/**
 * <a href="https://restfulapi.net/resource-naming/">REST Api naming conventions</a>
 * Please make sure that names of methods are the same as in the model.js file and
 * PagesControllerTest - it will help to work with client side code.
 * Another thing is that the order of methods is important - it should be the same
 * as in the model.js file and in the Swagger UI.
 *
 *   HTTP GET    /api/pages
 *      Get a list of all Pages
 *      @see PagesController#getAllPages()
 *   
 *   HTTP GET    /api/pages/{pageName}
 *      Obtaining a Page content
 *      @see PagesController#getPage(String)
 *      
 */
@Slf4j
@Tag(name = "Pages API")
@RestController
@RequestMapping(REST_URL)
@AllArgsConstructor
public class PagesController {

    public static final String REST_URL = "/api";
    
    private final PageFacade pages;

    @Operation(summary = "Load all blog content from GitHub",
            description = "This will get all content from GitHub. " +
                    SECURED_OPERATION,
            parameters = @Parameter(name = "secret", description = SECURED_KEY),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK."),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject("Internal server error")))
            })
    @Order(1)
    @GetMapping("/init")
    public void loadPages(@RequestParam("secret") String secret) {
        pages.load(secret);
    }

    @Operation(summary = "Get a list of all Pages",
            description = "Pages are returned with path and description.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Pages list",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE, 
                                               examples = @ExampleObject(SAMPLE_PAGES))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE, 
                                               examples = @ExampleObject("Internal server error")))
            })
    @Order(2)
    @GetMapping("/pages")
    public List<PageDTO> getAllPages() {
        return pages.pages();
    }

    @Operation(summary = "Obtaining a Page by path",
            description = "A complete page file.",
            parameters = @Parameter(name = "path", description = "Page path", example = SAMPLE_PAGE_NAME),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK. Page content",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject(SAMPLE_PAGE_CONTENT))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(mediaType = TEXT_PLAIN_VALUE,
                                    examples = @ExampleObject("Internal server error")))
            })
    @Order(3)
    @GetMapping("/pages/{path}")
    public ResponseEntity<String> getPage(@PathVariable("path") String path) {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(TEXT_PLAIN_VALUE + ";charset=" + UTF_8.name()))
                .body(pages.content(path));
    }
}