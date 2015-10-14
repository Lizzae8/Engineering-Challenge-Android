package com.holmusk.model.dao;

import com.holmusk.model.RecentSearch;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gmsdvt on 10/14/15.
 */
public class RecentSearchDAOImpl implements RecentSearchDAO {
    private Realm realm;
    private static final String TAG = RecentSearchDAOImpl.class.getSimpleName();

    protected RecentSearchDAOImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public RealmResults<RecentSearch> getAllRecentSearches() {
        //Find all recent searches sorted by the most recent
        RealmResults<RecentSearch> results = realm.where(RecentSearch.class).findAllSorted("timestamp", false);
        return results;
    }

    @Override
    public RealmResults<RecentSearch> findRecentSearchByQuery(String query) {
        RealmResults<RecentSearch> results = realm.where(RecentSearch.class).equalTo("query", query).findAll();
        return results;
    }

    @Override
    public boolean addOrUpdateRecentSearch(RecentSearch recentSearch) {
        RealmResults<RecentSearch> recentResults = findRecentSearchByQuery(recentSearch.getQuery());
        if(recentResults!=null && recentResults.size()>0){
            recentSearch.setEntryId(recentResults.get(0).getEntryId());
        }else{
            recentSearch.setEntryId((UUID.randomUUID().toString()));
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(recentSearch);
        realm.commitTransaction();

        return true;
    }
}
