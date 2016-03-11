package com.rey.material.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rey.material.widget.ProgressView;

public class BaseFragment extends Fragment{
	
		
	private String fragment_class_tag = "base";
	
	
	public BaseFragment newInstance(){
		BaseFragment fragment = new BaseFragment();
		return fragment;
	}

	public String getFragment_class_tag() {
		return fragment_class_tag;
	}

	public void setFragment_class_tag(String fragment_class_tag) {
		this.fragment_class_tag = fragment_class_tag;
	}



	
}
