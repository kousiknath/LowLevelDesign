package com.lld.imdb.service.impl;

import com.lld.imdb.model.FAQ;
import com.lld.imdb.model.Show;
import com.lld.imdb.repository.ShowRepository;
import com.lld.imdb.service.FAQService;

public class FAQServiceImpl implements FAQService {
    private ShowRepository repository;
    public FAQServiceImpl(ShowRepository repository) {
        this.repository = repository;
    }

    @Override
    public Show addFAQ(Show show, FAQ faq) {
        return repository.addFAQ(show, faq);
    }
}
