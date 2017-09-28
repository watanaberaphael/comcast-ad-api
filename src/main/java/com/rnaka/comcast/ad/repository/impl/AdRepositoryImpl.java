package com.rnaka.comcast.ad.repository.impl;

import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.repository.AdRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
@Repository
public class AdRepositoryImpl implements AdRepository {

    public static List<Ad> ads = new ArrayList<>();


    @Override
    public void create(Ad ad) {
        ads.add(ad);
    }

    @Override
    public boolean existsActive(String partner_id) {

        List<Ad> adResult = ads
                .stream()
                .filter(ad -> ad.getPartner_id().equals(partner_id)
                                && (ad.getCreated_on().plusSeconds(ad.getDuration()).compareTo(LocalDateTime.now()) > 0))
                .collect(Collectors.toList());

        return adResult.size() > 0;
    }

    @Override
    public Ad find(String partner_id) {
        List<Ad> adResult = ads
                .stream()
                .filter(ad -> ad.getPartner_id().equals(partner_id)
                        && (ad.getCreated_on().plusSeconds(ad.getDuration()).compareTo(LocalDateTime.now()) > 0))
                .collect(Collectors.toList());

        return adResult == null || adResult.size() == 0 ? null : adResult.get(0);
    }

    @Override
    public List<Ad> list() {
        return ads;
    }
}
