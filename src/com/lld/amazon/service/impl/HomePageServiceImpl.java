package com.lld.amazon.service.impl;

import com.lld.amazon.model.*;
import com.lld.amazon.service.HomePageService;

import java.util.Collections;
import java.util.List;

public class HomePageServiceImpl implements HomePageService {
    @Override
    public List<Catalogue> chooseCatalogue(HomePage homePage) {
        return homePage.getCatalogues();
    }

    @Override
    public List<Product> chooseProducts(List<Catalogue> catalogues) {
        Catalogue catalogue =  catalogues.get(0);
        if (catalogue.getProducts().isEmpty()) {
            return Collections.emptyList();
        }

        return catalogue.getProducts();
    }
}
