package com.example.leakypete.operationgreentampon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostPreviewActivity extends AppCompatActivity {


    private TextView pTitle;
    private TextView pBody;
    DatabaseReference mPostRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);
        final String bitchassnigger = getIntent().getStringExtra("title");
        final String punkassbitch = getIntent().getStringExtra("username");
        final String poopshooter = getIntent().getStringExtra("email");
        final String dickturdinchat = getIntent().getStringExtra("content");

        pBody = (TextView) findViewById(R.id.txtBodyPreview);
        pTitle = (TextView) findViewById(R.id.txtPreviewTitle);
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
                            pBody.setText(content);
                            pTitle.setText(title);

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
