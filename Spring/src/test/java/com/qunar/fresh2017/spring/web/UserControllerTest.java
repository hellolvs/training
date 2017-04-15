package com.qunar.fresh2017.spring.web;


import com.qunar.fresh2017.spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(locations = {
        "classpath:spring/mockito_service.xml",
        "classpath:spring/mvc/mvc-vm.xml",
        "classpath:spring/mvc/mvc.xml"})
@WebAppConfiguration
public class UserControllerTest extends AbstractJUnit4SpringContextTests{

    private static final Logger LOG = LoggerFactory
            .getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UserService userService;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAdd() throws Exception {
        LOG.info("run testAdd");
        ResultActions ac = mockMvc.perform(post("/user/add").param("id", "1").param("name", "test").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().is3xxRedirection());
        LOG.info("end");
    }

    @Test
    public void testAddPage() throws Exception {
        LOG.info("run testAddPage");
        ResultActions ac = mockMvc.perform(get("/user/add").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().isOk());
        LOG.info("end");
    }

    @Test
    public void testList() throws Exception {
        LOG.info("run testList");
        ResultActions ac = mockMvc.perform(get("/user/list/1").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().isOk());
        LOG.info("end");
    }

    @Test
    public void testGet() throws Exception {
        LOG.info("run testGet");
        ResultActions ac = mockMvc.perform(get("/user/1").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().isOk());
        LOG.info("end");
    }

    @Test
    public void testUpdate() throws Exception {
        LOG.info("run testUpdate");
        ResultActions ac = mockMvc.perform(post("/user/update/1").param("name","test").param("gender","true").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().is3xxRedirection());
        LOG.info("end");
    }

    @Test
    public void testUpdatePage() throws Exception {
        LOG.info("run testUpdatePage");
        ResultActions ac = mockMvc.perform(get("/user/update/1").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().isOk());
        LOG.info("end");
    }

    @Test
    public void testDelete() throws Exception {
        LOG.info("run testDelete");
        ResultActions ac = mockMvc.perform(post("/user/delete/1").param("isDelete","true").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().is3xxRedirection());
        LOG.info("end");
    }

    @Test
    public void testDeletePage() throws Exception {
        LOG.info("run testDeletePage");
        ResultActions ac = mockMvc.perform(get("/user/delete/1").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().isOk());
        LOG.info("end");
    }
}