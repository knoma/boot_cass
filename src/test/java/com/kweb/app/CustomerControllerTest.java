package com.kweb.app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @MockBean
    private CustomerRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void customerFound() throws Exception {
        given(this.repository.findByFirstName("test")).willReturn( new Customer(UUID.fromString("d2624fe5-c4f2-4408-87f7-dfc353d950f6"), "test", "test"));

        mvc.perform(MockMvcRequestBuilders.get("/customer/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"d2624fe5-c4f2-4408-87f7-dfc353d950f6\",\"firstName\":\"test\",\"lastName\":\"test\"}"));
    }

    @Test
    public void noCustomerFound() throws Exception {
        given(this.repository.findByFirstName("test")).willReturn( null);

        mvc.perform(MockMvcRequestBuilders.get("/customer/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}