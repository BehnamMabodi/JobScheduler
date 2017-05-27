package ir.example.behnam.jobscheduler.Class.JobScheduler;


import java.util.ArrayList;
import java.util.List;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;

/**
 * Created by goldm on 25/05/2017.
 */

public abstract class Scheduler {


    protected List<Job> mJobList;
    int mTime;
    String mLog;

    public Scheduler() {
        mJobList = new ArrayList<>();
    }

    public void Execute() {
        mLog = "زمان انتظار\t\tزمان سپری شده\t\tزمان باقیمانده\t\tزمان شروع\t\tکار فعلی\t\tزمان سیستم\n";
        while (true) {
            Job job = scheduleNextJob();
            if (job == null)
                break;

            job.execute(mTime, 1);
            if (!job.getName().equals("-"))
                mLog += "\t\t" + mTime + "\t\t\t\t" + job.getName() + "\t\t\t" + job.getStartTime() + "\t\t\t\t\t" + job.getRemainingTime() + "\t\t\t\t\t" + job.getElapsedTime() + "\t\t\t\t\t" + job.getWaitTime() + "\n";
            else
                mLog += "\t\t" + mTime + "\t\t\t\t\t" + job.getName() + "\t\t\t" + job.getName() + "\t\t\t\t\t" + job.getName() + "\t\t\t\t\t" + job.getName() + "\t\t\t\t\t" + job.getName() + "\n";
            mTime++;
        }
    }

    public String getLog() {
        return mLog;
    }

    protected abstract Job scheduleNextJob();

    public void addJob(Job job) {
        mJobList.add(job);
    }

    public int getJobsCount() {
        return mJobList.size();
    }

    public void clearJobs() {
        mJobList.clear();
        mTime = 0;
    }

}
