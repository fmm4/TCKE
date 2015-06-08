package com.example.projetotyche;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class CalcularRota extends Activity implements View.OnClickListener{
	
	//TTS//
	private TextToSpeech ttobj;
	///////
	
	Button buttonCalcRota;
	Button buttonListarProd;
	Button buttonBack;
	Button buttonHelp;
	GlobalClass globalVariable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcular_rota);
		getActionBar().hide();
		
		//Inicializando o vetor, se ainda nao foi inicializado//
		globalVariable = ((GlobalClass)getApplicationContext());
		if(globalVariable.isNull()==true){
			globalVariable.start();
		}
		
		buttonCalcRota = (Button) findViewById(R.id.btn_guiarRota);
		buttonCalcRota.setOnClickListener(this);
		
		buttonListarProd = (Button) findViewById(R.id.btn_falarLista);
		buttonListarProd.setOnClickListener(this);
		
		buttonBack = (Button) findViewById(R.id.btn_backCalcRota);
		buttonBack.setOnClickListener(this);
		
		buttonHelp = (Button) findViewById(R.id.btn_helpCalcRota);
		buttonHelp.setOnClickListener(this);
		
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
		getMenuInflater().inflate(R.menu.calcular_rota, menu);
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
		case R.id.btn_guiarRota:
			String txtRoute = "Caminho gerado,";
			for(int a = 0;
					a < globalVariable.getSize();
					a++){
				txtRoute += "Para achar "+globalVariable.getCurrent()+", ";
				int randomPath = (int) Math.floor(Math.random()*3);
				globalVariable.next();
				switch(randomPath){
					case 0:
						txtRoute += "Ande 3 metros para frente,";
					case 1:
						txtRoute += "Vire a esquerda,";
					case 2:
						txtRoute += "Vire a direita,";
				}
			}
			txtRoute += "Clique novamente se você deseja ouvir novamente.";
			speakText(txtRoute);
		break;
		case R.id.btn_falarLista:
			listProducts();
			break;
		case R.id.btn_backCalcRota:
			finish();
			break;
		case R.id.btn_helpCalcRota:
			speakText("Aqui você pode calcular as rotas dos produtos.");
			break;
		}
	}
	
	public void listProducts(){
		String result = "Produtos na lista:";
		
		for(int a = 0;
				a < globalVariable.getSize();
				a++){
				result += " "+globalVariable.getCurrent();
				if(a<globalVariable.getSize()-1){
					result+=",";
				}else{
					result+=".";
				}
				globalVariable.next();
			}
		
		speakText(result);
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

	   
	public void speakText(String speak){
		String toSpeak1 = speak;
		Toast.makeText(getApplicationContext(), toSpeak1, 
		Toast.LENGTH_SHORT).show();
		ttobj.speak(toSpeak1, TextToSpeech.QUEUE_FLUSH, null);

	}
	//////
}
