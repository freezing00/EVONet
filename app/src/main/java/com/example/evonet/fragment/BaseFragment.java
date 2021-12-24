package com.example.evonet.fragment;


import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public void toast(String content){
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

}
