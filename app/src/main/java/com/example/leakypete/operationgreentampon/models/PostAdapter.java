package com.example.leakypete.operationgreentampon.models;

import android.content.Context;
import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;



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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mThumbnail;
        private TextView mTitle;
        private TextView mPostID;
        private String _email;
        private ConstraintLayout layout;
        private Button mUpvote;
        private Button mComments;
        private Button mShare;


        public ViewHolder(View v) {
            super(v);

            mThumbnail = v.findViewById(R.id.imgThumbnail);
            mTitle =  v.findViewById(R.id.txtPreviewTitle);
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
            intent.putExtra("email",_email);
            context.startActivity(intent);
        }
    }
}
