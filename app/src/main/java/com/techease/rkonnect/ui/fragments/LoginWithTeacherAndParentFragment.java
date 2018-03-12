package com.techease.rkonnect.ui.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.fragments.Parent.ParentSingInFragment;
import com.techease.rkonnect.ui.fragments.Teacher.LoginTeacherFragment;


public class LoginWithTeacherAndParentFragment extends Fragment {

    Button btnTeacher,btnParent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login_with_teacher_and_parent, container, false);

        btnParent=(Button)view.findViewById(R.id.btnParentLogin);
        btnTeacher=(Button)view.findViewById(R.id.btnTeacherLogin);

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Fragment fragment=new ParentSingInFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
            }
        });

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoginTeacherFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
            }
        });

        return view;
    }
}
