package com.gmail.kharchenko55.vlad.service.search;

import com.gmail.kharchenko55.vlad.model.search.SearchHistory;

import java.util.List;

public interface SearchHistoryService {
    List<SearchHistory> getAllHistory();
    List<SearchHistory> getAllByEmail(String email);
    SearchHistory save(SearchHistory searchHistory);
}
