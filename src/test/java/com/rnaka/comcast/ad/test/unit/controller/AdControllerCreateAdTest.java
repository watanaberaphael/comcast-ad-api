package com.rnaka.comcast.ad.test.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rnaka.comcast.ad.controller.AdController;
import com.rnaka.comcast.ad.exception.BusinessValidationException;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.service.impl.AdServiceImpl;
import com.rnaka.comcast.ad.test.utils.MockUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 *
 * Tests for the service POST /ad
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AdController.class)
public class AdControllerCreateAdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdServiceImpl adService;

    private JacksonTester<Ad> jsonTester;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
    }

    /**
     * Validates if creates
     * @throws Exception
     */
    @Test
    public void shouldInsert() throws Exception {
        Ad ad = MockUtils.getAdMock();

        String adJson = jsonTester.write(ad).getJson();

        this.mockMvc.perform(post("/ad").content(adJson).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    /**
     * Validates when there is already an active ad
     * @throws Exception
     */
    @Test
    public void shouldNotInsert() throws Exception {
        Ad ad = MockUtils.getAdMock();

        doThrow(new BusinessValidationException("There is already an active campaign for the partner"))
                .when(adService)
                .create(Mockito.any());

        String adJson = jsonTester.write(ad).getJson();

        this.mockMvc.perform(post("/ad").content(adJson).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("There is already an active campaign for the partner")));
    }

}
