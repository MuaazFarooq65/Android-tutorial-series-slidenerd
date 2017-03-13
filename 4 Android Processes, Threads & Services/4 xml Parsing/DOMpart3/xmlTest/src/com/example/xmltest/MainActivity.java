package com.example.xmltest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements ResultsCallBack{
	
	PlaceholderFragment taskFragment;
	ListView articlesListView;
	
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
        articlesListView= (ListView) findViewById(R.id.articlesListView);
    }
    
    //interface
	@Override
	public void onPreExecute() {
		
	}

	//interface
	@Override
	public void onPostExecute(ArrayList<HashMap<String, String>> results) {
		articlesListView.setAdapter(new MyAdapter(this, results));
	}

    
    public static class PlaceholderFragment extends Fragment {
    	
    	TechCrunchTask downloadTask;
    	ResultsCallBack callBack;
    	
    	public PlaceholderFragment(){
    	}
    	
    	@Override
    	public void onAttach(Activity activity) {
    		super.onAttach(activity);
    		callBack=(ResultsCallBack) activity;
    		//temos dizer ao TechCrunch para actualizar seus dados
    		if (downloadTask!=null) {
				downloadTask.onAttack(callBack);
			}
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
				downloadTask= new TechCrunchTask(callBack);
				downloadTask.execute();
			}
    	}
    	
    	@Override
    	public void onDetach() {
    		super.onDetach();
    		callBack=null;//fazemos isto para que onAttack nao haja problemas
    		if (downloadTask!=null) {
				downloadTask.onDettach();
			}
    	}
    }
    
    //Params, Progress, Result
    public static class TechCrunchTask extends AsyncTask<Void,Void, ArrayList<HashMap<String, String>>>{
    	
    	ResultsCallBack callBack= null;
    	DownloadImages download;
    	
    	public TechCrunchTask(ResultsCallBack callBack) {
    		this.callBack=callBack;
		}
    	
    	public void onAttack(ResultsCallBack callBack){
    		this.callBack=callBack;
    	}
    	
    	public void onDettach(){
    		callBack=null;
    	}

    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		if (callBack!=null) {
				callBack.onPreExecute();
			}
    	}
    	
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
			super.onPostExecute(result);
    		if (callBack!=null) {
				callBack.onPostExecute(result);
			}
		}
		
		@Override
		protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
			String downloadURL= "http://feeds.feedburner.com/techcrunch/android?format=xml";
			ArrayList<HashMap<String, String>> results= new ArrayList<HashMap<String,String>>();
			try {
				URL url= new URL(downloadURL);
				HttpURLConnection connection= (HttpURLConnection) url.openConnection();//abrir ligaçao
				connection.setRequestMethod("GET");//TIPO LIGAÇAO
				InputStream inputStream= connection.getInputStream();//abrir input stream para podermos ler dados da conexao
				results= processXml(inputStream);
			} catch (Exception e) {
				Log.d("andre", ""+e);
			}
			return results;
		}
		
		public ArrayList<HashMap<String, String>> processXml(InputStream inputStream){
			DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
			//guardar dados
			ArrayList<HashMap<String, String>> results= new ArrayList<HashMap<String,String>>();
			HashMap<String, String> currentMap= null;
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
					itemChildren= currentItem.getChildNodes();
					currentMap= new HashMap<String, String>();
					for (int j = 0; j < itemChildren.getLength(); j++) {
						currentChild= itemChildren.item(j);
						if (currentChild.getNodeName().equalsIgnoreCase("title")) {
							currentMap.put("title", currentChild.getTextContent());
						} if (currentChild.getNodeName().equalsIgnoreCase("pubDate")) {
							currentMap.put("pubDate", currentChild.getTextContent());
						} if (currentChild.getNodeName().equalsIgnoreCase("description")) {
							currentMap.put("description", currentChild.getTextContent());
						} if (currentChild.getNodeName().equalsIgnoreCase("media:thumbnail")) {
							//qeremos url da 2 imagem pq do tamanho
							countImage++;
							if (countImage==2) {
//								Log.d("andre",currentChild.getAttributes().item(0).getTextContent());//width=150
//								currentMap.put("imageURL", currentChild.getAttributes().item(0).getTextContent());
								download = new DownloadImages(i);
								download.execute(currentChild.getAttributes().item(0).getTextContent());
							}
						}
					}
					if (currentMap!=null && !currentMap.isEmpty()) {
						results.add(currentMap);
					}
					countImage=0;
				}
			} catch (ParserConfigurationException e) {
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return results;
		}
    }
}
