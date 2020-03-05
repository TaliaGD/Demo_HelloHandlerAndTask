package be.ehb.demo_hellohandlerandtask.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class ProgressTask extends AsyncTask <Void,Integer, Void>{
//weak reference is noodzakelijk zodat dit naar de achtergrond kan gezet worden
    private WeakReference<ProgressBar> pbReference;
    private WeakReference<Button> btnReference;

    //constructor
    public ProgressTask(ProgressBar pb, Button btnStartTask) {
        pbReference = new WeakReference<>(pb);
        btnReference = new WeakReference<>(btnStartTask);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //1, voorbereidend werk alvorens de taak begint, bv. iets initialiseren
        btnReference.get().setEnabled(false);
    }

//do in background moet overschreven worden!
    @Override
    protected Void doInBackground(Void... niks) {
        //2, voert uit op de achtergrond
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);

        }

        //return naar onPostExecute , komt binnen als parameter
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //3, tussentijdse updates tijdens het uivoeren

        pbReference.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //4, klaar met uitvoer, laatste aanpassingen aan ui
        //opkuisen van geheugen
        btnReference.get().setEnabled(true);
        pbReference = null;
        btnReference = null;
    }
}
