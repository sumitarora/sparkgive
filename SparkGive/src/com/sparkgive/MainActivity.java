package com.sparkgive;

import java.util.ArrayList;
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
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.sparkgive.components.Card;
import com.sparkgive.components.StackAdapter;
import com.sparkgive.components.StackItem;
import com.sparkgive.model.SparkGiveModel;

public class MainActivity extends Activity implements OnClickListener, SearchView.OnQueryTextListener,
SearchView.OnCloseListener {

	private ImageView profileButton, mapsButton, publicCardsButton, settingsButton;
	private TextView  mStatusView;
	private SearchView mSearchView;
//	private Button mRedeemButton;
	ArrayList<Card> mCardList;
	ArrayList<StackItem> mStackItems;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	SparkGiveModel.nearByPlaces.add("Place 1");
    	SparkGiveModel.nearByPlaces.add("Place 2");
    	SparkGiveModel.nearByPlaces.add("Place 3");
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ActionBar actionBar = getActionBar(); 
        Bitmap actionBarBackground = BitmapFactory.decodeResource(getResources(), R.drawable.footer_bar);
        BitmapDrawable background = new BitmapDrawable(getResources(), actionBarBackground);
        
        actionBar.setBackgroundDrawable(background);
        actionBar.setHomeButtonEnabled(true);
        
        profileButton = (ImageView) findViewById(R.id.profile);
        mapsButton = (ImageView) findViewById(R.id.Maps_button);
        publicCardsButton = (ImageView) findViewById(R.id.public_cards);
        settingsButton = (ImageView) findViewById(R.id.list);
        //mStatusView = (TextView) findViewById(R.id.status_text);

        
        profileButton.setOnClickListener(this);
        mapsButton.setOnClickListener(this);
        publicCardsButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

       mStackItems = new ArrayList<StackItem>();
       mCardList = SparkGiveModel.cardList;
       
       reloadCards();
       for(int i=0; i<mCardList.size(); i++) {
    	   mStackItems.add(new StackItem(this.getResources().getDrawable(mCardList.get(i).getResourceId())));
       }
      	///Stackview
        StackView stk = (StackView)this.findViewById(R.id.stackView1);
      	StackAdapter adapt = new StackAdapter(this, R.layout.item, mStackItems);
   	
   	 
        stk.setAdapter(adapt);

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

    @Override
    protected void onResume() {
    	super.onResume();
    	
    }
    
    @Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.profile:
			startActivity(new Intent(getApplicationContext(), CampaignsActivity.class));
			break;
		case R.id.Maps_button:
			startActivity(new Intent(getApplicationContext(), MapsActivity.class));
			break;	
		case R.id.public_cards:
			startActivity(new Intent(getApplicationContext(), PublicCardsActivity.class));
			break;
		case R.id.list:
			break;
		
		}
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
       
        return true;
    }

    public boolean onQueryTextSubmit(String query) {

    	startActivity(new Intent(getApplicationContext(), SearchableActivity.class));
        return true;
    }
    

    
    
    private void reloadCards() {
    	SparkGiveModel.cardList.clear();
    	SparkGiveModel.cardList.add(new Card("Loblaws will donate food worth 5% of your purchase price, given a purchase of at least $50, to Food Bank Canada. SparkCard expires on March 31, 2013.", "food banks",R.drawable.final_loblaws_foodbank, R.drawable.back_loblaws));
    	SparkGiveModel.cardList.add(new Card("Shoppers Drug Mart will support SickKids Foundation by donating 3% of your purchase price, given a purchase of at least $40, to help sick kids ail faster. SparkCard expires on May 15, 2013.", "sick kids", R.drawable.final_sdm_sickkids, R.drawable.back_sickkids));
    	SparkGiveModel.cardList.add(new Card("Starbucks will donate 2% of your purchase price, given a purchase of $5 or more, to the Canadian Breast Cancer Research Foundation. SparkCard expires on June 1, 2013.", "breast cancer", R.drawable.final_star, R.drawable.back_starbucks));
    	SparkGiveModel.cardList.add(new Card("ToyRUs will donate 1% of your  purchase cost, given a purchase of $50 or more, to Make A Wish Foundation to help grant a child a wish. SparkCard expires on September 21, 2013.", "wish",R.drawable.final_toysrus, R.drawable.back_toysrus));
    	SparkGiveModel.cardList.add(new Card("Walmart will donate 15% of your purchase price, on purchase worth $200 or more, to Global Women's Economic Empowerment. SparkCard valid on Women's Day (March 8, 2013).", "Red cross",R.drawable.final_walmartredcross, R.drawable.back_walmart));
    }
    
    public boolean onClose() {
       
        return false;
    }
    
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

}
