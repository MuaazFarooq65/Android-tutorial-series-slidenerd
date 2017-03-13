package com.example.vivzintentstest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.intentsexample.R;

public class MainActivity extends Activity {
	
	private File imageFile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void process(View view) throws IOException {

		Intent intent = null, chooser = null;

		if (view.getId() == R.id.launchMap) {
			// lat 19.076 long:72.8777
			// definimos intent com a acao standart de vermos algo ACTION_VIEW
			intent = new Intent(android.content.Intent.ACTION_VIEW);
			
			// qeremos lançar mapa com algumas coodenadas
			intent.setData(Uri.parse("geo:37.1900006, -7.4155743"));
			
			//nos emuladores nao temos app para abrir
			//temos criar um chooser
			chooser = intent.createChooser(intent, "Launch Maps");
			startActivity(chooser);
		} else if (view.getId() == R.id.launchMarket) {
			intent = new Intent(android.content.Intent.ACTION_VIEW);
			intent.setData(Uri.parse("market://details?id=dolphin.developers.com"));
			chooser = Intent.createChooser(intent, "Lauch Market");
			startActivity(intent);
		} else if (view.getId() == R.id.sendEmail) {
			intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.setData(Uri.parse("mailto:"));
			String[] to = {"andremlsantos@gmail.com, andremlsantos@live.com"};
			intent.putExtra(Intent.EXTRA_EMAIL, to);
			intent.putExtra(Intent.EXTRA_SUBJECT, "boas, este email foi enviado pela minha APP");
			intent.putExtra(Intent.EXTRA_TEXT, "Vai trabalhar vadiu!");
			//temos meter mime type para q haja app que sejam capazes lidar com putExtra deste intent
			intent.setType("message/rfc822");
			chooser = intent.createChooser(intent, "Send Email");
			startActivity(chooser);
		} else if(view.getId() == R.id.sendImage){
			Uri image = Uri.parse("android.resource://com.example.intentsexample/drawable/"+R.drawable.ic_launcher);
			intent =  new Intent(Intent.ACTION_SEND);
			//q tipo dados vamos enviar para que as apps apropriedas sejam selecionadas
			//mime type
			intent.setType("image/*");
			intent.putExtra(Intent.EXTRA_STREAM, image);
			intent.putExtra(Intent.EXTRA_TEXT, "Eu meti esta imagem");
			chooser = intent.createChooser(intent, "Send Image");
			startActivity(chooser);
		} else if(view.getId() == R.id.sendMultipleImages){
			File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			String[] listOfPictures = pictures.list();
			
			Uri uri = null;
			ArrayList<Uri> arrayList = new ArrayList<Uri>();
			for (String picture : listOfPictures) {
				uri = Uri.parse("file://"+pictures.toString()+"/"+picture);
				arrayList.add(uri);
			}
			intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
			intent.setType("image/*");
			intent.putExtra(Intent.EXTRA_STREAM, arrayList);
			chooser = intent.createChooser(intent, "Send Multiple Pictures");
			startActivity(chooser);
		} else if(view.getId() == R.id.takePicture){
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//especificamos directoria
			imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "foto.jpg");
			//obtemos a directoria
			Uri uri = Uri.fromFile(imageFile);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			startActivityForResult(intent, 0);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//temos verificar se requestcode foi igual aqele q mandamos
		//se foi a nossa actividade que enviou o request
		if (requestCode == 0) {
			switch (resultCode) {
			// sera q user cancelo pedido tirar imagem
			case Activity.RESULT_OK:
				if (imageFile.exists()) {
					Toast.makeText(this,
							"File foi salvo em " + imageFile.getAbsolutePath(),
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(this, "File não foi salvo ",
							Toast.LENGTH_LONG).show();
				}
				break;
			case Activity.RESULT_CANCELED:
				Toast.makeText(this, "File não foi salvo ", Toast.LENGTH_LONG)
						.show();
				break;
			default:
				break;
			}
		}
	}
}