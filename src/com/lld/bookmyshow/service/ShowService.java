package com.lld.bookmyshow.service;

import com.lld.bookmyshow.model.Show;

import java.time.Instant;
import java.util.List;

public interface ShowService {
    void addShow(Show show);
    List<Show> getShows(Instant start, Instant end);
}
