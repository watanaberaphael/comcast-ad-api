package com.rnaka.comcast.ad.test.unit.controller;

import com.rnaka.comcast.ad.controller.AdController;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.service.impl.AdServiceImpl;
import com.rnaka.comcast.ad.test.utils.MockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 *
 * Tests for the service GET /ad
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AdController.class)
public class AdControllerListAdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdServiceImpl adService;


    /**
     * Validates when the service returns a list
     * @throws Exception
     */
    @Test
    public void shouldList() throws Exception {
        List<Ad> ads = MockUtils.getListAdMock();

        given(adService.list()).willReturn(ads);

        this.mockMvc.perform(get("/ad").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(ads.size())));
    }

    /**
     * Validates when the service returns null
     * @throws Exception
     */
    @Test
    public void shouldNotNullList() throws Exception {
        given(adService.list()).willReturn(null);

        this.mockMvc.perform(get("/ad").contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    /**
     * Validtes when the service returns an empty list
     * @throws Exception
     */
    @Test
    public void shouldEmptyList() throws Exception {
        given(adService.list()).willReturn(new ArrayList<>());

        this.mockMvc.perform(get("/ad").contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}
