package com.example.leakypete.operationgreentampon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class PostViewActivity extends AppCompatActivity {

    private TextView txtTitle, txtBody,txtUser;
    DatabaseReference mPostRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        final String bitchassnigger = getIntent().getStringExtra("title");
        final String punkassbitch = getIntent().getStringExtra("username");
        final String poopshooter = getIntent().getStringExtra("email");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView profileActivity = (ImageView) toolbar.findViewById(R.id.profile);
        profileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                {
                    Intent ProfAcitivty = new Intent(PostViewActivity.this, ProfAcitivty.class);
                    startActivity(ProfAcitivty);
                    finish();
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                }
            }
        });
        final ImageView homeactivity = (ImageView) toolbar.findViewById(R.id.home);
        homeactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeactivtybae = new Intent(PostViewActivity.this, HomeActivity.class);
                startActivity(homeactivtybae);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });


        txtBody = (TextView) findViewById(R.id.txtBodyView);
        txtTitle = (TextView) findViewById(R.id.txtTitleView);
        txtUser = (TextView) findViewById(R.id.txtUsernameView);
        mPostRef = FirebaseDatabase.getInstance().getReference().child("Posts");

        mPostRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String name = postSnapshot.child("Name").getValue(String.class);
                        String content = postSnapshot.child("Content").getValue(String.class);
                        String title = postSnapshot.child("Title").getValue(String.class);
                        String email = postSnapshot.child("Email").getValue(String.class);

                        if(email.equals(poopshooter) && title.equals(bitchassnigger))
                        {
                            txtBody.setText(content);
                            txtTitle.setText(title);
                            txtUser.setText(name);
                        }

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

    }
}
