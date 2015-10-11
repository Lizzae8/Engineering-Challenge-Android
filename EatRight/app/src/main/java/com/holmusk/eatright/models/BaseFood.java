package com.holmusk.eatright.models;

import io.realm.RealmList;

/**
 * Created by kai on 2015-10-09.
 */
public interface BaseFood {

    RealmList<Portion> getPortions();
    String getName();
}
