package com.example.vijay.contactandnearby.ui.interfaces;

import com.example.vijay.contactandnearby.ui.contact.Contact;

public interface ContactItemClickListener {
    void onItemPhoneClick(String phoneNo);
    void onItemMsgClick(Contact contact);
}
