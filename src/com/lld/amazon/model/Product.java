package com.lld.amazon.model;

import java.util.*;

public class Product {
    private final String productId;
    private String title;
    private String description;
    private List<KV> topLevelMetadata;
    private List<KV> promises; // The value can be html to render directly on screen
    private List<ProductOffer> offers;
//    private List<ProtectionPlan> protectionPlans; // Specifically for electronics
    private List<ProductVariety> varieties;
    private ProductReviewsAndRating reviewsAndRating;

    public Product(String title, String description, List<ProductVariety> varieties) {
        this.productId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.varieties = varieties;
        this.topLevelMetadata = new ArrayList<>();
        this.promises = new ArrayList<>();
        this.reviewsAndRating = new ProductReviewsAndRating();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<ProductVariety> getVarieties() {
        return Collections.unmodifiableList(varieties);
    }

    public void addMetadata(String key, String val) {
        this.topLevelMetadata.add(new KV(key, val));
    }

    public void addPromise(String key, String val) {
        this.promises.add(new KV(key, val));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Product other = (Product) obj;
        return this.productId.equals(other.productId);
    }
}
