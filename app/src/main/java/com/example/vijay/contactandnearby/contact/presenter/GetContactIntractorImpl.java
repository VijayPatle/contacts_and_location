package com.example.vijay.contactandnearby.contact.presenter;

import android.content.Context;

import com.example.vijay.contactandnearby.contact.contact.ContactFetcher;
import com.example.vijay.contactandnearby.contact.interfaces.MainContract;

public class GetContactIntractorImpl implements MainContract.GetNoticeIntractor {

        @Override
        public void getNoticeArrayList(Context context, final OnFinishedListener onFinishedListener) {
            ContactFetcher fetcher=new ContactFetcher(context);
            onFinishedListener.onFinished(fetcher.fetchAll());

        }

    }

