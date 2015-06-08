package com.example.projetotyche;



import java.util.Locale;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	
	//TTS//
	TextToSpeech ttobj;
	///////
	
	Button buttonAdd;
	Button buttonCalc;
	Button buttonMall;
	Button buttonHelp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonAdd = (Button) findViewById(R.id.btn_addProd);
		buttonAdd.setOnClickListener(this);
		
		buttonMall = (Button) findViewById(R.id.btn_mallSelect);
		buttonMall.setOnClickListener(this);
		
		buttonHelp = (Button) findViewById(R.id.btn_help);
		buttonHelp.setOnClickListener(this);
		
		buttonCalc = (Button) findViewById(R.id.btn_calcRoute);
		buttonCalc.setOnClickListener(this);		
		
		getActionBar().hide();
		
		//TTS//
	      ttobj=new TextToSpeech(getApplicationContext(), 
	      new TextToSpeech.OnInitListener() {
	      @Override
	      public void onInit(int status) {
	         if(status != TextToSpeech.ERROR){
	             ttobj.setLanguage(new Locale("pt","br"));
	            }				
	         }
	      });
	    ///////
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btn_addProd:
			addProduct();
			break;
		case R.id.btn_calcRoute:
			calcRoute();
			Toast.makeText(this,"Test",Toast.LENGTH_SHORT);
			break;
		case R.id.btn_mallSelect:
			mallSelect();
			break;
		case R.id.btn_help:
			speakText("Tyche é uma ferramenta de ajuda para cegos.");
			break;
		}	
	}
	
	private void addProduct(){
		startActivity(new Intent("com.example.projetotyche.AddProdActivity"));
	}
	
	
	private void mallSelect(){
		speakText("Localizando o seu mercado. . . . . . Encontrado. Você está no supermercado X");
	}
		
	private void calcRoute(){
		startActivity(new Intent("com.example.projetotyche.CalcularRota"));
	}
	
	
	//TTS//
	   @Override
	   public void onPause(){
	      if(ttobj !=null){
	         ttobj.stop();
	         ttobj.shutdown();
	      }
	      super.onPause();
	   }

	   
	public void speakText(String a){
			String toSpeak = a;
			Toast.makeText(getApplicationContext(), toSpeak, 
			Toast.LENGTH_SHORT).show();
			ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
			
	}
	//////
	

}