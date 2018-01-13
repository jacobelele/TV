package com.example.nazam.grandhika;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by nazam on 13/01/18.
 */

public class BackgroundNetwork extends AsyncTask<String, String, String> {

    Context context;
    ProgressDialog progress;

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setTitle("Loading...");
        progress.show();
        super.onPreExecute();
    }

    public BackgroundNetwork(Context activity) {
        context = activity;
    }

    @Override
    protected void onPostExecute(String result) {
        if (progress.isShowing()) {
            progress.dismiss();
        }
        super.onPostExecute(result);
    }


    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
