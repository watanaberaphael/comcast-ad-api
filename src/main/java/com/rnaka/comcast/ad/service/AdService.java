package com.rnaka.comcast.ad.service;

import com.rnaka.comcast.ad.model.Ad;

import java.util.List;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public interface AdService {

    void create(Ad ad);

    Ad find(String partner_id);

    List<Ad> list();

}
