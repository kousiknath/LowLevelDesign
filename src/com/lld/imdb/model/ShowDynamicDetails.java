package com.lld.imdb.model;

import java.util.ArrayList;
import java.util.List;

public class ShowDynamicDetails {
    private double rating;
    private double currentPopularityIndex;
    private List<UserReview> reviews;
    private List<FAQ> faqs;

    public ShowDynamicDetails() {
        this.reviews = new ArrayList<>();
        this.faqs = new ArrayList<>();
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getCurrentPopularityIndex() {
        return currentPopularityIndex;
    }

    public void setCurrentPopularityIndex(double currentPopularityIndex) {
        this.currentPopularityIndex = currentPopularityIndex;
    }

    public List<UserReview> getReviews() {
        return reviews;
    }

    public void addReview(UserReview review) {
        this.reviews.add(review);
    }

    public List<FAQ> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<FAQ> faqs) {
        this.faqs = faqs;
    }
}
