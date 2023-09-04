package com.lld.bookmyshow.service.impl;

import com.lld.bookmyshow.model.Show;
import com.lld.bookmyshow.repository.ShowRepository;
import com.lld.bookmyshow.service.ShowService;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class ShowServiceImpl implements ShowService {
    private ShowRepository showRepository;

    public ShowServiceImpl() {
        this.showRepository = new ShowRepository();
    }

    @Override
    public void addShow(Show show) {
        Objects.requireNonNull(show);
        this.showRepository.addShow(show);
    }

    @Override
    public List<Show> getShows(Instant start, Instant end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        long startMillis = start.toEpochMilli();
        long endMillis = end.toEpochMilli();

        return showRepository.getShows(startMillis, endMillis);
    }
}
