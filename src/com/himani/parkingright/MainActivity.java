package com.himani.parkingright;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private TextView mAddText;
	private EditText mSearchText;
	private AutoCompleteTextView mSearchAutoCompletetext;
	
	private String [] plates = {"C75657","A78579","B79897","A98298"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAddText = (TextView) findViewById(R.id.add_text);

		mAddText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		mSearchAutoCompletetext = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,plates);
		mSearchAutoCompletetext.setAdapter(adapter);
	    mSearchAutoCompletetext.setThreshold(1);
	    mSearchAutoCompletetext.setOnItemClickListener(new OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
	            Object item = parent.getItemAtPosition(position);
	            if (item instanceof String){
	            String plate =(String) item;
	                cacheaccoutn
	            }
	        }
	    });
	}

	
}
