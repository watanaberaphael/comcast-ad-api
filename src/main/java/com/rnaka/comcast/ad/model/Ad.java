package com.rnaka.comcast.ad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public class Ad {

    private String partner_id;

    private Integer duration;

    private String ad_content;

    @JsonIgnore
    private LocalDateTime created_on;

    public Ad() {
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAd_content() {
        return ad_content;
    }

    public void setAd_content(String ad_content) {
        this.ad_content = ad_content;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime inclusion) {
        this.created_on = inclusion;
    }

    @Override
    public boolean equals(Object param) {

        if (param == this) return true;
        if (!(param instanceof Ad)) {
            return false;
        }

        Ad ad = (Ad) param;

        return Objects.equals(this.partner_id, ad.partner_id) &&
                this.duration == ad.duration &&
                Objects.equals(this.ad_content, ad.ad_content) &&
                Objects.equals(this.created_on, ad.created_on);
    }
}
