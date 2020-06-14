package com.gmail.kharchenko55.vlad.service.search;

import com.gmail.kharchenko55.vlad.dao.SearchHistoryRepository;
import com.gmail.kharchenko55.vlad.model.search.SearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchHistoryImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryRepository historyRepository;

    @Override
    public SearchHistory save(SearchHistory searchHistory) {
        return historyRepository.save(searchHistory);
    }

    @Override
    public List<SearchHistory> getAllHistory() {
        List<SearchHistory> history = new ArrayList<>();
        historyRepository.findAll().forEach(history::add);
        return history;
    }

    @Override
    public SearchHistory findByEmail(String email) {
        return historyRepository.findByEmail(email).orElse(null);
    }
}
