package com.apptech.newsniffer.view;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apptech.newsniffer.R;
import com.apptech.newsniffer.databinding.ActivityMainBinding;
import com.apptech.newsniffer.model.MessageModel;
import com.apptech.newsniffer.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding activityMainBinding;
String sender,message;
int status;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        MessageModel mainViewModel = new MessageModel();
        mainViewModel.setSender("Sender here");
        mainViewModel.setMessage("Message here");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getInt("status");
            sender = extras.getString("sender");
            message = extras.getString("message");
            if (status == 0) {
                mainViewModel.setSender("Sender: "+sender);
                mainViewModel.setMessage(message);
                mainViewModel.setGenuine(true);
                mainViewModel.setStatus("GENUINE MPESA MESSAGE!");

            } else if (status == 1) {
                mainViewModel.setSender("Sender: "+sender);
                mainViewModel.setMessage("Please confirm the sender of this message.");
                mainViewModel.setStatus("MESSAGE NOT FROM MPESA!");
                mainViewModel.setGenuine(false);
            }
        }else{
            mainViewModel.setSender("");
            mainViewModel.setMessage("Waiting for Mpesa message");
            mainViewModel.setStatus("Status");
        }

        MainViewModel mainViewModel2 = new MainViewModel(mainViewModel);
        activityMainBinding.setViewmodel(mainViewModel2);

    }

}
