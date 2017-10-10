package com.example.leakypete.operationgreentampon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.leakypete.operationgreentampon.models.PostMalone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostViewActivity extends AppCompatActivity {

    private TextView txtTitle, txtBody;
    DatabaseReference mPostRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        final String bitchassnigger = getIntent().getStringExtra("title");
        final String punkassbitch = getIntent().getStringExtra("username");
        final String poopshooter = getIntent().getStringExtra("email");
        txtBody = (TextView) findViewById(R.id.txtBody);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
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
