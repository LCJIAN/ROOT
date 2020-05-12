package com.lcjian.spunsugar.entity;

// Generated 2015-10-25 11:06:49 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * HentaiAnimeEpisode generated by hbm2java
 */
public class HentaiAnimeEpisode implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Expose
    private String id;
    private HentaiAnimeSeries hentaiAnimeSeries;
    private HentaiAnimeMaker hentaiAnimeMaker;
    @Expose
    private String name;
    @Expose
    private String overview;
    @Expose
    private String posterSmall;
    @Expose
    private String posterMedium;
    @Expose
    private String posterLarge;
    @Expose
    private String sampleImage;
    @Expose
    private Integer duration;
    @Expose
    private Float rating;
    @Expose
    private Date releaseDate;
    @Expose
    private Date deliveryStartDate;
    private Set<HentaiAnimeGenre> hentaiAnimeGenres = new HashSet<HentaiAnimeGenre>(0);

    public HentaiAnimeEpisode() {
    }

    public HentaiAnimeEpisode(String id) {
        this.id = id;
    }

    public HentaiAnimeEpisode(String id, HentaiAnimeSeries hentaiAnimeSeries,
            HentaiAnimeMaker hentaiAnimeMaker, String name, String overview,
            String posterSmall, String posterMedium, String posterLarge,
            String sampleImage, Integer duration, Float rating,
            Date releaseDate, Date deliveryStartDate,
            Set<HentaiAnimeGenre> hentaiAnimeGenres) {
        this.id = id;
        this.hentaiAnimeSeries = hentaiAnimeSeries;
        this.hentaiAnimeMaker = hentaiAnimeMaker;
        this.name = name;
        this.overview = overview;
        this.posterSmall = posterSmall;
        this.posterMedium = posterMedium;
        this.posterLarge = posterLarge;
        this.sampleImage = sampleImage;
        this.duration = duration;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.deliveryStartDate = deliveryStartDate;
        this.hentaiAnimeGenres = hentaiAnimeGenres;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HentaiAnimeSeries getHentaiAnimeSeries() {
        return this.hentaiAnimeSeries;
    }

    public void setHentaiAnimeSeries(HentaiAnimeSeries hentaiAnimeSeries) {
        this.hentaiAnimeSeries = hentaiAnimeSeries;
    }

    public HentaiAnimeMaker getHentaiAnimeMaker() {
        return this.hentaiAnimeMaker;
    }

    public void setHentaiAnimeMaker(HentaiAnimeMaker hentaiAnimeMaker) {
        this.hentaiAnimeMaker = hentaiAnimeMaker;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterSmall() {
        return this.posterSmall;
    }

    public void setPosterSmall(String posterSmall) {
        this.posterSmall = posterSmall;
    }

    public String getPosterMedium() {
        return this.posterMedium;
    }

    public void setPosterMedium(String posterMedium) {
        this.posterMedium = posterMedium;
    }

    public String getPosterLarge() {
        return this.posterLarge;
    }

    public void setPosterLarge(String posterLarge) {
        this.posterLarge = posterLarge;
    }

    public String getSampleImage() {
        return this.sampleImage;
    }

    public void setSampleImage(String sampleImage) {
        this.sampleImage = sampleImage;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getDeliveryStartDate() {
        return this.deliveryStartDate;
    }

    public void setDeliveryStartDate(Date deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public Set<HentaiAnimeGenre> getHentaiAnimeGenres() {
        return this.hentaiAnimeGenres;
    }

    public void setHentaiAnimeGenres(Set<HentaiAnimeGenre> hentaiAnimeGenres) {
        this.hentaiAnimeGenres = hentaiAnimeGenres;
    }

}
