package com.lld.amazon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalogue {
    private List<Media> mediaList;
    private List<Product> products;

    public Catalogue() {
        this.mediaList = new ArrayList<>();
        products = new ArrayList<>();
    }

    public void addMedia(Media media) {
        this.mediaList.add(media);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }
}
