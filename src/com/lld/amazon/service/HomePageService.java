package com.lld.amazon.service;

import com.lld.amazon.model.Catalogue;
import com.lld.amazon.model.HomePage;
import com.lld.amazon.model.Product;

import java.util.List;

public interface HomePageService {
    List<Catalogue> chooseCatalogue(HomePage homePage);
    // It randomly chooses a product for our application.
    // However, in reality, a user can choose any number of
    // products
    List<Product> chooseProducts(List<Catalogue> catalogue);
}
