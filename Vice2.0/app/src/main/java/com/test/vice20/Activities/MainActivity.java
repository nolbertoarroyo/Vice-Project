package com.test.vice20.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.vice20.R;

public class MainActivity extends AppCompatActivity {

    private String baseURL = "http://vice.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
