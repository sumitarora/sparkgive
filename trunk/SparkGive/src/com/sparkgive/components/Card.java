package com.sparkgive.components;


public class Card {
	private String details;
	private String cardName;
	private int resourceId;

	public Card() {
		// Default
	}
	
	public Card(String details, String cardName, int resourceId) {
		this.details = details;
		this.cardName = cardName;
		this.resourceId = resourceId;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
}