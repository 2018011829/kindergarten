package com.example.myapplication.apply.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.main.util.ChangeStatusBarColor;

public class ApplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ChangeStatusBarColor.initSystemBar(this);
    }
}