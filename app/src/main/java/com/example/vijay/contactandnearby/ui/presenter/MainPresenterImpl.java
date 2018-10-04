package com.example.vijay.contactandnearby.ui.presenter;

import android.content.ContentResolver;
import android.content.Context;

import com.example.vijay.contactandnearby.ui.contact.Contact;
import com.example.vijay.contactandnearby.ui.interfaces.MainContract;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
        if(mainView != null){
            mainView.showProgress();
        }
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }
    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }

    }

    @Override
    public void requestDataFromProvider(Context context) {
        getNoticeIntractor.getNoticeArrayList(context,this);
    }


    @Override
    public void onFinished(ArrayList<Contact> contactArrayList) {
        ArrayList<Contact> contactArrayListfinal=new ArrayList<>();
        if(contactArrayList.size()>0){
            for (Contact contact:contactArrayList){
                if(contact.numbers.size()>0){
                    contactArrayListfinal.add(contact) ;
                }
            }
        }
        if(mainView != null){
            mainView.setDataToRecyclerView(contactArrayListfinal);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}