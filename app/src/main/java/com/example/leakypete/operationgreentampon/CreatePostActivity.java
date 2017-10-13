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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePostActivity extends AppCompatActivity {

    private DatabaseReference mPostsRef,mUserRef;
    private Button btnPostPrv;

    EditText editTitle,editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        editContent = (EditText) findViewById(R.id.postContents);
        editTitle = (EditText) findViewById(R.id.txtTitle);

        mPostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        btnPostPrv = (Button) findViewById(R.id.btnPreview);

    btnPostPrv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent dickass = new Intent(CreatePostActivity.this, PostPreviewActivity.class);
            dickass.putExtra("title", editTitle.getText().toString());
            dickass.putExtra("content", editContent.getText().toString());
            dickass.putExtra("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
            startActivity(dickass);
            finish();
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    });

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
