package com.apptech.newsniffer.viewmodel;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.apptech.newsniffer.BR;
import com.apptech.newsniffer.model.MessageModel;

/**
 * Created by Emmanuel Rop on 15/09/2018.
 */
public class MainViewModel extends BaseObservable {
    public String sender, message, status;
    boolean genuine;

    public MainViewModel(MessageModel messageModel) {
        this.message = messageModel.getMessage();
        this.sender = messageModel.getSender();
        this.status = messageModel.getStatus();
        this.genuine = messageModel.isGenuine();
    }

    @Bindable
    public boolean isGenuine() {
        return genuine;
    }

    public void setGenuine(boolean genuine) {
        this.genuine = genuine;
        notifyPropertyChanged(BR.genuine);
    }

    public void setSender(String sender) {
        this.sender = sender;
        notifyPropertyChanged(BR.sender);
    }

    @Bindable
    public String getSender() {
        return sender;
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    public void onClicked(View view) {
        Toast.makeText(view.getContext(), "askdlf", Toast.LENGTH_SHORT).show();
        setGenuine(false);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setMessage(s.toString());
    }
    public void exit(View view){
        ((Activity)view.getContext()).finish();
    }
}
