package com.lld.amazon.model;

import com.lld.amazon.constant.ProductOfferType;

import java.util.List;

/*
    Bank Offer
    Partner Offer
 */
public class ProductOffer {
    private ProductOfferType productOfferType;
    private List<Offer> offers;
}
