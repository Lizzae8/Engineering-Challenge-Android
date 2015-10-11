package com.holmusk.eatright.dao;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by kai on 2015-10-07.
 */
public class DaoFactory {

    private static Realm sRealm;

    public static Realm getInstance(Context context) {
        if (sRealm == null) {
            sRealm = Realm.getInstance(context);
        }
        return sRealm;
    }
}
