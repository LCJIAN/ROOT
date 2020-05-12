package com.lcjian.spunsugar.entity;

// Generated 2016-1-15 21:54:05 by Hibernate Tools 4.0.0

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * Movie generated by hbm2java
 */
public class Movie implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Expose
    private Integer id;
    @Expose
    private String title;
    @Expose
    private String overview;
    @Expose
    private String poster;
    @Expose
    private Float voteAverage;
    @Expose
    private Float popularity;
    @Expose
    private Date releaseDate;
    @Expose
    private String imdbId;
    @Expose
    private String doubanId;
    @Expose
    private String tmdbId;
    @Expose
    private Timestamp createTime;
    
    private String crawlerId;
    private Set<MovieVideo> movieVideos = new HashSet<MovieVideo>(0);
    private Set<MovieGenre> movieGenres = new HashSet<MovieGenre>(0);
    private Set<MovieProductionCountry> movieProductionCountries = new HashSet<MovieProductionCountry>(0);

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, String overview, String poster,
            Float voteAverage, Date releaseDate, String imdbId,
            String doubanId, Set<MovieVideo> movieVideos) {
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.imdbId = imdbId;
        this.doubanId = doubanId;
        this.movieVideos = movieVideos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Float getVoteAverage() {
        return this.voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImdbId() {
        return this.imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getDoubanId() {
        return this.doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }
    
    public String getCrawlerId() {
        return crawlerId;
    }

    public void setCrawlerId(String crawlerId) {
        this.crawlerId = crawlerId;
    }

    public Set<MovieVideo> getMovieVideos() {
        return this.movieVideos;
    }

    public void setMovieVideos(Set<MovieVideo> movieVideos) {
        this.movieVideos = movieVideos;
    }

    public Set<MovieGenre> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<MovieGenre> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Set<MovieProductionCountry> getMovieProductionCountries() {
        return movieProductionCountries;
    }

    public void setMovieProductionCountries(
            Set<MovieProductionCountry> movieProductionCountries) {
        this.movieProductionCountries = movieProductionCountries;
    }

}
