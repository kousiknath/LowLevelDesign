package com.lld.amazon.model;

import java.util.List;

public class ProductVariety {
    private String titleSuffix;
    private Pricing pricing;
    private ProductVarietyMetadata metadata;
    private List<Media> mediaList;
    // Item level item delivery options only for display
    // It's not order level information
    // This delivery data will be computed based on user's
    // default delivery option
    private List<DeliveryOption> deliveryOptions;
    private Seller seller;

    public ProductVariety(Pricing pricing, ProductVarietyMetadata metadata, Seller seller) {
        this.pricing = pricing;
        this.metadata = metadata;
        this.seller = seller;
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
    }
}
