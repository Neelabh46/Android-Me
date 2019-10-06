package com.example.android.android_me.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyFragment extends Fragment {


    public static final String IMAGE_IDS = "Image_id";
    public static final String INDEX = "index";
    private List<Integer> mImageID;
    private int mIndex;

    public BodyFragment()
    {
        //
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        if(savedInstanceState!=null)
        {
            mIndex=savedInstanceState.getInt(INDEX);
            mImageID=savedInstanceState.getIntegerArrayList(IMAGE_IDS);
        }
        View rootview = inflater.inflate(R.layout.fragment_body_part,container,false);
        final ImageView imgview = (ImageView) rootview.findViewById(R.id.fragment_head_id);
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIndex != mImageID.size()-1)
                    mIndex++;
                else{
                    mIndex=0;
                }
                imgview.setImageResource(mImageID.get(mIndex));

            }

        });
        imgview.setImageResource(mImageID.get(mIndex));
        return rootview;
    }

    public void setmImageID(List<Integer> mImageID) {
        this.mImageID = mImageID;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putIntegerArrayList(IMAGE_IDS, (ArrayList<Integer>) mImageID);
        outState.putInt(INDEX,(Integer) mIndex);

    }
}
