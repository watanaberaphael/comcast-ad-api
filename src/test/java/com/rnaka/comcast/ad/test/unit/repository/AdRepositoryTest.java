package com.rnaka.comcast.ad.test.unit.repository;

import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.repository.impl.AdRepositoryImpl;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public class AdRepositoryTest {

    private AdRepositoryImpl adRepository = new AdRepositoryImpl();

    /**
     * Tested method: create
     * Add an ad and check if it is in the List
     */
    @Test
    public void shouldInsert() {
        Ad ad = new Ad();
        ad.setPartner_id("12345");
        ad.setDuration(2);
        ad.setAd_content("Ad description");
        ad.setCreated_on(LocalDateTime.now());

        try {
            adRepository.create(ad);
            assert(true);
        } catch (Exception ex) {
            assert(false);
        }
    }

    /**
     * Tested method: create
     * Add an ad and check if it is in the List
     */
    @Test
    public void shouldShowIsActive() {
        Ad ad = new Ad();
        ad.setPartner_id("98765");
        ad.setDuration(2);
        ad.setAd_content("Ad description");
        ad.setCreated_on(LocalDateTime.now());

        adRepository.create(ad);

        if (adRepository.existsActive(ad.getPartner_id())) {
            assert(true);
        } else {
            assert(false);
        }
    }

    /**
     * Tested method: existsActive
     * Add an ad with 2 seconds of duration, it should show as inactive
     * @throws InterruptedException
     */
    @Test
    public void shouldNotShowIsActive() throws InterruptedException {
        AdRepositoryImpl.ads = new ArrayList<>();

        Ad ad = new Ad();
        ad.setPartner_id("54321");
        ad.setDuration(2);
        ad.setAd_content("Ad description");
        ad.setCreated_on(LocalDateTime.now().minusSeconds(2));

        adRepository.create(ad);

        if (adRepository.existsActive(ad.getPartner_id())) {
            assert(false);
        } else {
            assert(true);
        }
    }

    /**
     * Tested method: list
     * Add two ads and check it they are in the list
     */
    @Test
    public void shouldList() {
        AdRepositoryImpl.ads = new ArrayList<>();

        Ad ad1 = new Ad();
        ad1.setPartner_id("12345");
        ad1.setDuration(2);
        ad1.setAd_content("Ad description");
        ad1.setCreated_on(LocalDateTime.now());

        Ad ad2 = new Ad();
        ad2.setPartner_id("54321");
        ad2.setDuration(2);
        ad2.setAd_content("Ad description");
        ad2.setCreated_on(LocalDateTime.now());

        adRepository.create(ad1);
        adRepository.create(ad2);

        List<Ad> ads = adRepository.list();

        assert(ads.contains(ad1));
        assert(ads.contains(ad2));
    }

    /**
     * Tested method: list
     * Add two ads and check it they are in the list
     */
    @Test
    public void shouldListNull() {
        AdRepositoryImpl.ads = null;

        List<Ad> ads = adRepository.list();

        assertNull(ads);

        AdRepositoryImpl.ads = new ArrayList<>();
    }

    /**
     * Tested method: list
     * Add two ads and check it they are in the list
     */
    @Test
    public void shouldListEmpty() {
        AdRepositoryImpl.ads = new ArrayList<>();

        List<Ad> ads = adRepository.list();

        assertNotNull(ads);
        assertEquals(0, ads.size());
    }

}
