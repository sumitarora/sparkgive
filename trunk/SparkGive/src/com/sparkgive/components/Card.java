package com.sparkgive.components;


public class Card {
	private String details;
	private String cardName;
	private int resourceId;
	private int resourceIdRedeem;

	public Card() {
		// Default
	}
	
	public Card(String details, String cardName, int resourceId, int resourceIdRedeem) {
		this.details = details;
		this.cardName = cardName;
		this.resourceId = resourceId;
		this.resourceIdRedeem =  resourceIdRedeem;
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

	public int getResourceIdRedeem() {
		return resourceIdRedeem;
	}

	public void setResourceIdRedeem(int resourceIdRedeem) {
		this.resourceIdRedeem = resourceIdRedeem;
	}
}