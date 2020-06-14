package com.gmail.kharchenko55.vlad.dao;

import com.gmail.kharchenko55.vlad.model.search.SearchHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Integer> {
    List<SearchHistory> findAllByEmail(String email);
}
