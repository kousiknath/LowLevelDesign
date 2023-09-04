package com.lld.bookmyshow.model;

import java.util.List;

/**
 * Basic movie details
 */

public class Movie {
    private String name;
    private String description;
    private String releaseDate;
    private List<Cast> cast;
    private List<String> mediaLinks;

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public List<String> getMediaLinks() {
        return mediaLinks;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
