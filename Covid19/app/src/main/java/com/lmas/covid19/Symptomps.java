package com.lmas.covid19;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Symptomps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptomps);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Symptomps.this,MainActivity.class));
        finish();;
    }
}
