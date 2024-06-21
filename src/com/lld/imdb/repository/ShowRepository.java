package com.lld.imdb.repository;

import com.lld.imdb.model.FAQ;
import com.lld.imdb.model.Show;
import com.lld.imdb.model.UserReview;

import java.util.List;

public interface ShowRepository {
    Show saveShow(Show show);
    List<Show> searchShows(String title);
    Show addReview(Show show, UserReview review);
    Show addFAQ(Show show, FAQ faq);
    Show addEpisode(Show show, Show episode);
}
