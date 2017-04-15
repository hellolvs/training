package com.qunar.fresh2017.spring.web;

import com.qunar.fresh2017.spring.support.DateFormatter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DateControllerTest {

    private static final Logger LOG = LoggerFactory
            .getLogger(DateControllerTest.class);

    private MockMvc mockMvc;

    private DateController dateController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dateController = new DateController();
        dateController.setDateFormatter(new DateFormatter());
        mockMvc = MockMvcBuilders.standaloneSetup(dateController).build();
    }
    @Test
    public void testParse() throws Exception {
        LOG.info("run testParse");
        ResultActions ac = mockMvc.perform(get("/binding_date.do?date=2004-04-29").accept(MediaType.ALL));
        ac.andDo(print());
        ac.andExpect(status().isOk());
        LOG.info("end");
    }
}