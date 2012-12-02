package com.sparkgive;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

public class PublicCardsActivity extends Activity implements SearchView.OnQueryTextListener,
SearchView.OnCloseListener {

	private TextView  mStatusView;
	private SearchView mSearchView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campaigns);
		// enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
		final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
//        mStatusView = (TextView) findViewById(R.id.status_text);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_campaigns, menu);
//		return true;
//	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {    
       switch (item.getItemId()) 
       {        
          case android.R.id.home:            
             Intent intent = new Intent(this, MainActivity.class);            
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
             startActivity(intent);            
             return true;        
          default:            
             return super.onOptionsItemSelected(item);    
       }
    }

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        super.onCreateOptionsMenu(menu);

	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.layout.searchview_in_menu, menu);
	        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	        setupSearchView();

	        return true;
	    }

	private void setupSearchView() {

        mSearchView.setIconifiedByDefault(true);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();

            // Try to use the "applications" global search provider
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {
                if (inf.getSuggestAuthority() != null
                        && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            mSearchView.setSearchableInfo(info);
        }

        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
    }

    public boolean onQueryTextChange(String newText) {
        mStatusView.setText("Query = " + newText);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        mStatusView.setText("Query = " + query + " : submitted");
        return false;
    }

    public boolean onClose() {
        mStatusView.setText("Closed!");
        return false;
    }

}