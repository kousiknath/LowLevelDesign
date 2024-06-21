package com.lld.imdb.repository.impl;

import com.lld.imdb.model.FAQ;
import com.lld.imdb.model.Show;
import com.lld.imdb.model.ShowDynamicDetails;
import com.lld.imdb.model.UserReview;
import com.lld.imdb.repository.ShowRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRepositoryImpl implements ShowRepository {
    private Map<String, Show> shows;
    private Map<Show, ShowDynamicDetails> dynamicDetails;
    private Map<Show, List<FAQ>> showFAQs;

    public ShowRepositoryImpl() {
        this.shows = new HashMap<>();
        this.dynamicDetails = new HashMap<>();
        this.showFAQs = new HashMap<>();
    }
    @Override
    public Show saveShow(Show show) {
        this.shows.put(show.getStaticDetails().getTitle(), show);
        return show;
    }

    @Override
    public List<Show> searchShows(String title) {
        // Implements very basic exact search based on title
        for (String t: shows.keySet()) {
            if (t.equals(title)) {
                Show show = shows.get(t);
                show.setDynamicDetails(dynamicDetails.get(show)); // This modifies the show in the `shows` map
                return List.of(show);
            }
        }

        return null;
    }

    @Override
    public Show addReview(Show show, UserReview review) {
        // calculation should happen
        ShowDynamicDetails currentDetails = dynamicDetails.get(show);
        if (currentDetails == null) {
            ShowDynamicDetails details = new ShowDynamicDetails();
            details.setRating(review.getRating());
            details.addReview(review);
            dynamicDetails.put(show, details);

            return show;
        }

        int currentTotalReviews = currentDetails.getReviews().size();
        currentDetails.addReview(review);
        currentDetails.setRating((currentDetails.getRating() * currentTotalReviews
                + review.getRating()) / (currentTotalReviews + 1));

        dynamicDetails.put(show, currentDetails);
        show.setDynamicDetails(dynamicDetails.get(show));

        return show;
    }

    @Override
    public Show addFAQ(Show show, FAQ faq) {
        this.showFAQs.putIfAbsent(show, new ArrayList<>());
        this.showFAQs.get(show).add(faq);
        Show showInStorage = this.shows.get(show.getStaticDetails().getTitle());
        showInStorage.getDynamicDetails().setFaqs(this.showFAQs.get(show));
        return showInStorage;
    }

    @Override
    public Show addEpisode(Show show, Show episode) {
        Show showInStorage = this.shows.get(show.getStaticDetails().getTitle());
        showInStorage.addEpisode(episode);
        return showInStorage;
    }
}
