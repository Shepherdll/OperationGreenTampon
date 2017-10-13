package com.example.leakypete.operationgreentampon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PostPreviewActivity extends AppCompatActivity {


    private TextView pTitle, pUser;
    private TextView pBody;
    private Button btnPost;
    DatabaseReference mPostsRef, mUserRef;
    String name,email,title,content;
    HashMap<String, String> post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);
        final String burntheniggers = getIntent().getStringExtra("title");
        final String dildoinass = getIntent().getStringExtra("email");
        final String bitchasstit = getIntent().getStringExtra("content");


        pBody = (TextView) findViewById(R.id.txtBodyPreview);
        pTitle = (TextView) findViewById(R.id.txtPrevTitle);
        btnPost = (Button) findViewById(R.id.btnPost);
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        pUser = (TextView) findViewById(R.id.txtPrvUser);
        mPostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");

        mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(dildoinass)) {
                            name = postSnapshot.child("Name").getValue(String.class);
                            pUser.setText(name);

                        }

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        pBody.setText(bitchasstit);
        pTitle.setText(burntheniggers);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePost();
                Intent intent = new Intent(PostPreviewActivity.this, PostViewActivity.class);
                intent.putExtra("title", pTitle.getText().toString());
                intent.putExtra("username", pUser.getText().toString());
                intent.putExtra("email",FirebaseAuth.getInstance().getCurrentUser().getEmail());
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
    }


    private void makePost(){
        post = new HashMap<String, String>();
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        title = pTitle.getText().toString();
        content = pBody.getText().toString();

        post.put("Email", email);
        post.put("Title", title);
        post.put("Content", content);
        post.put("Name", name);
        mPostsRef.push().setValue(post);





    }

    }


