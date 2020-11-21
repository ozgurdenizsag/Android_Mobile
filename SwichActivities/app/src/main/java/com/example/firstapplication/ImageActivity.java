package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ImageActivity extends AppCompatActivity {


    private static final String TAG ="messageDuTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Log.i(TAG,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void undoMessage(View view) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
    }
}
