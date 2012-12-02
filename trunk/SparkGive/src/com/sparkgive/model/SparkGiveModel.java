package com.sparkgive.model;

import java.util.ArrayList;

import com.sparkgive.R;
import com.sparkgive.components.Card;
public class SparkGiveModel 
{
	public static String username = "Sumit Arora";
	public static String campaign = "Cancer Research Fund Raiser";
	
	public static ArrayList<String> nearByPlaces = new ArrayList<String>();
	
	
	public static ArrayList<Card> cardList = new ArrayList<Card>() {{ 
	add(new Card("details for PC", "food banks",R.drawable.foodbankcanada,R.drawable.back_sickkids));
	add(new Card("details for shoppers drug mart", "sick kids", R.drawable.back_sickkids, R.drawable.back_sickkids));
	add(new Card("details for starbucks", "breast cancer", R.drawable.back_starbucks, R.drawable.back_sickkids));
	add(new Card("details for PC", "food banks",R.drawable.back_walmart, R.drawable.back_sickkids));
	add(new Card("details for shoppers drug mart", "sick kids", R.drawable.back_loblaws, R.drawable.back_sickkids));
	add(new Card("details for starbucks", "breast cancer", R.drawable.back_toysrus, R.drawable.back_sickkids));
	}};
	
}
