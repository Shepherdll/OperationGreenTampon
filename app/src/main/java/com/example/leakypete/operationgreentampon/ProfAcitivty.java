package com.example.leakypete.operationgreentampon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfAcitivty extends AppCompatActivity {
    private TabLayout mTablayout2;
    private ViewPager mViewPager2;
    private ProfilePageAdapter mProfPager;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference mDatabase;
    ImageView profPic;
    TextView mUsername;
    FirebaseUser user;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_acitivty);
        auth = FirebaseAuth.getInstance();
        mProfPager = new ProfilePageAdapter(getSupportFragmentManager());
        mTablayout2 = (TabLayout) findViewById(R.id.main_tabs2);
        mViewPager2 = (ViewPager) findViewById(R.id.profilePager);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mUsername = (TextView) findViewById(R.id.txtUser);

        mTablayout2.setupWithViewPager(mViewPager2);
        mViewPager2.setAdapter(mProfPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot penisdick : dataSnapshot.getChildren())
                    {
                        String dildo = penisdick.child("Email").getValue(String.class).replace(".",",");
                        if (dildo.equals(auth.getCurrentUser().getEmail().replace(".",",")))
                        {
                            username = penisdick.child("Name").getValue(String.class);
                            mUsername.setText(username);
                            break;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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
