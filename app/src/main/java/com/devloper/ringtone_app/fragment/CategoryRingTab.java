package com.devloper.ringtone_app.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devloper.ringtone_app.R;
import com.devloper.ringtone_app.adapter.CategoryRingAdapter;
import com.devloper.ringtone_app.adapter.RingtoneObject;
import com.devloper.ringtone_app.adapter.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CategoryRingTab extends Fragment {

    private RecyclerView mRecyclerView;
    private CategoryRingAdapter mAdapter;

    private ProgressBar progressBar;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mCatObject;

    public CategoryRingTab() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.featured_ringtone, container, false);

        getActivity().setTitle("Ringtone");


        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = view.findViewById(R.id.progress_circular);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mCatObject = new ArrayList<Upload>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ringCategory");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("Ringtone", mCatObject.toString());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String imgName = postSnapshot.child("imgName").getValue(String.class);
                    String imgUrl = postSnapshot.child("imgUrl").getValue(String.class);
                    Upload ringtoneObject = new Upload(imgName, imgUrl);
                    mCatObject.add(ringtoneObject);
                }
                mAdapter = new CategoryRingAdapter(getContext(), mCatObject);
                mRecyclerView.setAdapter(mAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return view;


    }
}