package com.example.projetotyche;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.speech.tts.TextToSpeech; //TESTANDO

public class ProductList extends Activity implements View.OnClickListener {
	
	//TTS//
	TextToSpeech ttobj;
	///////
	
	Button buttonBack2;
	Button buttonNext;
	Button buttonReturn;	
	Button buttonRemove;
	GlobalClass globalVariable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		globalVariable = ((GlobalClass)getApplicationContext());
		if(globalVariable.isNull()==true){
			globalVariable.start();
		}
		getActionBar().hide();
		setContentView(R.layout.activity_product_list);
		buttonBack2 = (Button) findViewById(R.id.btn_readCurrent);
		buttonBack2.setOnClickListener(this);
		buttonNext = (Button) findViewById(R.id.btn_nextProd);
		buttonNext.setOnClickListener(this);
		buttonReturn = (Button) findViewById(R.id.btn_listReturn);
		buttonReturn.setOnClickListener(this);
		buttonRemove = (Button) findViewById(R.id.btn_removProd);
		buttonRemove.setOnClickListener(this);
		
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
		getMenuInflater().inflate(R.menu.product_list, menu);
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

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_readCurrent:
			speakText(v);
			break;
		case R.id.btn_nextProd:
			globalVariable.next();
			break;
		case R.id.btn_removProd:
			globalVariable.remProductFromVector();
			break;
		case R.id.btn_listReturn:
			finish();
			break;
		}
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

	   
	public void speakText(View view){
		switch(view.getId()){
		case R.id.btn_readCurrent:
			String toSpeak = globalVariable.getCurrent();
			Toast.makeText(getApplicationContext(), toSpeak, 
			Toast.LENGTH_SHORT).show();
			ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
			break;
		case R.id.btn_nextProd:
			String toSpeak1 = "Produto numero " + globalVariable.currPos();
			Toast.makeText(getApplicationContext(), toSpeak1, 
			Toast.LENGTH_SHORT).show();
			ttobj.speak(toSpeak1, TextToSpeech.QUEUE_FLUSH, null);
			break;
		}
	}
	//////
}
