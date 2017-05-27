package ir.example.behnam.jobscheduler;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ir.example.behnam.jobscheduler.Class.JobScheduler.FCFS;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Job.NormalJob;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Job.PriorityJob;
import ir.example.behnam.jobscheduler.Class.JobScheduler.PriorityScheduler;
import ir.example.behnam.jobscheduler.Class.JobScheduler.SJF;
import ir.example.behnam.jobscheduler.Class.JobScheduler.SRT;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Scheduler;

public class JobActivity extends AppCompatActivity {

    TextView mTvName;
    TextView mTvLog;
    TextView mTvLog2;
    EditText mEdtArrivalTime;
    EditText mEdtRequiredTime;
    EditText mEdtPriority;

    Button mBtnReset;
    Button mBtnAddJob;
    Button mBtnExecute;

    Scheduler mScheduler;

    boolean mPriorityMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String algorithm = getIntent().getExtras().getString("algorithm");

        if (algorithm.equals("fcfs"))
            mScheduler = new FCFS();
        else if (algorithm.equals("sjf"))
            mScheduler = new SJF();
        else if (algorithm.equals("srt"))
            mScheduler = new SRT();
        else if (algorithm.equals("priority")) {
            mScheduler = new PriorityScheduler();
            mPriorityMode = true;
        }


        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvLog = (TextView) findViewById(R.id.tv_log);
        mTvLog2 = (TextView) findViewById(R.id.tv_log2);
        mEdtArrivalTime = (EditText) findViewById(R.id.edt_arrival_time);
        mEdtRequiredTime = (EditText) findViewById(R.id.edt_reuired_time);
        mEdtPriority = (EditText) findViewById(R.id.edt_priority);

        if (!mPriorityMode)
            mEdtPriority.setVisibility(View.GONE);

        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnAddJob = (Button) findViewById(R.id.btn_add_job);
        mBtnExecute = (Button) findViewById(R.id.btn_execute);

        mTvName.setText("Job1");
        mTvLog.setMovementMethod(new ScrollingMovementMethod());


        mBtnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPriorityMode) {
                    if (!mEdtArrivalTime.getText().toString().equals("") && !mEdtRequiredTime.getText().toString().equals("") && !mEdtPriority.getText().toString().equals(""))
                        addJob();
                    else
                        Snackbar.make(mBtnAddJob, "لطفا اطلاعات را کامل کنید", Snackbar.LENGTH_SHORT).show();
                } else {
                    if (!mEdtArrivalTime.getText().toString().equals("") && !mEdtRequiredTime.getText().toString().equals(""))
                        addJob();
                    else
                        Snackbar.make(mBtnAddJob, "لطفا اطلاعات را کامل کنید", Snackbar.LENGTH_SHORT).show();

                }
            }
        });

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetViews();
                mTvName.setText("Job1");
                mScheduler.clearJobs();
            }
        });


        mBtnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScheduler.execute();
                mTvLog2.setText(mScheduler.getLog());
                mTvName.setText("Job1");
                mScheduler.clearJobs();
            }
        });

    }

    protected void addJob() {
        if (mPriorityMode) {
            PriorityJob job = new PriorityJob("Job" + (mScheduler.getJobsCount() + 1), Integer.parseInt(mEdtArrivalTime.getText().toString()), Integer.parseInt(mEdtRequiredTime.getText().toString()), Integer.parseInt(mEdtPriority.getText().toString()));
            mScheduler.addJob(job);
            clearViews();
            mTvName.setText("Job" + (mScheduler.getJobsCount() + 1));
            mTvLog.setText(mTvLog.getText() + job.getName() + "\t\t" + job.getArrivalTime() + "\t\t\t\t\t" + job.getRequiredTime() + "\t\t\t\t\t" + job.getPriority() + "\n");

        } else {
            NormalJob job = new NormalJob("Job" + (mScheduler.getJobsCount() + 1), Integer.parseInt(mEdtArrivalTime.getText().toString()), Integer.parseInt(mEdtRequiredTime.getText().toString()));
            mScheduler.addJob(job);
            clearViews();
            mTvName.setText("Job" + (mScheduler.getJobsCount() + 1));
            mTvLog.setText(mTvLog.getText() + job.getName() + "\t\t" + job.getArrivalTime() + "\t\t\t\t\t" + job.getRequiredTime() + "\n");
        }
    }

    protected void resetViews() {
        mEdtArrivalTime.setText("");
        mEdtRequiredTime.setText("");
        mEdtPriority.setText("");
        mTvLog.setText(getString(R.string.Log1Title));
        mTvLog2.setText("");
    }
    protected void clearViews() {
        mEdtArrivalTime.setText("");
        mEdtRequiredTime.setText("");
        mEdtPriority.setText("");
    }
}
