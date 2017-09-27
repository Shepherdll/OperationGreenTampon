package com.example.leakypete.operationgreentampon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ProfAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_acitivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item. getItemId()==android.R.id.home)
            {
                finish();
                startActivity(new Intent(new Intent(ProfAcitivty.this, HomeActivity.class)));
            }

            return super.onOptionsItemSelected(item);
    }

}
