package com.himani.parkingright;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class AddCarFragment extends DialogFragment implements OnEditorActionListener {


	    private EditText mPlateText;
	    private EditText mMakeText;
	    private EditText mModelText;
	    public static final String DELIMITER = "~";

	    public interface AddCarDialogListener {
	        void onFinishEditDialog(String inputText);
	    }


	    public AddCarFragment() {
	        // Empty constructor required for DialogFragment
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.add_car_fragment, container);
	        mPlateText = (EditText) view.findViewById(R.id.txt_car_plate);
	        mMakeText = (EditText) view.findViewById(R.id.txt_car_make);
	        mModelText = (EditText) view.findViewById(R.id.txt_car_model);

	        getDialog().setTitle("Add New Car");

	        // Show soft keyboard automatically
	        mPlateText.requestFocus();
	        getDialog().getWindow().setSoftInputMode(
	                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
	        mModelText.setOnEditorActionListener(this);
	        
	        return view;
	    }
	
	    @Override
	    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	        if (EditorInfo.IME_ACTION_DONE == actionId) {
	            // Return input text to activity
	            AddCarDialogListener activity = (AddCarDialogListener) getActivity();
	            String plate = mPlateText.getText().toString();
	            String make = mMakeText.getText().toString();
	            String model = mModelText.getText().toString();
	            if(plate == null || make == null || model == null)
	            activity.onFinishEditDialog(mPlateText.getText().toString() + DELIMITER + mMakeText.getText().toString() +DELIMITER + mModelText.getText().toString());
	            this.dismiss();
	            return true;
	        }
	        return false;
	    }
}
