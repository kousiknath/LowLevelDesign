package com.lld.imdb.service.impl;

import com.lld.imdb.constant.ShowType;
import com.lld.imdb.exception.ShowException;
import com.lld.imdb.model.AwardFunction;
import com.lld.imdb.model.Show;
import com.lld.imdb.repository.ShowRepository;
import com.lld.imdb.service.ShowService;

import java.util.List;

public class ShowServiceImpl implements ShowService {
    private ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository repository) {
        this.showRepository = repository;
    }

    @Override
    public Show addShow(Show show) {
        // basic validation
        return showRepository.saveShow(show);
    }

    @Override
    public List<Show> searchShows(String title) {
        // basic validation
        return showRepository.searchShows(title);
    }

    @Override
    public List<AwardFunction> searchFunctions(String title) {
        return null;
    }

    @Override
    public Show addEpisode(Show show, Show episode) throws ShowException {
        if (show.getShowType() != ShowType.TV_SERIES) {
            throw new ShowException("The show must be a TV series to add an episode");
        }

        return this.showRepository.addEpisode(show, episode);
    }
}
