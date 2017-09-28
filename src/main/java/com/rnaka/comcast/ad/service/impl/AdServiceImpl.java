package com.rnaka.comcast.ad.service.impl;

import com.rnaka.comcast.ad.enumeration.MessageEnum;
import com.rnaka.comcast.ad.exception.BusinessValidationException;
import com.rnaka.comcast.ad.exception.ResultNotFoundException;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.repository.AdRepository;
import com.rnaka.comcast.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public void create(Ad ad) {

        //* Only one active campaign can exist for a given partner.
        if (adRepository.existsActive(ad.getPartner_id())) {
            throw new BusinessValidationException(MessageEnum.B1000);
        }

        ad.setCreated_on(LocalDateTime.now());

        adRepository.create(ad);
    }

    @Override
    public Ad find(String partner_id) {
        Ad ad = adRepository.find(partner_id);

        if (ad == null) throw new ResultNotFoundException(MessageEnum.B1001);

        return ad;
    }

    @Override
    public List<Ad> list() {
        return adRepository.list();
    }

}
