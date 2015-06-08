package com.example.projetotyche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends Activity {

	private static int SPLASH_TIME_OUT = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		//Delay//
		 new Handler().postDelayed(new Runnable() {
			 
	            /*
	             * Dando um timer para o produto, meio que para simular carregando um mercado
	             * antes disso estar implementado.
	             */
	           public void run() {
	                // Move para a pagina inicial.
	                Intent i = new Intent(Splash.this, MainActivity.class);
	                startActivity(i);
	 
	                // close this activity
	                finish();
	            }
	        }, SPLASH_TIME_OUT);
	 
		 }
}
