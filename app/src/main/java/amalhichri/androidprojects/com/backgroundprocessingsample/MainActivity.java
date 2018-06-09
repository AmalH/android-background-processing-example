package amalhichri.androidprojects.com.backgroundprocessingsample;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {


    private JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jobScheduler = (JobScheduler) (MainActivity.this).getSystemService(JOB_SCHEDULER_SERVICE);

        (findViewById(R.id.startBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName((MainActivity.this), MyBackgroundService.class);
                JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                        .setPeriodic(1500).build();
                jobScheduler.schedule(jobInfo);
            }
        });

        (findViewById(R.id.stopBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobScheduler.cancelAll();
                Toast.makeText(getApplicationContext(), "Service stopped!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
