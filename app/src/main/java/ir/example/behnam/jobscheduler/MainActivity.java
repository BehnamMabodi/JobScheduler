package ir.example.behnam.jobscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btn_fcfs = (Button) findViewById(R.id.btn_fcfs);
        final Button btn_sjf = (Button) findViewById(R.id.btn_sjf);
        final Button btn_srf = (Button) findViewById(R.id.btn_srf);
        final Button btn_priority = (Button) findViewById(R.id.btn_priority);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btn_fcfs) {
                    Intent intent = new Intent(MainActivity.this, JobActivity.class);
                    intent.putExtra("algorithm", "fcfs");
                    startActivity(intent);

                } else if (view == btn_sjf) {
                    Intent intent = new Intent(MainActivity.this, JobActivity.class);
                    intent.putExtra("algorithm", "sjf");
                    startActivity(intent);

                } else if (view == btn_srf) {
                    Intent intent = new Intent(MainActivity.this, JobActivity.class);
                    intent.putExtra("algorithm", "srt");
                    startActivity(intent);

                } else if (view == btn_priority) {
                    Intent intent = new Intent(MainActivity.this, JobActivity.class);
                    intent.putExtra("algorithm", "priority");
                    startActivity(intent);

                }
            }
        };

        btn_fcfs.setOnClickListener(listener);
        btn_sjf.setOnClickListener(listener);
        btn_srf.setOnClickListener(listener);
        btn_priority.setOnClickListener(listener);

    }
}
