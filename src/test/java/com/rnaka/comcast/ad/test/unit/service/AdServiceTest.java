package com.rnaka.comcast.ad.test.unit.service;

import com.rnaka.comcast.ad.enumeration.MessageEnum;
import com.rnaka.comcast.ad.exception.BusinessValidationException;
import com.rnaka.comcast.ad.exception.ResultNotFoundException;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.repository.impl.AdRepositoryImpl;
import com.rnaka.comcast.ad.service.impl.AdServiceImpl;
import com.rnaka.comcast.ad.test.utils.MockUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
@RunWith(SpringRunner.class)
public class AdServiceTest {

    @Mock
    private AdRepositoryImpl adRepository;

    @InjectMocks
    private AdServiceImpl adService;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * Tested method: create
     * Validates if it creates the ad
     */
    @Test
    public void shouldCreate() {
        when(adRepository.existsActive(Mockito.anyString())).thenReturn(false);

        Ad ad = MockUtils.getAdMock();

        try {
            adService.create(ad);
            assert(true);
        } catch (Exception ex) {
            assert(false);
        }
    }

    /**
     * Tested method: create
     * Validates if there is already an active ad for the partner
     */
    @Test
    public void shouldNotCreate() {
        when(adRepository.existsActive(Mockito.anyString())).thenReturn(true);

        Ad ad = MockUtils.getAdMock();

        try {
            adService.create(ad);
            adService.create(ad);
            assert(false);
        } catch (BusinessValidationException ex) {
            assertEquals(MessageEnum.B1000.getMessage(), ex.getMessage());
            assertEquals(MessageEnum.B1000.getId(), ex.getId());
        }
    }


    /**
     * Tested method: find
     * Validates when the repository returns the ad
     */
    @Test
    public void shouldFind() {
        Ad ad = MockUtils.getAdMock();
        when(adRepository.find(Mockito.anyString())).thenReturn(ad);

        Ad adResult = adService.find("12345");

        assertNotNull(adResult);
        assertEquals(ad, adResult);
    }

    /**
     * Tested method: find
     * Validates when the repository returns null
     */
    @Test
    public void shouldNotFind() {
        when(adRepository.find(Mockito.anyString())).thenReturn(null);

        try {
            adService.find("12345");
            assert(false);
        } catch (ResultNotFoundException ex) {
            assertEquals(MessageEnum.B1001.getMessage(), ex.getMessage());
        }
    }


    /**
     * Tested method: list
     * Validates if the repository returns a list
     */
    @Test
    public void shouldList() {
        List<Ad> adsMock = MockUtils.getListAdMock();
        when(adRepository.list()).thenReturn(adsMock);

        List<Ad> ads = adService.list();

        assertNotNull(ads);
        assertEquals(adsMock.size(), ads.size());
    }

    /**
     * Tested method: list
     * Validates if the repository returns a list
     */
    @Test
    public void shouldListNull() {
        when(adRepository.list()).thenReturn(null);

        List<Ad> ads = adService.list();

        assertNull(ads);
    }
}
