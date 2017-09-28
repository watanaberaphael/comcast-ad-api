package com.rnaka.comcast.ad.controller;

import com.rnaka.comcast.ad.controller.dto.MessageDTO;
import com.rnaka.comcast.ad.enumeration.MessageEnum;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
@RestController
@RequestMapping("ad")
public class AdController {

    @Autowired
    private AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Ad ad) {
        adService.create(ad);

        return new ResponseEntity(new MessageDTO(MessageEnum.V3000.getMessage(), MessageEnum.V3000.getId()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{partner_id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable(value = "partner_id") String partner_id) {
        Ad ad = adService.find(partner_id);

        return new ResponseEntity(ad, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity list() {
        List<Ad> ads = adService.list();

        return new ResponseEntity(ads, ads == null || ads.size() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
