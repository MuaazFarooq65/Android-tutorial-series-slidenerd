package com.example.xmltest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class MainActivity extends ActionBarActivity {
	
	PlaceholderFragment taskFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState== null) {
        	//inicialmente criamos e adicionamos fragment indestrutivel
			taskFragment=new PlaceholderFragment();
			getSupportFragmentManager().beginTransaction().add(taskFragment, "MyFragment").commit();//v4 fragment
		} else {
			//obtemos referencia fragmento
			taskFragment= (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("MyFragment");
		}
        taskFragment.startTask();
    }
    
    public static class PlaceholderFragment extends Fragment {
    	
    	TechCrunchTask downloadTask;
    	public PlaceholderFragment(){
    	}
    	
    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		super.onActivityCreated(savedInstanceState);
    		setRetainInstance(true);//nunca destruir fragmento
    	}
    	
    	//iniciar transferencia
    	public void startTask(){
    		if (downloadTask!=null) {
				downloadTask.cancel(true);
			} else {
				downloadTask= new TechCrunchTask();
				downloadTask.execute();
			}
    	}
    }
    
    //Params, Progress, Result
    public static class TechCrunchTask extends AsyncTask<Void,Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			String downloadURL= "http://feeds.feedburner.com/techcrunch/android?format=xml";
			try {
				URL url= new URL(downloadURL);
				HttpURLConnection connection= (HttpURLConnection) url.openConnection();//abrir ligaçao
				connection.setRequestMethod("GET");//TIPO LIGAÇAO
				InputStream inputStream= connection.getInputStream();//abrir input stream para podermos ler dados da conexao
				processXml(inputStream);
			} catch (Exception e) {
				Log.d("andre", ""+e);
			}
			return null;
		}
		
		public void processXml(InputStream inputStream){
			DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
				Document xmlDocument= documentBuilder.parse(inputStream);
				Element rootElement= xmlDocument.getDocumentElement();//elemento root
				Log.d("andre", ""+rootElement.getTagName());
			} catch (ParserConfigurationException e) {
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    }
}


















