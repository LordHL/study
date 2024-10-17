package cn.ktl.lab.springmvc.controller;

import cn.ktl.lab.springmvc.service.UserService;
import cn.ktl.lab.springmvc.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testList() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGet() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(get("/users/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGet2() throws Exception {
        // Setup
        // Configure UserService.get(...).
        final UserVO userVO = new UserVO();
        userVO.setId(0);
        userVO.setUsername("username");
        when(mockUserService.get(0)).thenReturn(userVO);

        // Run the test and verify the results
        mockMvc.perform(get("/users/v2/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testAdd() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(post("/users")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(put("/users/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/users/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }
}
