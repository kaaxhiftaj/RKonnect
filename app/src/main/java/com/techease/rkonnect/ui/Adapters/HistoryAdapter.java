package com.techease.rkonnect.ui.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.techease.rkonnect.ui.Models.HistoryModel;

import java.util.ArrayList;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class HistoryAdapter {
    Context context;
    ArrayList<HistoryModel> modelArrayList;
    public HistoryAdapter(Context context, ArrayList<HistoryModel> models) {
        this.context=context;
        this.modelArrayList=models;
    }
}
