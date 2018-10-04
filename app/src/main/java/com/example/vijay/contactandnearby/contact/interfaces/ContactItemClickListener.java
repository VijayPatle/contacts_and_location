package com.example.vijay.contactandnearby.contact.interfaces;

import com.example.vijay.contactandnearby.contact.contact.Contact;

public interface ContactItemClickListener {
    void onItemPhoneClick(String phoneNo);
    void onItemMsgClick(Contact contact);
}
