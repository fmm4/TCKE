package com.example.projetotyche;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddProdActivity extends Activity implements View.OnClickListener{

	
	
	//TTS//
	TextToSpeech ttobj;
	MediaPlayer mediaPlayer = new MediaPlayer();
	//////
	
	Button buttonNew;
	Button buttonList;
	Button buttonBack;
	Button buttonHelp;
	GlobalClass globalVariable;
	
	//STT//
   private SpeechRecognizer sr;
	///////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		globalVariable = ((GlobalClass)getApplicationContext());
		if(globalVariable.isNull()==true){
			globalVariable.start();
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_prod);
		buttonList = (Button) findViewById(R.id.btn_listProd);
		buttonList.setOnClickListener(this);
		buttonBack = (Button) findViewById(R.id.btn_backAddProduto);
		buttonBack.setOnClickListener(this);
		buttonHelp = (Button) findViewById(R.id.btn_helpAddProduto);
		buttonHelp.setOnClickListener(this);
		
		//STT//
		buttonNew = (Button) findViewById(R.id.btn_newProd);
		getActionBar().hide();
		buttonNew.setOnClickListener(this);
		sr = SpeechRecognizer.createSpeechRecognizer(this);       
        sr.setRecognitionListener(new listener());		
		/////////////////
		
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
		getMenuInflater().inflate(R.menu.add_prod, menu);
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
	
		private void listProd(){
		startActivity(new Intent("com.example.projetotyche.ProductList"));
	}
	
	private void backProd(){
		finish();
	}
	
	private void helpProd(){
		Toast.makeText(this, "Ainda nao implementado!",Toast.LENGTH_SHORT).show();
	}
	
	//STT//
	class listener implements RecognitionListener          
	{
		public void onResults(Bundle results)
		{
			String str = new String();
			ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
			for(int i=0;
				i<data.size();
				i++)
				{
					str+= data.get(i);
				}
			globalVariable.addProductToVector(data.get(0).toString());
			for(int a = 0;
				a < globalVariable.getSize();
				a++){
				globalVariable.next();
			}
			speakText(data.get(0).toString()+" foi adicionado ao sistema.");
		}

		@Override
		public void onReadyForSpeech(Bundle params) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBeginningOfSpeech() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRmsChanged(float rmsdB) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBufferReceived(byte[] buffer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEndOfSpeech() {
		}

		@Override
		public void onError(int error) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPartialResults(Bundle partialResults) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEvent(int eventType, Bundle params) {
			// TODO Auto-generated method stub
			
		}
	}

	public void onClick(View v) {
            if (v.getId() == R.id.btn_newProd) 
            {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);        
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"com.example.projetotyche");

                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5); 
                     sr.startListening(intent);
            }
            if(v.getId() == R.id.btn_listProd)
            {
            	listProd();
            }
            if(v.getId() == R.id.btn_helpAddProduto)
            {
            	helpProd();
            }
            if(v.getId() == R.id.btn_backAddProduto)
            {
            	backProd();
            }
    }
	///////
	
	//TTS//
	   @Override
	   public void onPause(){
	      if(ttobj !=null){
	         ttobj.stop();
	         ttobj.shutdown();
	      }
	      super.onPause();
	   }

	   
	public void speakText(String speak){
		String toSpeak1 = speak;
		ttobj.speak(toSpeak1, TextToSpeech.QUEUE_FLUSH, null);

	}
	//////
}
