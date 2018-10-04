package com.example.vijay.contactandnearby.ui.view.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vijay.contactandnearby.R;
import com.example.vijay.contactandnearby.ui.adapters.ContactAdapter;
import com.example.vijay.contactandnearby.ui.contact.Contact;
import com.example.vijay.contactandnearby.ui.interfaces.ContactItemClickListener;
import com.example.vijay.contactandnearby.ui.interfaces.MainContract;
import com.example.vijay.contactandnearby.ui.presenter.GetContactIntractorImpl;
import com.example.vijay.contactandnearby.ui.presenter.MainPresenterImpl;
import com.example.vijay.contactandnearby.ui.util.PermissionUtil;

import java.util.ArrayList;

public class ContactFragment extends Fragment implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String mPhoneNo;

    private MainContract.presenter presenter;
    private Context mContext;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE=111;
    private static final int READ_CONTACT_PERMISSION_REQUEST_CODE=222;
    private static final int PERMISSIONS_REQUEST_SEND_SMS=333;
    private Contact mContact;
    private String mMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.contact_fragment, container, false);
        initializeToolbarAndRecyclerView(rootView);
        fetchContactList();

        return rootView;
    }

    private void fetchContactList() {
        if(PermissionUtil.getInstance().checkPermission(Manifest.permission.READ_CONTACTS,mContext)) {
            presenter = new MainPresenterImpl(this, new GetContactIntractorImpl());
            presenter.requestDataFromProvider(mContext);
        }else{
            PermissionUtil.getInstance().showContactPermissionDialog(getActivity(),READ_CONTACT_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    private void initializeToolbarAndRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<Contact> noticeArrayList) {
        ContactAdapter adapter = new ContactAdapter(noticeArrayList , recyclerItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(mContext,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }
    /**
         * RecyclerItem click event listener
     * */
    private ContactItemClickListener recyclerItemClickListener = new ContactItemClickListener() {


        @Override
        public void onItemPhoneClick(String phoneNo) {
            if(!PermissionUtil.getInstance().checkPermission(Manifest.permission.CALL_PHONE,mContext)){
                PermissionUtil.getInstance().showPhonePermissionDialog(getActivity(),MAKE_CALL_PERMISSION_REQUEST_CODE);
            } else {
               // Toast.makeText(mContext, "Permission Call Phone denied", Toast.LENGTH_SHORT).show();

                mPhoneNo = "tel:" + phoneNo;
                makeCall();

            }
        }

        @Override
        public void onItemMsgClick(final Contact contact) {

            mContact=contact;
            LayoutInflater li = LayoutInflater.from(mContext);
            View promptsView = li.inflate(R.layout.send_message, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    mContext);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                  mMessage=  userInput.getText().toString();
                                  if(PermissionUtil.getInstance().checkPermission(Manifest.permission.SEND_SMS,mContext)){
                                      sendSMS();
                                  }else{
                                      PermissionUtil.getInstance().showSMSPermissionDialog(getActivity(),PERMISSIONS_REQUEST_SEND_SMS);
                                  }

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }


    };

    private void makeCall() {

        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(mPhoneNo)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE :
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    makeCall();
                }
                break;
            case READ_CONTACT_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    fetchContactList();
                }
                break;
            case PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    sendSMS();
                }
        }
    }
    public void sendSMS() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mContact.getNumbers().get(0).number, null, mMessage, null, null);
            Toast.makeText(mContext.getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(mContext.getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}