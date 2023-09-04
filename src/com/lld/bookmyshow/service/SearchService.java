package com.lld.bookmyshow.service;

import com.lld.bookmyshow.model.Show;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface SearchService {
    List<Show> search(String namePrefix, Instant date); // For now, we are ignoring user location and assuming, all the theatres
    // we are interested in are in the same region / city as the user
}
