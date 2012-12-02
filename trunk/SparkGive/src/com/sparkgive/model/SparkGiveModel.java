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
	add(new Card("details for PC", "PC"));
	add(new Card("details for P&G", "xyz"));
	}};
	
}
