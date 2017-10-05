package com.example.leakypete.operationgreentampon.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.leakypete.operationgreentampon.PostViewActivity;
import com.example.leakypete.operationgreentampon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Peter on 6/6/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<PostMalone> mPosts;
    private Context mContext;
    String dickass = "";
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    String title = "";

    ImageView ItemImage;


    public PostAdapter(ArrayList<PostMalone> posts, Context dick) {
        mPosts = posts;
        mContext = dick;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.withthatpistol, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        final PostMalone post = mPosts.get(position);

        // Set item views based on your views and data model
        final TextView title = holder.mTitle;
        title.setText(post.getTitle());

        ItemImage = holder.mThumbnail;

        dickass = post.getUsername();

        final TextView usernameID = holder.mPostID;
        usernameID.setText(post.getUsername());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference("Posts");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {

                            if(postSnapshot.hasChild("Title"))
                            {
                                title.setText(postSnapshot.child("Title").getValue(String.class));
                                break;
                            }
                            else
                            {
                                title.setText("N/A");
                                break;
                            }


                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            if(postSnapshot.hasChild("Name"))
                            {
                                usernameID.setText(postSnapshot.child("Name").getValue(String.class));
                                break;
                            }
                            else
                            {
                                usernameID.setText("N/A");
                                break;
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

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mThumbnail;
        private TextView mTitle;
        private TextView mPostID;
        private ConstraintLayout layout;
        private Button mUpvote;
        private Button mComments;
        private Button mShare;


        public ViewHolder(View v) {
            super(v);

            mThumbnail = v.findViewById(R.id.imgThumbnail);
            mTitle =  v.findViewById(R.id.txtTitle);
            mPostID = v.findViewById(R.id.txtUserID);

            layout =  itemView.findViewById(R.id.with_that_pistol_layout);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent intent = new Intent(context, PostViewActivity.class);
            // intent.putExtra("image_id", );
            intent.putExtra("title", mTitle.getText().toString());
            intent.putExtra("username", mPostID.getText().toString());

            context.startActivity(intent);
        }
    }
}
