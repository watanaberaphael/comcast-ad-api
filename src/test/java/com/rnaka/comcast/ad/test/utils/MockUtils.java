package com.rnaka.comcast.ad.test.utils;

import com.rnaka.comcast.ad.model.Ad;
import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naka on 9/27/17
 * watanabe.raphael@gmail.com
 */
public class MockUtils {

    public static Ad getAdMock() {
        Ad ad = new Ad();
        ad.setPartner_id(RandomStringUtils.random(10, true, true));
        ad.setDuration(3600);
        ad.setAd_content(RandomStringUtils.random(100, true, true));
        ad.setCreated_on(LocalDateTime.now());
        return ad;
    }

    public static List<Ad> getListAdMock() {
        List<Ad> ads = new ArrayList<>();

        ads.add(getAdMock());
        ads.add(getAdMock());
        ads.add(getAdMock());

        return ads;
    }


}
