package com.sparkgive;

import com.sparkgive.model.SparkGiveModel;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	SparkGiveModel.nearByPlaces.add("Place 1");
    	SparkGiveModel.nearByPlaces.add("Place 2");
    	SparkGiveModel.nearByPlaces.add("Place 3");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}
