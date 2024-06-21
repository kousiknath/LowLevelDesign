package com.lld.imdb.model;

import com.lld.imdb.constant.Genre;
import com.lld.imdb.constant.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowStaticDetails {
    private String title;
    private String description;
    private List<CastAndCrew> castAndCrewList;
    private String releaseDate;
    private int runtime;
    private String plot;
    private List<Language> languages;
    private String originCountry;
    private List<String> productionCompanies;
    private String soundMix;
    private String aspectRatio;
    private List<Genre> genres;
    private List<String> tags;
    // Photos and videos to be shown on the show details page
    private EmbeddedMedia embeddedMedia;

    public ShowStaticDetails() {
        this.castAndCrewList = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.productionCompanies = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CastAndCrew> getCastAndCrewList() {
        return castAndCrewList;
    }

    public void setCastAndCrewList(List<CastAndCrew> castAndCrewList) {
        this.castAndCrewList = castAndCrewList;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getSoundMix() {
        return soundMix;
    }

    public void setSoundMix(String soundMix) {
        this.soundMix = soundMix;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowStaticDetails that)) return false;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getReleaseDate(), that.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getReleaseDate());
    }
}
