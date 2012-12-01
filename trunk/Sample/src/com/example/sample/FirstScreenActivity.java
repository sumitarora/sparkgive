package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstScreenActivity extends Activity {
	// Initializing variables
	EditText inputName;
	EditText inputEmail;
	Button btnMapScreen;
	Button btnFavoriteScreen;
	Button btnPublicScreen;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_cards);

		inputName = (EditText) findViewById(R.id.name);
		inputEmail = (EditText) findViewById(R.id.email);
		btnMapScreen = (Button) findViewById(R.id.btnMapScreen);
		btnFavoriteScreen = (Button) findViewById(R.id.btnFavoriteScreen);
		btnPublicScreen = (Button) findViewById(R.id.btnPublicScreen);
		
		// Listening to button event
		btnMapScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Starting a new Intent

				Intent nextScreen = new Intent(getApplicationContext(),
						SecondScreenActivity.class);

				// Sending data to another Activity
				nextScreen.putExtra("name", inputName.getText().toString());
				nextScreen.putExtra("email", inputEmail.getText().toString());

				Log.e("n", inputName.getText() + "." + inputEmail.getText());

				startActivity(nextScreen);
			}

		});
		// Listening to button event
		btnPublicScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Starting a new Intent

				Intent nextScreen = new Intent(getApplicationContext(),
						ThirdScreenActivity.class);

				startActivity(nextScreen);
			}

		});
	}
}