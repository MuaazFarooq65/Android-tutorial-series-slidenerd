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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
				
				NodeList itemsList= rootElement.getElementsByTagName("item");//obter todos items num arrayList
				NodeList itemChildren= null;//filhos do no actual
				Node currentItem=null;
				Node currentChild=null;
				
				int countImage=0;
				
				for (int i = 0; i < itemsList.getLength(); i++) {
					currentItem= itemsList.item(i);//obter item na posicao dada
					//Log.d("andre", currentItem.getNodeName());
					itemChildren= currentItem.getChildNodes();
					for (int j = 0; j < itemChildren.getLength(); j++) {
						currentChild= itemChildren.item(j);
						//Log.d("andre", currentChild.getNodeName());
						if (currentChild.getNodeName().equalsIgnoreCase("title")) {
							//Log.d("andre", currentChild.getTextContent());
						} if (currentChild.getNodeName().equalsIgnoreCase("pubDate")) {
							//Log.d("andre", currentChild.getTextContent());
						} if (currentChild.getNodeName().equalsIgnoreCase("description")) {
							//Log.d("andre", currentChild.getTextContent());
						} if (currentChild.getNodeName().equalsIgnoreCase("media:thumbnail")) {
							//qeremos url da 2 imagem pq do tamanho
							countImage++;
							if (countImage==2) {
								Log.d("andre",currentChild.getAttributes().item(0).getTextContent());//width=150
							}
						}
					}
					countImage=0;
				}
				
			} catch (ParserConfigurationException e) {
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    }
}