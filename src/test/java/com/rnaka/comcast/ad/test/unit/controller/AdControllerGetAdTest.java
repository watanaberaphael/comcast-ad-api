package com.rnaka.comcast.ad.test.unit.controller;

import com.rnaka.comcast.ad.controller.AdController;
import com.rnaka.comcast.ad.exception.ResultNotFoundException;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.service.impl.AdServiceImpl;
import com.rnaka.comcast.ad.test.utils.MockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 *
 * Tests for the service GET /ad/{partner_id}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AdController.class)
public class AdControllerGetAdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdServiceImpl adService;

    /**
     * Validates when the service returns an add
     */
    @Test
    public void shouldGet() throws Exception{
        Ad ad = MockUtils.getAdMock();

        given(adService.find(Mockito.anyString())).willReturn(ad);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/ad/"+ad.getPartner_id())
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partner_id", is(ad.getPartner_id())))
                .andExpect(jsonPath("$.duration", is(ad.getDuration())))
                .andExpect(jsonPath("$.ad_content", is(ad.getAd_content())));

    }

    /**
     * Validates when there is no active ad
     */
    @Test
    public void shouldNotGet() throws Exception{
        Ad ad = MockUtils.getAdMock();

        given(adService.find(Mockito.anyString())).willReturn(ad);

        doThrow(new ResultNotFoundException("test")).when(adService).find(Mockito.anyString());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/ad/"+ad.getPartner_id())
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("test")));

    }
}
