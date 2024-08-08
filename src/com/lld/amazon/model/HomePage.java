package com.lld.amazon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    private List<Catalogue> catalogues;

    public HomePage() {
        this.catalogues = new ArrayList<>();
    }

    public void addCatalogue(Catalogue catalogue) {
        this.catalogues.add(catalogue);
    }

    public List<Catalogue> getCatalogues() {
        return Collections.unmodifiableList(this.catalogues);
    }
}
