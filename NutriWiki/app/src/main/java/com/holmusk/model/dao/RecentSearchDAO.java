package com.holmusk.model.dao;

import com.holmusk.model.RecentSearch;

import io.realm.RealmResults;

/**
 * Created by gmsdvt on 10/14/15.
 */

public interface RecentSearchDAO {
    RealmResults<RecentSearch> getAllRecentSearches();
    RealmResults<RecentSearch> findRecentSearchByQuery(String query);
    boolean addOrUpdateRecentSearch(RecentSearch recentSearch);
}