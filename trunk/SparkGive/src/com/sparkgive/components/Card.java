package com.sparkgive.components;


public class Card {
	private String details;
	private String cardName;
	
	public Card() {
		// Default
	}
	
	public Card(String details, String cardName) {
		this.details = details;
		this.cardName = cardName;
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
}