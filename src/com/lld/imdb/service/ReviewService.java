package com.lld.imdb.service;

import com.lld.imdb.model.Show;
import com.lld.imdb.model.ShowDynamicDetails;
import com.lld.imdb.model.UserReview;

public interface ReviewService {
    ShowDynamicDetails addReview(Show show, UserReview review);
}
