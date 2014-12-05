package com.ingresse.ingressesdktest;


import com.ingresse.ingressesdk.AuthenticationClient;

import com.ingresse.ingressesdk.IngresseUser;
import com.ingresse.ingressesdk.IngresseError;
import com.ingresse.ingressesdk.IngresseSettings;

import com.ingresse.ingressesdktest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		IngresseSettings.setPublicKey("INSERT_YOUR_PUBLIC_KEY");
		IngresseSettings.setPrivateKey("INSERT_YOUR_PRIVATE_KEY");
		
		final TextView tokenTextView = (TextView) findViewById(R.id.token_text_view);
		final TextView userIdTextView = (TextView) findViewById(R.id.userid_text_view);
		
		Button ingresseLoginButton = (Button) findViewById(R.id.ingresse_login_button);
		ingresseLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AuthenticationClient authClient = new AuthenticationClient(LoginActivity.this);
				
				authClient.login(new AuthenticationClient.AuthenticationHandler() {
					
					@Override
					public void onCompleted(IngresseError error, IngresseUser user) {
						if (error == null) {
							tokenTextView.setText(user.getToken());
							userIdTextView.setText(user.getUserId());
						}
					}
					
				});
				
			}
		});
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		AuthenticationClient.onActivityResult(requestCode, resultCode, data);

	}

}
