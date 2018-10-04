package com.example.vijay.contactandnearby.ui.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vijay.contactandnearby.R;
import com.example.vijay.contactandnearby.ui.contact.Contact;
import com.example.vijay.contactandnearby.ui.interfaces.ContactItemClickListener;

import java.util.ArrayList;
import java.util.Random;

public class RepositoriesRecyclerAdapter extends RecyclerView.Adapter<RepositoriesRecyclerAdapter.EmployeeViewHolder> {

    private ArrayList<Contact> dataList;
    private ContactItemClickListener recyclerItemClickListener;

    public RepositoriesRecyclerAdapter(ArrayList<Contact> dataList , ContactItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_info_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
if(dataList.get(position).getNumbers().size()>0) {
    holder.mPhoneNo.setText(dataList.get(position).getNumbers().get(0).number);
    String name = dataList.get(position).getName();
    String firstChar = String.valueOf(name.charAt(0));
    holder.mContactName.setText(name);
    Random rndtext = new Random();
    int colortext = Color.argb(255, rndtext.nextInt(256), rndtext.nextInt(256), rndtext.nextInt(256));
    // holder.mContactName.setTextColor(colortext);
    Random rnd = new Random();
    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    holder.mContactProfile.setText(firstChar.toUpperCase());
    holder.mContactProfile.setTextColor(colortext);
    holder.mContactProfile.setBackgroundResource(R.drawable.rounded_textview);
   // holder.mContactProfile.setBackgroundColor(color);
}
        holder.mContactPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemPhoneClick(dataList.get(position).getNumbers().get(0).number);
            }
        });


        holder.mContactMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemMsgClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView mPhoneNo, mContactName,mContactProfile;

        AppCompatImageView mContactPhone,mContactMessage;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            mPhoneNo =  (AppCompatTextView)itemView.findViewById(R.id.phone_txt);
            mContactName =  (AppCompatTextView) itemView.findViewById(R.id.name_of_number);
            mContactProfile =  (AppCompatTextView) itemView.findViewById(R.id.profile);
            mContactPhone =  (AppCompatImageView) itemView.findViewById(R.id.ph_call);
            mContactMessage =  (AppCompatImageView) itemView.findViewById(R.id.send_msg);

        }
    }
}
