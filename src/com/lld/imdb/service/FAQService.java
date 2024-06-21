package com.lld.imdb.service;

import com.lld.imdb.model.FAQ;
import com.lld.imdb.model.Show;

public interface FAQService {
    Show addFAQ(Show show, FAQ faq);
}
