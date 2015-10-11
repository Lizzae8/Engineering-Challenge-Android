package com.holmusk.eatright.ui.views;

/**
 * A view in MVP design that provides an interface to a View that is required to load data
 * from server.
 *
 * Created by kai on 2015-10-07.
 */
public interface LoadDataView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);
}
