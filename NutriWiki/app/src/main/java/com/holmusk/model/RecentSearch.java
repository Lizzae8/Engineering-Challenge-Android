package com.holmusk.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gmsdvt on 10/14/15.
 */
public class RecentSearch extends RealmObject implements Serializable{

    @PrimaryKey
    private String entryId;
    private String query;
    private long timestamp;

    public RecentSearch(){

    }

    public RecentSearch(String query, long timestamp){
        this.query = query;
        this.timestamp = timestamp;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
