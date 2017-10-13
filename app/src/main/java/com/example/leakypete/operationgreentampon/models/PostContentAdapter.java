package com.example.leakypete.operationgreentampon.models;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leakypete.operationgreentampon.PostViewActivity;
import com.example.leakypete.operationgreentampon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by That Sexy Taylor on 10/11/2017.
 */

public class PostContentAdapter extends RecyclerView.Adapter {
    private ArrayList<PostMalone> mPosts;
    private Context mContext;
    String dickass = "";
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    String title = "";

    ImageView ItemImage;


    public PostContentAdapter(ArrayList<PostMalone> posts, Context dick) {
        mPosts = posts;
        mContext = dick;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View dick;

        if(mContext instanceof PostViewActivity)
        {
            // Inflate the custom layout
            dick = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_item_view, parent, false);

            // Return a new holder instance
            return new PostViewHolder(dick);

        }
        else
        {
            // Inflate the custom layout
            dick = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.posttext_item, parent, false);

            // Return a new holder instance
            return new PostEditViewHolder(dick);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get the data model based on position
        final PostMalone post = mPosts.get(position);

        if(mContext instanceof PostViewActivity)
        {
            ((PostViewHolder)holder).lineContent.setText();
        }

        // Set item views based on your views and data model
        final TextView title = holder.mTitle;
        title.setText(post.getTitle());

        holder._email = post.getEmail();
        ItemImage = holder.mThumbnail;

        dickass = post.getUsername();

        final TextView usernameID = holder.mPostID;
        usernameID.setText(post.getUsername());






    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public static class PostEditViewHolder extends RecyclerView.ViewHolder  {

        private EditText lineContent;
        private ConstraintLayout layout;



        public PostEditViewHolder(View v) {
            super(v);


            lineContent = v.findViewById(R.id.txtPostItem);

            layout =  itemView.findViewById(R.id.with_that_pistol_layout);

        }




    }
    public static class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView lineContent;
        private ConstraintLayout layout;



        public PostViewHolder(View v) {
            super(v);


            lineContent = v.findViewById(R.id.txtPostItem);

            layout =  itemView.findViewById(R.id.with_that_pistol_layout);

        }
    }
}
