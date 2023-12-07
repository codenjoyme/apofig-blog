package com.codenjoy.blog.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static com.codenjoy.blog.controller.Samples.*;
import static com.codenjoy.blog.utils.JsonUtils.prettyPrint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PagesControllerTest extends BaseControllerTest {

    @Test
    public void shouldGetAllPages() {
        // when
        String result = getAllPages(null);

        // then
        assertEquals(fix(SAMPLE_PAGES),
                fix(result));
    }

    @Test
    public void shouldGetAllTags() {
        // when
        String result = getAllTags();

        // then
        assertEquals(fix(SAMPLE_TAGS),
                fix(result));
    }

    @Test
    public void shouldGetAllPages_byTagName_case1() {
        // when
        String result = getAllPages("hello");

        // then
        assertEquals(fix("[\n" +
                        "  {\n" +
                        "    'fileName': '2008-06-25_15-30-00_hello-world.md',\n" +
                        "    'description': 'hello world',\n" +
                        "    'settings': {\n" +
                        "      'tags': 'hello',\n" +
                        "      'time': '2008-06-25 15:30:00'\n" +
                        "    }\n" +
                        "  },\n" +
                        "  {\n" +
                        "    'fileName': '2008-06-26_09-20-00_Some-title.md',\n" +
                        "    'description': 'Some title',\n" +
                        "    'settings': {\n" +
                        "      'tags': 'hello, empty',\n" +
                        "      'time': '2008-06-26 09:20:00'\n" +
                        "    }\n" +
                        "  }\n" +
                        "]"),
                fix(result));
    }

    @Test
    public void shouldGetAllPages_byTagName_case2() {
        // when
        String result = getAllPages("empty");

        // then
        assertEquals(fix("[\n" +
                        "  {\n" +
                        "    'fileName': '2008-06-26_09-20-00_Some-title.md',\n" +
                        "    'description': 'Some title',\n" +
                        "    'settings': {\n" +
                        "      'tags': 'hello, empty',\n" +
                        "      'time': '2008-06-26 09:20:00'\n" +
                        "    }\n" +
                        "  },\n" +
                        "  {\n" +
                        "    'fileName': '2008-07-15_20-03-00_Untitled.md',\n" +
                        "    'description': 'Untitled',\n" +
                        "    'settings': {\n" +
                        "      'tags': 'empty',\n" +
                        "      'time': '2008-07-15 20:03:00'\n" +
                        "    }\n" +
                        "  }\n" +
                        "]"),
                fix(result));
    }

    @Test
    public void shouldGetPage() {
        // when
        String result = getPage(SAMPLE_PAGE_NAME);

        // then
        assertEquals(fix(SAMPLE_PAGE_CONTENT),
                fix(result));
    }

    /**
     * Bellow you can see methods that interact with the server. Please note
     * that the order of the methods and its names are same as the order of
     * the methods of PagesController.java.
     */

    /**
     * @see PagesController#getAllPages(String)
     */
    @SneakyThrows
    private String getAllPages(String tag) {
        return prettyPrint(mvc.perform(get("/api/pages?tag={tag}", tag))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    /**
     * @see PagesController#getPage(String)
     */
    @SneakyThrows
    private String getPage(String fileName) {
        return mvc.perform(get("/api/pages/{fileName}", fileName))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * @see PagesController#getAllTags()
     */
    @SneakyThrows
    private String getAllTags() {
        return prettyPrint(mvc.perform(get("/api/pages/tags"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }
}
