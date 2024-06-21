package com.lld.imdb.service;

import com.lld.imdb.exception.ShowException;
import com.lld.imdb.model.AwardFunction;
import com.lld.imdb.model.Show;

import java.util.List;

public interface ShowService {
    Show addShow(Show show);
    List<Show> searchShows(String title);
    List<AwardFunction> searchFunctions(String title);
    Show addEpisode(Show show, Show episode) throws ShowException;
}
