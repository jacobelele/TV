package com.example.nazam.grandhika;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by nazam on 13/01/18.
 */

public class BackgroundNetwork extends AsyncTask<String, String, String> {

    Context context;
    ProgressDialog progress;
    String url;
    Bitmap bitmap;

    @Override
    protected void onPreExecute() {
//        progress = new ProgressDialog(context);
//        progress.setTitle("Loading...");
//        progress.show();
        super.onPreExecute();
    }

    public BackgroundNetwork(Context activity) {
        context = activity;
    }
    public BackgroundNetwork(Context activity,String url) {
        context = activity;
        this.url = url;
    }

    @Override
    protected void onPostExecute(String result) {
//        if (progress.isShowing()) {
//            progress.dismiss();
//        }
        super.onPostExecute(result);
    }

    public String getUrl() {
        return url;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
