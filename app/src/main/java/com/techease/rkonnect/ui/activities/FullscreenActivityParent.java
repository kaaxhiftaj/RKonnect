package com.techease.rkonnect.ui.activities;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.fragments.LoginWithTeacherAndParentFragment;

import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivityParent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen_parent);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        Fragment fragment = new LoginWithTeacherAndParentFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container2, fragment).commit();

    }

}
