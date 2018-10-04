package com.example.vijay.contactandnearby.ui.interfaces;

import android.content.ContentResolver;
import android.content.Context;

import com.example.vijay.contactandnearby.ui.contact.Contact;

import java.util.ArrayList;

public interface MainContract {
    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromProvider(Context context);

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Contact> noticeArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Contact> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getNoticeArrayList(Context context,OnFinishedListener onFinishedListener);
    }
}

