package com.example.leakypete.operationgreentampon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreatePostActivity extends AppCompatActivity {

    private DatabaseReference mPostsRef,mUserRef;
    private Button btnPost;
    EditText editTitle,editContent;
    String name,email,title,content;
    HashMap<String, String> post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        editContent = (EditText) findViewById(R.id.postContents);
        editTitle = (EditText) findViewById(R.id.txtPreviewTitle);
        mPostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        btnPost = (Button) findViewById(R.id.btnPreview);

    btnPost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            makePost();
        }
    });

    }

    private void makePost(){
        post = new HashMap<String, String>();
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        title = editTitle.getText().toString();
        content = editContent.getText().toString();

        post.put("Email", email);
        post.put("Title", title);
        post.put("Content", content);


        mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot penisdick : dataSnapshot.getChildren())
                    {
                        String dildo = penisdick.child("Email").getValue(String.class).replace(".",",");
                        if (dildo.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".",",")))
                        {
                            name = penisdick.child("Name").getValue(String.class);
                            post.put("Name", name);
                            mPostsRef.push().setValue(post);
                            break;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //mPostsRef.push().setValue(post);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item. getItemId() == android.R.id.home)
        {
            finish();
            startActivity(new Intent(new Intent(CreatePostActivity.this, HomeActivity.class)));
        }

        return super.onOptionsItemSelected(item);
    }
}
