package com.amy.currency.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.amy.currency.BaseActivity;
import com.amy.currency.R;

import com.amy.currency.json.JSon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class MenuActivity extends BaseActivity {
	int width, height;
	private static String url = "http://apilayer.net/api/live?access_key=7855c2ec9864580c8002f450d1d7ddec";
	static public String datajson= "";
	private EditText editCalculator;
	private EditText editCountry1;
	private EditText editCountry2;
	private String calc="";
	private String num1="";
	private String num2="";
	private String result="";
	boolean isnum1Pointed =false;
	boolean isnum2Pointed =false;
	boolean isAdd =false;
	boolean isSub =false;
	boolean isMul =false;
	boolean isDiv = false;
	boolean isCalc =false;
	private Spinner sp1;
	private Spinner sp2;
	
	private ItemData aud;
	private ItemData gbp;
	private ItemData khr;
	private ItemData cad;
	private ItemData cny;
	private ItemData cop;
	private ItemData czk;
	private ItemData dkk;
	private ItemData egp;
	private ItemData hkd;
	private ItemData eur;
	private ItemData inr;
	private ItemData idr;
	private ItemData jpy;
	private ItemData kwd;
	private ItemData lak;
	private ItemData myr;
	private ItemData mxn;
	private ItemData nzd;
	private ItemData php;
	private ItemData rub;
	private ItemData sar;
	private ItemData sgd;
	private ItemData krw;
	private ItemData sek;
	private ItemData chf;
	private ItemData twd;
	private ItemData thb;
	private ItemData Try;
	private ItemData usd;
	private ItemData vnd;
	private ItemData vef;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_menu);
		InputStream inputStream = getResources().openRawResource(R.drawable.data);
		datajson= loadJSONFromResource(inputStream);
		width =Resources.getSystem().getDisplayMetrics().widthPixels;
		height=Resources.getSystem().getDisplayMetrics().heightPixels;
		ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED 
		    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {
			 new JSONParse().execute();
		    // notify user you are online

		}
		editCalculator = (EditText) findViewById(R.id.editText1);
		editCountry1  = (EditText) findViewById(R.id.editText2);
		editCountry2  = (EditText) findViewById(R.id.editText3);
		editCalculator.setText("");
		editCountry1.setText("");
		editCountry2.setText("");
		ArrayList<ItemData> list=new ArrayList<ItemData>();
		aud=new ItemData("AUD\nAustralian Dollar",R.drawable.australia);
		list.add(aud);
		gbp=new ItemData("GBP\nBristish Pound",R.drawable.bristish);
		list.add(gbp);
		 khr=new ItemData("Khr\nCambodia Riel",R.drawable.cambodia);
		list.add(khr);
		 cad=new ItemData("CAD\nCanadian Dollar",R.drawable.canada);
		list.add(cad);
		 cny=new ItemData("CNY\nChinese Yuan", R.drawable.china);
		list.add(cny);
		 cop=new ItemData("COP\nColombian Peso",R.drawable.colombia);
		list.add(cop);
		 czk =new ItemData("CZK\nCzech Koruna",R.drawable.czech);
		list.add(czk);
		 dkk= new ItemData("DKK\nDanish Krone",R.drawable.danmach);
		list.add(dkk);
		 egp=new ItemData("EGP\nEgyptian Pound",R.drawable.aicap);
		list.add(egp);
		 eur=new ItemData("EUR\nEuro", R.drawable.eu);
		list.add(eur);
		 hkd=new ItemData("HKD\nHongKong Dollar",R.drawable.hongcong);
		list.add(hkd);
		 inr=new ItemData("INR\nIndian Rupee",R.drawable.ando);
		list.add(inr);
		 idr=new ItemData("IDR\nIndonesian Rupiah",R.drawable.indo);
		list.add(idr);
		 jpy=new ItemData("JPY\nJapanese Yen",R.drawable.nhat);
		list.add(jpy);
		 kwd=new ItemData("KWD\nKuwaiti Dinar", R.drawable.kuwait);
		list.add(kwd);
		 lak=new ItemData("LAK\nLao Kip",R.drawable.lao);
		list.add(lak);
		 myr=new ItemData("MYR\nMalaysian Ringgit",R.drawable.malai);
		list.add(myr);
		 mxn=new ItemData("MXN\nMexican Peso",R.drawable.mexico);
		list.add(mxn);
		 nzd=new ItemData("NZD\nNew Zealand Dollar",R.drawable.newzealand);
		list.add(nzd);
		 php=new ItemData("PHP\nPhilippine Peso", R.drawable.philippines);
		list.add(php);
		 rub=new ItemData("RUB\nRussian Rouble",R.drawable.nga);
		list.add(rub);
		 sar =new ItemData("SAR\nSaudi Arabian Riyal",R.drawable.arap);
		list.add(sar);
		 sgd=new ItemData("SGD\nSingapore Dollar",R.drawable.singapore);
		list.add(sgd);
		 krw =new ItemData("KWR\nSouth Korean Won",R.drawable.hanquoc);
		list.add(krw);
		 sek= new ItemData("SEK\nSwedish Krona", R.drawable.sweden);
		list.add(sek);
		 chf=new ItemData("CHF\nSwiss Francer",R.drawable.thuysi);
		list.add(chf);
		 twd=new ItemData("TWD\nTaiwan Dollar",R.drawable.taiwan);
		list.add(twd);
		 thb =new ItemData("THB\nThai Baht",R.drawable.thailand);
		list.add(thb);
		 Try=new ItemData("TRY\nTurkish Lira",R.drawable.turque);
		list.add(Try);
		 usd=new ItemData("USD\nUS Dollar", R.drawable.usa);
		list.add(usd);
		 vef=new ItemData("VEF\nVenezuelan Bolivar Fuerte",R.drawable.venezuela);
		list.add(vef);
		 vnd=new ItemData("VND\nVietNam Dong", R.drawable.vietnam);
		list.add(vnd);
		sp1 =(Spinner)findViewById(R.id.spinner1);
		sp2=(Spinner)findViewById(R.id.spinner2);
		SpinnerAdapter adapter=new SpinnerAdapter(this, 
		R.layout.layout_spinner,R.id.txt,list);
		sp1.setAdapter(adapter);
		sp2.setAdapter(adapter);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(!num1.equals(""))editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(!num1.equals(""))editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	

	public void onClickButtonCalculator(View view) {
		switch (view.getId()) {
			case R.id.Button01:
			{
				calc=calc +1;
				if(isCalc) num2= num2+1;
				else num1=num1 +1;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
				
			}break;
			case R.id.Button02:
			{
				calc=calc +2;
				if(isCalc) num2= num2+2;
				else num1=num1 +2;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button03:
			{
				calc=calc +3;
				if(isCalc) num2= num2+3;
				else num1=num1 +3;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button04:
			{
				calc=calc +4;
				if(isCalc) num2= num2+4;
				else num1=num1 +4;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button05:
			{
				calc=calc +5;
				if(isCalc) num2= num2+5;
				else num1=num1 +5;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button06:
			{
				calc=calc +6;
				if(isCalc) num2= num2+6;
				else num1=num1 +6;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button07:
			{
				calc=calc +7;
				if(isCalc) num2= num2+7;
				else num1=num1 +7;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button08:
			{
				calc=calc +8;
				if(isCalc) num2= num2+8;
				else num1=num1 +8;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button09:
			{
				calc=calc +9;
				if(isCalc) num2= num2+9;
				else num1=num1 +9;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button0:
			{
				calc=calc +0;
				if(isCalc) num2= num2+0;
				else num1=num1 +0;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button00:
			{
				calc=calc +0 + 0;
				if(isCalc) num2= num2+0 +0;
				else num1=num1 +0 + 0;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.Button000:
			{
				calc=calc +0 + 0 + 0;
				if(isCalc) num2= num2+0 +0+0;
				else num1=num1 +0 + 0 +0;
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.bangbutton:
			{
				if(isCalc && calc.charAt(calc.length()-1)!='.' && !num2.equals(""))
				{
					if(isAdd)
					{
						result =Cong(num1,num2);
						isAdd=false;
					}
					if(isSub){
						result =Tru(num1,num2);
						isSub=false;
					}
					if(isMul)
					{
						result =Nhan(num1,num2);
						if(numberPointed(result)>8) result=result.substring(0,result.indexOf(".")+6);
						isMul=false;
					}
					if(isDiv)
					{
						if(!deleteZezo(num2).equals("0")&&!deleteZezo(num2).equals("0.0")){
						result =Chia(num1,num2);
						isDiv=false;
						}
					}
					calc =result;
					num1=result;
					if(num1.equals("")) isnum1Pointed=false;
					if(numberPointed(num1)==0)isnum1Pointed=false;
					else isnum1Pointed=true;
					num2="";
					isnum2Pointed=false;
					editCalculator.setText(calc);
					isCalc=false;
				}
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
			}	break;
			case R.id.chiabutton: 
			{
				if(isCalc)
				{
					if(isAdd)
					{
						if(!num2.equals("")){
					result =Cong(num1,num2);
					calc =result;
					editCountry1.setText(num1);
					num1=result;
					if(num1.equals("")) isnum1Pointed=false;
					if(numberPointed(num1)==0)isnum1Pointed=false;
					else isnum1Pointed=true;
					num2="";
					isnum2Pointed=false;

					calc=calc + "÷";
					editCalculator.setText(calc);
				
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "÷";
							editCalculator.setText(calc);
						}
						isAdd=false;
						isCalc=true;
					}
					if(isSub)
					{if( !num2.equals("")){
						result =Tru(num1,num2);
						if(numberPointed(result)>8) result=result.substring(0,result.indexOf(".")+6);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						editCalculator.setText(calc);
						calc=calc + "÷";
						editCalculator.setText(calc);
	
					}
					else{
						calc=calc.substring(0,calc.length()-1);
						calc=calc + "÷";
						editCalculator.setText(calc);
					}
					isSub=false;
					isCalc=true;
					}
					if(isMul)
					{
						if(!num2.equals("")){
						result =Nhan(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "÷";
						editCalculator.setText(calc);
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "÷";
							editCalculator.setText(calc);
						}
						isMul=false;
						isCalc=true;
					}
					if(isDiv)
					{
						if( !num2.equals("")){
						if(!deleteZezo(num2).equals("0")&&!deleteZezo(num2).equals("0.0")){
						result =Chia(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "÷";
						editCalculator.setText(calc);
						isCalc=true;
						}
						}
					}
					isDiv=true;
				}
				else{
					if(!num1.equals("") ){
					isCalc=true;
					isDiv=true;
					calc=calc + "÷";
					editCalculator.setText(calc);
					}
				}
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
			}	break;
			case R.id.clrbutton: 
			{
				calc="";
				num1="";
				num2="";
				isnum1Pointed=false;
				isnum2Pointed=false;
				isCalc=false;
				isAdd=false;
				isDiv=false;
				isMul=false;
				result="";
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
			}	break;
			case R.id.congbutton: 
			{
				if(isCalc)
				{
					if(isAdd)
					{
						if( !num2.equals("")){
					result =Cong(num1,num2);
					calc =result;
					editCountry1.setText(num1);
					num1=result;
					if(num1.equals("")) isnum1Pointed=false;
					if(numberPointed(num1)==0)isnum1Pointed=false;
					else isnum1Pointed=true;
					num2="";
					isnum2Pointed=false;
					calc=calc + "+";
					editCalculator.setText(calc);
					isCalc=true;
						}
					}
					if(isSub)
					{
						if( !num2.equals("")){
						result =Tru(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "+";
						editCalculator.setText(calc);
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "+";
							editCalculator.setText(calc);
						}
						isSub=false;
						isCalc=true;
					}
					if(isMul)
					{if( !num2.equals("")){
						result =Nhan(num1,num2);
						if(numberPointed(result)>8) result=result.substring(0,result.indexOf(".")+6);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "+";
						editCalculator.setText(calc);
					}
					else{
						calc=calc.substring(0,calc.length()-1);
						calc=calc + "+";
						editCalculator.setText(calc);
					}
					isMul=false;
					isCalc=true;
					}
					if(isDiv)
					{if(!deleteZezo(num2).equals("0")&&!deleteZezo(num2).equals("0.0") && !num2.equals("")){
						result =Chia(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "+";
						editCalculator.setText(calc);
					}
					else{
						calc=calc.substring(0,calc.length()-1);
						calc=calc + "+";
						editCalculator.setText(calc);
					}
					isDiv=false;
					isCalc=true;
					}
					isAdd=true;
				}
				else{
					if(!num1.equals("")){
					isCalc=true;
					isAdd=true;
					calc=calc + "+";
					editCalculator.setText(calc);
					}
				}
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
			}	break;
			case R.id.delbutton: 
			{
				if(!calc.equals("")){
				calc=calc.substring(0, calc.length()-1);
				if(isCalc)
					{
						if(!num2.equals("")){
						if('.'==num2.charAt(num2.length()-1)) isnum2Pointed=false;
						num2=num2.substring(0, num2.length()-1);
						}
						else {
							isCalc=false;
							isAdd=false;
							isDiv=false;
							isMul=false;
							isSub=false;
						}
					}
				else{
					if('.'==num1.charAt(num1.length()-1)) isnum1Pointed=false;
						num1=num1.substring(0, num1.length()-1);
					}
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
				editCalculator.setText(calc);
				}
			}	break;

			case R.id.trubutton: 
			{
				if(isCalc)
				{
					if(isAdd)
					{
						if( !num2.equals("")){
					result =Cong(num1,num2);
					calc =result;
					editCountry1.setText(num1);
					num1=result;
					num2="";
					if(num1.equals("")) isnum1Pointed=false;
					if(numberPointed(num1)==0)isnum1Pointed=false;
					else isnum1Pointed=true;
					isnum2Pointed=false;

					calc=calc + "-";
					editCalculator.setText(calc);
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "-";
							editCalculator.setText(calc);
						}
						isAdd=false;
						isCalc=true;
					}
					if(isSub)
					{
						if(!num2.equals("")){
						result =Tru(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "-";
						editCalculator.setText(calc);
						isCalc=true;
						}
					}
					if(isMul)
					{if(!num2.equals("")){
						result =Nhan(num1,num2);
						if(numberPointed(result)>8) result=result.substring(0,result.indexOf(".")+6);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "-";
						editCalculator.setText(calc);
					}
					else{
						calc=calc.substring(0,calc.length()-1);
						calc=calc + "-";
						editCalculator.setText(calc);
					}
					isMul=false;
					isCalc=true;
					}
					if(isDiv)
					{
						if(!deleteZezo(num2).equals("0")&&!deleteZezo(num2).equals("0.0")&&!num1.equals("") && !num2.equals("")){
						result =Chia(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "-";
						editCalculator.setText(calc);
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "-";
							editCalculator.setText(calc);
						}
						isDiv=false;
						isCalc=true;
					}
					isSub=true;
				}
				else{
					if(!num1.equals("") ){
					isCalc=true;
					isSub=true;
					calc=calc + "-";
					editCalculator.setText(calc);
					}
				}
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
			}	break;

			case R.id.nhanbutton: 
			{
				if(isCalc)
				{
					if(isAdd)
					{
						if( !num2.equals("")){
					result =Cong(num1,num2);
					calc =result;
					editCountry1.setText(num1);
					num1=result;
					if(num1.equals("")) isnum1Pointed=false;
					if(numberPointed(num1)==0)isnum1Pointed=false;
					else isnum1Pointed=true;
					num2="";
					isnum2Pointed=false;
					calc=calc + "X";
					editCalculator.setText(calc);
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "X";
							editCalculator.setText(calc);
						}
						isAdd=false;
						isCalc=true;
					}
					if(isSub)
					{
						if( !num2.equals("")){
						result =Tru(num1,num2);
						if(numberPointed(result)>8) result=result.substring(0,result.indexOf(".")+6);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						editCalculator.setText(calc);
						calc=calc + "X";
						editCalculator.setText(calc);
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "X";
							editCalculator.setText(calc);
						}
						isAdd=false;
						isCalc=true;
					}
					if(isMul)
					{
						if( !num2.equals("")){
						result =Nhan(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "X";
						editCalculator.setText(calc);
						isCalc=true;
						}
					}
					if(isDiv)
					{
						if(!deleteZezo(num2).equals("0")&&!deleteZezo(num2).equals("0.0") && !num2.equals("")){
						result =Chia(num1,num2);
						calc =result;
						editCountry1.setText(num1);
						num1=result;
						if(num1.equals("")) isnum1Pointed=false;
						if(numberPointed(num1)==0)isnum1Pointed=false;
						else isnum1Pointed=true;
						num2="";
						isnum2Pointed=false;
						calc=calc + "X";
						editCalculator.setText(calc);
						isCalc=true;
						isDiv=false;
						}
						else{
							calc=calc.substring(0,calc.length()-1);
							calc=calc + "X";
							editCalculator.setText(calc);
						}
						isAdd=false;
						isCalc=true;
					}
					isMul=true;
				}
				else{
					if(!num1.equals("") ){
					isCalc=true;
					isMul=true;
					calc=calc + "X";
					editCalculator.setText(calc);
					}
				}
				editCountry1.setText(num1);
				if(!num1.equals("")) editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1));
				else editCountry2.setText("");
			}	break;
			case R.id.chambutton:
			{
			
			if(!num1.equals("") && (!isnum1Pointed ||!isnum2Pointed)){
			if(isCalc && !isnum2Pointed){ 
				if(num2.equals("")){
					num2=num2+"0.";
					calc=calc+"0.";
				}
				else{
					num2= num2+".";
					calc=calc+".";
				}
				isnum2Pointed=true;
			}
			if(!isCalc && !isnum1Pointed){ 
				if(num1.equals("")){
					num1=num1+"0.";
					calc=calc+"0.";
				}
				else{
					num1=num1 +".";
					calc=calc +".";
				}
				isnum1Pointed=true;
			}
			editCalculator.setText(calc);
			}
			editCountry1.setText(num1);
			}
				break;
			case R.id.timeicon:
			{
				final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
				if (activeNetwork != null && activeNetwork.isConnected()) {
					 new JSONParse().execute();
				} 
				if(!num1.equals(""))
				editCountry2.setText(convertCurrency(datajson, sp1, sp2, num1)); 
			}
			break;
			case R.id.chuyendoi:
			{
				int a=sp2.getSelectedItemPosition();
				sp2.setSelection(sp1.getSelectedItemPosition());
				sp1.setSelection(a);
			}
			break;
			case R.id.button_share:
			{
				shareViaFacebook("https://play.google.com/store/apps/details?id="+getPackageName());
			}
			break;
		}
	}
	public String Cong(String num1,String num2)
	{
		String temp1 ,temp2,result; 
	if(num1.indexOf(".")<0 ||num2.indexOf(".")<0)
		{
			if(num1.indexOf(".")<0)
			{
				num1=num1+".0";
			}
			if(num2.indexOf(".")<0)
			{
				num2=num2+".0";
			}			
		}
		
		if( numberPointed(num1)> numberPointed(num2))
		{
			int temp=numberPointed(num2);
			for(int i=0;i<numberPointed(num1)- temp;i++)
			{
				num2=num2+0;
			}
		}
		if( numberPointed(num2)> numberPointed(num1))
		{
			int temp= numberPointed(num1);
			for(int i=0;i<numberPointed(num2)-temp;i++)
				num1=num1+0;
		}
		int size1 = num1.length();
		int size2 = num2.length();

		if(size1>size2) {
			temp1 = "" + num1;
			temp2 = "" + num2;
		}
		else
		{
			temp1 = "" + num2;
			temp2 = "" + num1;
		}
		result="";
		int rel=0;
		for(int i=1;i<=temp2.length();i++)
		{
		if('.'!=temp2.charAt(temp2.length()-i))
		{
			
		 rel += Integer.parseInt(""+temp1.charAt(temp1.length()-i)) + Integer.parseInt(""+temp2.charAt(temp2.length()-i));
		 if(rel<=9)
		 	{
			 	result =""+rel +result ;
			 	rel=0;
			 }
		 else
		 	{
			 result ="" + (rel%10)+ result;
			 	rel=1;
		 	}
		}
		else result ="." +result;
		}
		for(int j = temp2.length()+1;j<=temp1.length();j++)
		{
			rel += Integer.parseInt(""+temp1.charAt(temp1.length()-j)) ;
			if(rel<=9)
		 	{
			 	result =""+rel +result ;
			 	rel=0;
			 }
		 else
		 	{
			 result ="" + (rel%10)+ result;
			 	rel=1;
		 	}
		}
		num1=deleteZezo(num1);
		num2=deleteZezo(num2);
		if(1==rel) result ="" +1 +result;
		result=deleteZezo(result);
		if(result.charAt(result.length()-1)=='0'&&result.charAt(result.length()-2)=='.') result=result.substring(0,result.length()-2);
		return result;
	}
	public String Tru(String num1,String num2)
	{
		String result;
		num1=deleteZezo(num1);
		num2=deleteZezo(num2);		
		if(num1.indexOf(".")<0 ||num2.indexOf(".")<0)
		{
			if(num1.indexOf(".")<0)
			{
				num1=num1+".0";
			}
			if(num2.indexOf(".")<0)
			{
				num2=num2+".0";
			}			
		}
		if( numberPointed(num1)> numberPointed(num2))
		{
			int temp=numberPointed(num2);
			for(int i=0;i<numberPointed(num1)-temp ;i++)
				num2=num2+0;
		}
		if( numberPointed(num2)> numberPointed(num1))
		{
			int temp=numberPointed(num1);
			for(int i=0;i<numberPointed(num2)- temp;i++)
				num1=num1+0;
		}
		int size1 = num1.length();
		int size2 = num2.length();
		if(size1<size2) {
			return "" + 0;
		}
		if(size1==size2)
		{
			for(int k=0;k<size1;k++)
			{
				if('.'!=num1.charAt(k))
				{
				if(Integer.parseInt(""+num1.charAt(k))> Integer.parseInt(""+num2.charAt(k)))
					break;
				if(Integer.parseInt(""+num1.charAt(k))< Integer.parseInt(""+num2.charAt(k)))
					return ""+0;
				}
			}
		}
		else
		{
			
		}
		result="";
		int rel=0;
		for(int i=1;i<=size2;i++)
		{
			if('.'!=num1.charAt(size1-i))
			{
			int temp1=Integer.parseInt(""+num1.charAt(size1-i));
			int temp2= Integer.parseInt(""+num2.charAt(size2-i));
			if(temp1>=(temp2 +rel))
				{
					rel = temp1 - temp2 -rel ;
					result =""+rel +result ;
					rel=0;
				}
			else
			{
				rel = temp1 +10 -temp2 -rel;
				result =""+rel +result ;
				rel=1;
			}
			}
			else result ="." +result;
		}
		for(int j = size2+1;j<=size1;j++)
		{
			if(Integer.parseInt(""+num1.charAt(num1.length()-j))>=rel)
				{
					rel = Integer.parseInt(""+num1.charAt(num1.length()-j)) -rel;
					result =""+rel +result ;
					rel=0;
				}
			else
			{
				rel = Integer.parseInt(""+num1.charAt(num1.length()-j))+10 -rel;
				result =""+rel +result ;
				rel=1;
			}
		}
		if(1==rel) result ="" +0;
		num1=deleteZezo(num1);
		num2=deleteZezo(num2);
		result=deleteZezo(result);
		return result;
	}
	public String Nhan(String num1,String num2)
	{
		num1=deleteZezo(num1);
		num2=deleteZezo(num2);
		int numberpointed1=numberPointed(num1);
		int numberpointed2=numberPointed(num2);
		String temp=num2;
		if(0!=numberpointed2) temp=temp.replace(".", "");
		String result =Nhan(num1,Integer.parseInt(""+temp.charAt(0)));
		int size =temp.length();
		for(int i=1;i<size;i++)
		{
				result = Nhan(result,10);
				result =Cong(result,Nhan(num1,Integer.parseInt(""+temp.charAt(i))));
			
		}
		if(0!=numberpointed2)
			{
				if(0!=numberpointed1)
					result=result.substring(0,result.indexOf(".")-numberpointed2)+"."+result.substring(result.indexOf(".")-numberpointed2, result.length()).replace(".", "");
				else
					result=result.substring(0,result.length()-numberpointed2)+"."+result.substring(result.length()-numberpointed2, result.length());
			}
		if(result.length()>=2)if(result.charAt(result.length()-1)=='0'&&result.charAt(result.length()-2)=='.') result=result.substring(0,result.length()-2);
		result=deleteZezo(result);
		return result;
		
	}
	public String Nhan(String num,int n)
	{
		num=deleteZezo(num);
		if(0==n) return ""+0;
		if(1==n) return num;
		String result="";
		String temp=num;
		for(int i=2;i<=n;i++)
		{
			result = Cong(temp,num);
			temp="" + result;
		}
		return result;
	}
	public String Chia(String num1,String num2)
	{
		String q= "0";
		num1=deleteZezo(num1);
		num2=deleteZezo(num2);
		String a=num1;
		String b=num2;
		
		int pointed1=numberPointed(num1);
		int pointed2=numberPointed(num2);
		int count=pointed1;
		if(pointed1<pointed2) count=pointed2;
		for(int i=0;i<count;i++)
		{
			a=Nhan(a,"10");
			b=Nhan(b,"10");
		}
		int size1=a.length();
		int size2=b.length();
		count=0;
		if(size2>size1)
		{
			int k=size2-size1;
			size1=size2+1;
			count=k+1;
			for(int j=0;j<=k;j++)
				a=Nhan(a,"10");
		}
		a=deleteZezo(a);
		b=deleteZezo(b);
		String r=a.substring(0,size2);
		String t=a.substring(size2,size1);
		int isbreak=0;
		if(0==numberPointed(b))b=b+".0";
		r=r+".0";
		while(true)
		{
			if(r.equals(b))
			{
				r=Tru(r,b);
				q=Cong(q,"1");
				if(t.equals(""))
					break;	
			}
			if(Tru(r,b).equals("0")||Tru(r,b).equals("0.0"))
			{
				if(!t.equals("")){
				r=Cong(Nhan(r,"10"),t.substring(0,1));
				t=t.substring(1);
				}
				else
				{
				r=deleteZezo(r);
					if(r.equals("0.0"))
						break;
					r=Nhan(r,"10");
					count++;
					isbreak++;
					
				}
				q=Nhan(q,"10");
			}
			if(9==isbreak)
				break;
			if(!Tru(r,b).equals("0")){ r=Tru(r,b);
			q=Cong(q,"1");
			}
		}
		for(int m=0;m<count;m++)
		{
			if(0==numberPointed(q)) q=q.substring(0,q.length()-1)+"."+q.substring(q.length()-1);
			else q=q.substring(0,q.indexOf(".")-1)+"."+q.substring(q.indexOf(".")-1).replace(".", "");
			if('.'==q.charAt(0)) q="0"+q;
		}
		q=deleteZezo(q);
		return q;
	}
	public String deleteZezo(String num)
	{
		String result = num;
		if(!result.equals("")){
		int count =result.length();
		for(int i=0;i<count-1;i++)
		if('0'==result.charAt(0))
		{
			result=result.substring(1);
		}
		if('.'==result.charAt(0))
			result="0"+result;
		
		int k =result.length()-1;
		for(int j=0;j<k;j++)
		{
			if('.'==result.charAt(j))
			{
				while('0'==result.charAt(result.length()-1))
				{
					if('.'!=result.charAt(result.length()-2)) result = result.substring(0, result.length()-1);
					else break;
				}
				break;
			}
		}
		}
		return result;
	}
	public int numberPointed(String num)
	{
		int count=0;
		for(int i=0;i<num.length();i++)
		{
			if('.'==num.charAt(i))
			{
				for(int j=i+1;j<num.length();j++)
					count++;
				break;
			}
		}
		return count;
	}
	public String loadJSONFromResource(InputStream is) {
		 String json = null;
		    try {
		        int size = is.available();
		        byte[] buffer = new byte[size];
		        is.read(buffer);
		        is.close();
		        json = new String(buffer, "UTF-8");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        return null;
		    }
		    return json;
    }
	public String convertCurrency(String data,Spinner sp1,Spinner sp2,String num1)
	{
		String rate1="";
		String rate2="";
		try {
			JSONObject obj = new JSONObject(data);
			JSONObject json=obj.getJSONObject("quotes");
			if(sp1.getSelectedItem().equals(aud)) rate1=""+ json.getDouble("USDAUD");
			if(sp2.getSelectedItem().equals(aud)) rate2=""+ json.getDouble("USDAUD");
			if(sp1.getSelectedItem().equals(gbp)) rate1=""+ json.getDouble("USDGBP");
			if(sp2.getSelectedItem().equals(gbp)) rate2=""+ json.getDouble("USDGBP");
			if(sp1.getSelectedItem().equals(khr)) rate1=""+ json.getDouble("USDKHR");
			if(sp2.getSelectedItem().equals(khr)) rate2=""+ json.getDouble("USDKHR");
			if(sp1.getSelectedItem().equals(cad)) rate1=""+ json.getDouble("USDCAD");
			if(sp2.getSelectedItem().equals(cad)) rate2=""+ json.getDouble("USDCAD");
			if(sp1.getSelectedItem().equals(cny)) rate1=""+ json.getDouble("USDCNY");
			if(sp2.getSelectedItem().equals(cny)) rate2=""+ json.getDouble("USDCNY");
			if(sp1.getSelectedItem().equals(cop)) rate1=""+ json.getDouble("USDCOP");
			if(sp2.getSelectedItem().equals(cop)) rate2=""+ json.getDouble("USDCOP");
			if(sp1.getSelectedItem().equals(czk)) rate1=""+ json.getDouble("USDCZK");
			if(sp2.getSelectedItem().equals(czk)) rate2=""+ json.getDouble("USDCZK");
			if(sp1.getSelectedItem().equals(dkk)) rate1=""+ json.getDouble("USDDKK");
			if(sp2.getSelectedItem().equals(dkk)) rate2=""+ json.getDouble("USDDKK");
			if(sp1.getSelectedItem().equals(egp)) rate1=""+ json.getDouble("USDEGP");
			if(sp2.getSelectedItem().equals(egp)) rate2=""+ json.getDouble("USDEGP");
			if(sp1.getSelectedItem().equals(eur)) rate1=""+ json.getDouble("USDEUR");
			if(sp2.getSelectedItem().equals(eur)) rate2=""+ json.getDouble("USDEUR");
			if(sp1.getSelectedItem().equals(hkd)) rate1=""+ json.getDouble("USDHKD");
			if(sp2.getSelectedItem().equals(hkd)) rate2=""+ json.getDouble("USDHKD");
			if(sp1.getSelectedItem().equals(inr)) rate1=""+ json.getDouble("USDINR");
			if(sp2.getSelectedItem().equals(inr)) rate2=""+ json.getDouble("USDINR");
			if(sp1.getSelectedItem().equals(idr)) rate1=""+ json.getDouble("USDIDR");
			if(sp2.getSelectedItem().equals(idr)) rate2=""+ json.getDouble("USDIDR");
			if(sp1.getSelectedItem().equals(jpy)) rate1=""+ json.getDouble("USDJPY");
			if(sp2.getSelectedItem().equals(jpy)) rate2=""+ json.getDouble("USDJPY");
			if(sp1.getSelectedItem().equals(kwd)) rate1=""+ json.getDouble("USDKWD");
			if(sp2.getSelectedItem().equals(kwd)) rate2=""+ json.getDouble("USDKWD");
			if(sp1.getSelectedItem().equals(lak)) rate1=""+ json.getDouble("USDLAK");
			if(sp2.getSelectedItem().equals(lak)) rate2=""+ json.getDouble("USDLAK");
			if(sp1.getSelectedItem().equals(myr)) rate1=""+ json.getDouble("USDMYR");
			if(sp2.getSelectedItem().equals(myr)) rate2=""+ json.getDouble("USDMYR");
			if(sp1.getSelectedItem().equals(mxn)) rate1=""+ json.getDouble("USDMXN");
			if(sp2.getSelectedItem().equals(mxn)) rate2=""+ json.getDouble("USDMXN");
			if(sp1.getSelectedItem().equals(nzd)) rate1=""+ json.getDouble("USDNZD");
			if(sp2.getSelectedItem().equals(nzd)) rate2=""+ json.getDouble("USDNZD");
			if(sp1.getSelectedItem().equals(php)) rate1=""+ json.getDouble("USDPHP");
			if(sp2.getSelectedItem().equals(php)) rate2=""+ json.getDouble("USDPHP");
			if(sp1.getSelectedItem().equals(rub)) rate1=""+ json.getDouble("USDRUB");
			if(sp2.getSelectedItem().equals(rub)) rate2=""+ json.getDouble("USDRUB");
			if(sp1.getSelectedItem().equals(sar)) rate1=""+ json.getDouble("USDSAR");
			if(sp2.getSelectedItem().equals(sar)) rate2=""+ json.getDouble("USDSAR");
			if(sp1.getSelectedItem().equals(sgd)) rate1=""+ json.getDouble("USDSGD");
			if(sp2.getSelectedItem().equals(sgd)) rate2=""+ json.getDouble("USDSGD");
			if(sp1.getSelectedItem().equals(krw)) rate1=""+ json.getDouble("USDKRW");
			if(sp2.getSelectedItem().equals(krw)) rate2=""+ json.getDouble("USDKRW");
			if(sp1.getSelectedItem().equals(sek)) rate1=""+ json.getDouble("USDSEK");
			if(sp2.getSelectedItem().equals(sek)) rate2=""+ json.getDouble("USDSEK");
			if(sp1.getSelectedItem().equals(chf)) rate1=""+ json.getDouble("USDCHF");
			if(sp2.getSelectedItem().equals(chf)) rate2=""+ json.getDouble("USDCHF");
			if(sp1.getSelectedItem().equals(twd)) rate1=""+ json.getDouble("USDTWD");
			if(sp2.getSelectedItem().equals(twd)) rate2=""+ json.getDouble("USDTWD");
			if(sp1.getSelectedItem().equals(thb)) rate1=""+ json.getDouble("USDTHB");
			if(sp2.getSelectedItem().equals(thb)) rate2=""+ json.getDouble("USDTHB");
			if(sp1.getSelectedItem().equals(Try)) rate1=""+ json.getDouble("USDTRY");
			if(sp2.getSelectedItem().equals(Try)) rate2=""+ json.getDouble("USDTRY");
			if(sp1.getSelectedItem().equals(usd)) rate1="1";
			if(sp2.getSelectedItem().equals(usd)) rate2="1";
			if(sp1.getSelectedItem().equals(vef)) rate1=""+ json.getDouble("USDVEF");
			if(sp2.getSelectedItem().equals(vef)) rate2=""+ json.getDouble("USDVEF");
			if(sp1.getSelectedItem().equals(vnd)) rate1=""+ json.getDouble("USDVND");
			if(sp2.getSelectedItem().equals(vnd)) rate2=""+ json.getDouble("USDVND");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		String result="";
		if(!rate1.equals("")&&!rate2.equals(""))
		{
		if(rate1.equals(rate2)) return num1;
		result=Chia(Nhan(num1,rate2),rate1);
		}return result;
	}

	private class JSONParse extends AsyncTask<String, String, JSONObject> {
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
          

       }

       @Override
       protected JSONObject doInBackground(String... args) {
           JSon jParser = new JSon();

           // Getting JSON from URL
           JSONObject json = jParser.getJSONFromUrl(url);
           return json;
       }
        @Override
        protected void onPostExecute(JSONObject json) {


                   datajson = json.toString();
            
        }
   }
	public final void shareViaFacebook(String urlToShare) {// String urlToShare
		  // =
		  // "https://play.google.com/store/apps/details?id="+getPackageName();
		  Intent intent = new Intent(Intent.ACTION_SEND);
		  intent.setType("text/plain");
		  // intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no
		  // effect!
		  intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

		  // See if official Facebook app is found
		  boolean facebookAppFound = false;
		  List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
		  for (ResolveInfo info : matches) {
		   if (info.activityInfo.packageName.equals("com.facebook.katana")) {
		    intent.setPackage(info.activityInfo.packageName);
		    startActivity(intent);
		    return;
		   }
		  }
		  for (ResolveInfo info : matches) {
		   if (info.activityInfo.packageName.equals("com.google.android.apps.plus")) {
		    intent.setPackage(info.activityInfo.packageName);
		    startActivity(intent);
		    return;
		   }
		  }
		  for (ResolveInfo info : matches) {
		   if (info.activityInfo.packageName.equals("com.instagram.android")) {
		    intent.setPackage(info.activityInfo.packageName);
		    startActivity(intent);
		    return;
		   }
		  }
		  for (ResolveInfo info : matches) {
		   if (info.activityInfo.packageName.equals("com.twitter.android")) {
		    intent.setPackage(info.activityInfo.packageName);
		    startActivity(intent);
		    return;
		   }
		  }
		  // As fallback, launch sharer.php in a browser
		  if (!facebookAppFound) {
		   String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
		   intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
		  }
		  startActivity(intent);
		 }
}
