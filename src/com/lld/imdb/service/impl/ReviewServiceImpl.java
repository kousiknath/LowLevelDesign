package com.lld.imdb.service.impl;

import com.lld.imdb.model.Show;
import com.lld.imdb.model.ShowDynamicDetails;
import com.lld.imdb.model.UserReview;
import com.lld.imdb.repository.ShowRepository;
import com.lld.imdb.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
    private ShowRepository showRepository;

    public ReviewServiceImpl(ShowRepository repository) {
        this.showRepository = repository;
    }

    @Override
    public ShowDynamicDetails addReview(Show show, UserReview review) {
        Show s = showRepository.addReview(show, review);
        return s.getDynamicDetails();
    }
}
