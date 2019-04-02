package com.example.mahmoudebrahim.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseDialogFragment extends Fragment {
    BaseDialogActivity activity;
    MaterialDialog dialog;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(BaseDialogActivity)context;
        this.context=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public MaterialDialog showMessage(int titleResId, int contentResId, int positiveMessageResId){

        dialog= activity.showMessage(titleResId,contentResId,positiveMessageResId);
        return dialog;

    }
    public MaterialDialog showMessage(String title, String content, String positiveMessage){
        dialog=activity.showMessage(title,content,positiveMessage);
        return dialog;
    }

    public MaterialDialog showConfirmationMessage(String title, String content, String positiveMessage , MaterialDialog.SingleButtonCallback action){
        dialog=activity.showConfirmationMessage(title,content,positiveMessage,action);
        return dialog;

    }

    public MaterialDialog showConfirmationMessage(int titleResId, int contentResId, int positiveMessageResId , MaterialDialog.SingleButtonCallback action){

        dialog=activity.showConfirmationMessage(titleResId,contentResId,positiveMessageResId,action);
        return dialog;
    }
    public MaterialDialog showProgressBar(String title){
        dialog=activity.showProgressBar(title);
        return dialog;
    }
    public MaterialDialog showProgressBar(int title){
        dialog=activity.showProgressBar(title);
        return dialog;
    }
    public void dismissProgressBar(){

        activity.dismissProgressBar();
    }
}
