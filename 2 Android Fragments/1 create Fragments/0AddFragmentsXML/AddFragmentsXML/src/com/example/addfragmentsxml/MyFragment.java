package com.example.addfragmentsxml;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

	/*
	 * podemos usar activity e usarmos getSystemServices e findViewById cha,ado
	 * depois do fragmento ter sido linkado a actividade
	 */
	@Override
	public void onAttach(Activity activity) {
		Log.d("VIVZ", "onAttach from Fragment");
		super.onAttach(activity);
	}

	/*
	 * podemos guarddar dados aqui se foi a 1.vez que usamos o fragment é null
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("VIVZ", "onCreate from Fragment");
		super.onCreate(savedInstanceState);
	}

	/*
	 * fazomos o link entre xml e java aqui com LayoutInflater
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("VIVZ", "onCreateView from Fragment");
		return inflater.inflate(R.layout.my_fragment_layout, container, false);
	}

	/*
	 * chamado depois de onCreate(actividade ter terminado) pq que precisamos
	 * disto ? sem o setContentView ter sido estabelecido nao conseguimos aceder
	 * dados UI temos ter certeza que onCreate na actividade terminou antes de
	 * tentarmos acessar elementos da UI da actividade
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d("VIVZ", "onActivityCreated from Fragment");
		super.onActivityCreated(savedInstanceState);
	}

	/*
	 * chamado quando o user começa a ver a APP
	 */
	@Override
	public void onStart() {
		Log.d("VIVZ", "onStart from Fragment");
		super.onStart();
	}

	/*
	 * chamado quando o user começa a ver a APP
	 */
	@Override
	public void onResume() {
		Log.d("VIVZ", "onResume from Fragment");
		super.onResume();
	}

	/*
	 * fragmento esta a ser bloqueado por algo
	 */
	@Override
	public void onPause() {
		Log.d("VIVZ", "onPause from Fragment");
		super.onPause();
	}

	/*
	 * onde podemos guardar dados relativos ao fragmento por exemplo fragmento
	 * de lista qeremos guardar actual elmento selecionado
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d("VIVZ", "onSaveInstanceState from Fragment");
		super.onSaveInstanceState(outState);
	}

	/*
	 * chamado depois fragmento ter sido parado
	 */
	@Override
	public void onStop() {
		Log.d("VIVZ", "onStop from Fragment");
		super.onStop();
	}

	/*
	 * momento onde depois da sua execução ja nao podemos aceder ao fragmento
	 */
	@Override
	public void onDestroyView() {
		Log.d("VIVZ", "onDestroyView from Fragment");
		super.onDestroyView();
	}

	/*
	 * fragmento va comecar a ser destruido
	 */
	@Override
	public void onDestroy() {
		Log.d("VIVZ", "onDestroy from Fragment");
		super.onDestroy();
	}

	/*
	 * fragmento ja nao esta ligado a actividade
	 */
	@Override
	public void onDetach() {
		Log.d("VIVZ", "onDetach from Fragment");
		super.onDetach();
	}
}