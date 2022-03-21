package com.drugs_app_v1.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import com.cazaea.sweetalert.SweetAlertDialog;
public class Loading {
    private Activity activity;
    private AlertDialog dialog;
    Loading (Activity myActivity) {
        activity = myActivity;
    }
    void startioadingDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }
            void dismissDialog (){
        dialog.dismiss ();

}
    public void showProgress(){
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);

    }
}
