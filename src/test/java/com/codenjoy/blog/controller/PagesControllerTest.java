package com.codenjoy.blog.controller;

import org.junit.jupiter.api.Test;

import static com.codenjoy.blog.controller.Samples.*;
import static com.codenjoy.blog.utils.JsonUtils.prettyPrint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PagesControllerTest extends BaseControllerTest {

    @Test
    public void shouldGetAllPages() throws Exception {
        // when
        String result = getAllPages();

        // then
        assertEquals(fix(SAMPLE_PAGES),
                fix(result));
    }

    @Test
    public void shouldGetPage() throws Exception {
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
     * @see PagesController#getAllPages()
     */
    private String getAllPages() throws Exception {
        return prettyPrint(mvc.perform(get("/api/pages"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    /**
     * @see PagesController#getPage(String)
     */
    private String getPage(String fileName) throws Exception {
        return mvc.perform(get("/api/pages/{fileName}", fileName))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
