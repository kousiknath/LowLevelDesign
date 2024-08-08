package com.lld.amazon.model;

public class CartOrOrderItem {
    private Product product;
    // A user chooses a specific variety of a product in her
    // cart / order from the multiple varieties offered by the
    // product
    private ProductVariety chosenVariety;
    public CartOrOrderItem(Product product, ProductVariety variety) {
        this.product = product;
        this.chosenVariety = variety;
    }

    public Product getProduct() {
        return product;
    }

    public ProductVariety getChosenVariety() {
        return chosenVariety;
    }
}
