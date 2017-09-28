package com.rnaka.comcast.ad.repository;

import com.rnaka.comcast.ad.model.Ad;

import java.util.List;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public interface AdRepository {

    void create(Ad ad);
    boolean existsActive(String partner_id);
    Ad find(String partner_id);
    List<Ad> list();
}
