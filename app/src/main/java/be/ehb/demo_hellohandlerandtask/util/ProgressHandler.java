package be.ehb.demo_hellohandlerandtask.util;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ProgressHandler extends Handler {

    private ProgressBar pb;
    private TextView tv;

    //aanmaak constructor
    public ProgressHandler(ProgressBar pb, TextView tv) {
        this.pb = pb;
        this.tv = tv;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);

        int value = msg.arg1;

        if(Build.VERSION.SDK_INT >=25)
            pb.setProgress(value, true);
        else
            pb.setProgress(value);



    }
}
