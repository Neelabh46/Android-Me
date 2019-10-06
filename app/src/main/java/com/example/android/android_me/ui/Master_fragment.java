package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class Master_fragment extends Fragment {

    public Master_fragment(){

    }
    public interface ClickInterface{

        void OnImageClick(int position);

    }
    ClickInterface mImageInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView mgridview = (GridView) rootview.findViewById(R.id.images_grid_view);
        MasterListAdapter adapter = new  MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        mgridview.setAdapter(adapter);
        mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mImageInterface.OnImageClick(i);
            }
        });
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            mImageInterface = (ClickInterface) context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

}

