package com.holmusk.model.dao;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by gmsdvt on 10/14/15.
 */
public class DAOHandler {

    private static DAOHandler daoHandler;

    private Realm realm;
    private RecentSearchDAOImpl recentSearchDAOImpl;
    private FoodDAOImpl foodDAOImpl;

    public static DAOHandler getDaoHandler(Context context) {
        if(daoHandler == null){
            daoHandler = new DAOHandler(context);
        }
        return daoHandler;
    }

    private DAOHandler(Context context) {
        realm = Realm.getInstance(context);
        recentSearchDAOImpl = new RecentSearchDAOImpl(realm);
        foodDAOImpl = new FoodDAOImpl(realm);
    }


    public RecentSearchDAOImpl getRecentSearchDAOImpl(){
        return recentSearchDAOImpl;
    }
    public FoodDAOImpl getFoodDAOImpl(){return foodDAOImpl;}

}
