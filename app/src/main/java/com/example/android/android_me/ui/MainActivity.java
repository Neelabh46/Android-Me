package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements Master_fragment.ClickInterface {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean mPane;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout)!=null)
        {
            mPane=true;

        GridView gridView = (GridView) findViewById(R.id.images_grid_view);
        gridView.setNumColumns(2);

        // Getting rid of the "Next" button that appears on phones for launching a separate activity
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.GONE);

        if(savedInstanceState == null) {
            // In two-pane mode, add initial BodyPartFragments to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Creating a new head fragment
            BodyFragment headFragment = new BodyFragment();
            headFragment.setmImageID(AndroidImageAssets.getHeads());
            // Add the fragment to its container using a transaction
            fragmentManager.beginTransaction()
                    .add(R.id.frame_body_head, headFragment)
                    .commit();

            // New body fragment
            BodyFragment bodyFragment = new BodyFragment();
            bodyFragment.setmImageID(AndroidImageAssets.getBodies());
            fragmentManager.beginTransaction()
                    .add(R.id.frame_body_mid, bodyFragment)
                    .commit();

            // New leg fragment
            BodyFragment legFragment = new BodyFragment();
            legFragment.setmImageID(AndroidImageAssets.getLegs());
            fragmentManager.beginTransaction()
                    .add(R.id.frame_body_legs, legFragment)
                    .commit();
        }
    } else {
        // We're in single-pane mode and displaying fragments on a phone in separate activities
        mPane = false;
    }

    // Dividing by 12 gives us these integer values because each list of images resources has a size of 12

    }

    @Override
    public void OnImageClick(int position) {
        Toast.makeText(this,"Item Selected: "+position,Toast.LENGTH_SHORT).show();
        int bodyPartNumber = position /12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        // Set the currently displayed item for the correct body part fragment
        if (mPane) {
            // Create two=pane interaction

            BodyFragment newFragment = new BodyFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    // A head image has been clicked
                    // Give the correct image resources to the new fragment
                    newFragment.setmImageID(AndroidImageAssets.getHeads());
                    newFragment.setmIndex(listIndex);
                    // Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_body_head, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageID(AndroidImageAssets.getBodies());
                    newFragment.setmIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_body_mid, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageID(AndroidImageAssets.getLegs());
                    newFragment.setmIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_body_legs, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {

            // Handle the single-pane phone case by passing information in a Bundle attached to an Intent


            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        // The "Next" button launches a new AndroidMeActivity
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}