package com.holmusk.eatright.ui.presenters;

/**
 * Created by kai on 2015-10-07.
 */


/**
 * Presenter interface that should be implemented by all presenters in the applications.
 */
public interface Presenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();
}