package com.cpcrew.instadine.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cpcrew.instadine.R;

public class VotingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voting);
	}
	
	public void callSearchActivity(View v){
		Intent i = new Intent(this, MapActivity.class);
		startActivity(i);
	}
}
