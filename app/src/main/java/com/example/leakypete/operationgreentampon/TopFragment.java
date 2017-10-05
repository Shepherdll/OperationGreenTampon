package com.example.leakypete.operationgreentampon;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leakypete.operationgreentampon.models.PostAdapter;
import com.example.leakypete.operationgreentampon.models.PostMalone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TopFragment extends Fragment {


    private RecyclerView recyclerView;
    private DatabaseReference mUserRef,mPostRef;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<PostMalone> posts;
    private PostAdapter adapter;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View penis = inflater.inflate(R.layout.fragment_top, container, false);
        posts = new ArrayList<>();
        adapter = new PostAdapter(posts, getContext());
        mPostRef = FirebaseDatabase.getInstance().getReference().child("Posts");

        mPostRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String name = postSnapshot.child("Name").getValue(String.class);
                        String email = postSnapshot.child("Email").getValue(String.class);
                        String title = postSnapshot.child("Title").getValue(String.class);

                        posts.add(new PostMalone(title, name, email));

                    }

                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

        //Recycler View
        recyclerView = penis.findViewById(R.id.brenGay);

        //Context c = getContext();
        mLinearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLinearLayoutManager);
        VerticalSpaceItemDecoration dividerItemDecoration = new VerticalSpaceItemDecoration(60);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);
        return penis;
    }




class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration
{
    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight)
    {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.bottom = verticalSpaceHeight;
    }
}

}
