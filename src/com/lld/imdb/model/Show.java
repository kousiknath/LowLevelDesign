package com.lld.imdb.model;

import com.lld.imdb.constant.ShowType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Show {
    private ShowType showType;
    private ShowStaticDetails staticDetails;
    private ShowDynamicDetails dynamicDetails;
    // `episodes` is valid for TV shows. For movies, it would be empty.
    private List<Show> episodes;

    private Show() {
        this.episodes = new ArrayList<>();
    }

    public Show(ShowType showType) {
        this();
        this.showType = showType;
    }

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    public ShowStaticDetails getStaticDetails() {
        return staticDetails;
    }

    public void setStaticDetails(ShowStaticDetails staticDetails) {
        this.staticDetails = staticDetails;
    }

    public ShowDynamicDetails getDynamicDetails() {
        return dynamicDetails;
    }

    public void setDynamicDetails(ShowDynamicDetails dynamicDetails) {
        this.dynamicDetails = dynamicDetails;
    }

    public List<Show> getEpisodes() {
        return episodes;
    }
    public void addEpisode(Show show) {
        this.episodes.add(show);
    }

    @Override
    public String toString() {
        return staticDetails.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show show)) return false;
        return getShowType() == show.getShowType() && Objects.equals(getStaticDetails(), show.getStaticDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShowType(), getStaticDetails());
    }
}
