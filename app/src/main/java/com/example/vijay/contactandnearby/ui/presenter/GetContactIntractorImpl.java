package com.example.vijay.contactandnearby.ui.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.vijay.contactandnearby.ui.contact.Contact;
import com.example.vijay.contactandnearby.ui.contact.ContactFetcher;
import com.example.vijay.contactandnearby.ui.interfaces.MainContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetContactIntractorImpl implements MainContract.GetNoticeIntractor {

        @Override
        public void getNoticeArrayList(Context context, final OnFinishedListener onFinishedListener) {
            ContactFetcher fetcher=new ContactFetcher(context);
            onFinishedListener.onFinished(fetcher.fetchAll());

        }

    }

