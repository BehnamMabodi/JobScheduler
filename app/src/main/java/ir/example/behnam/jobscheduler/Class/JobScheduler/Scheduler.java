package ir.example.behnam.jobscheduler.Class.JobScheduler;


import java.util.ArrayList;
import java.util.List;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;

/**
 * Created by goldm on 25/05/2017.
 */

public abstract class Scheduler {


    public static final int EXECUTION_MODE_PREEMPTIVE = 10;
    public static final int EXECUTION_MODE_NON_PREEMPTIVE = 20;

    protected List<Job> mJobList;
    int mTime;
    String mLog;
    int mExecutionTimeSteps = 1;
    int mExecutionMode = EXECUTION_MODE_NON_PREEMPTIVE;
    Job mCurrentJob;

    public Scheduler(int executionMode) {
        mJobList = new ArrayList<>();
        mExecutionMode = executionMode;
    }

    public void execute() {
        sortJobsByArrivalTime();
        mLog = "زمان انتظار\t\tزمان سپری شده\t\tزمان باقیمانده\t\tزمان شروع\t\tکار فعلی\t\tزمان سیستم\n";
        while (true) {
            if (mExecutionMode == EXECUTION_MODE_NON_PREEMPTIVE || (mCurrentJob != null && mCurrentJob.isFinished()))
                mCurrentJob = scheduleNextJob();
            else if (mCurrentJob == null)
                mCurrentJob = scheduleNextJob();

            if (mCurrentJob == null)
                break;


            mCurrentJob.execute(mTime, mExecutionTimeSteps);


            // Log Generation:
            if (!mCurrentJob.getName().equals("-"))
                mLog += "\t\t" + mTime + "\t\t\t\t" + mCurrentJob.getName() + "\t\t\t" + mCurrentJob.getStartTime() + "\t\t\t\t\t" + mCurrentJob.getRemainingTime() + "\t\t\t\t\t" + mCurrentJob.getElapsedTime() + "\t\t\t\t\t" + mCurrentJob.getWaitTime() + "\n";
            else
                mLog += "\t\t" + mTime + "\t\t\t\t\t" + mCurrentJob.getName() + "\t\t\t" + mCurrentJob.getName() + "\t\t\t\t\t" + mCurrentJob.getName() + "\t\t\t\t\t" + mCurrentJob.getName() + "\t\t\t\t\t" + mCurrentJob.getName() + "\n";
            mTime += mExecutionTimeSteps;
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

    protected Job findSmallestArrivalTime() {
        Job smallestArrivalTimeJob = null;
        for (Job job : mJobList) {
            if (!job.isFinished()) {
                smallestArrivalTimeJob = job;
                break;
            }
        }

        if (smallestArrivalTimeJob != null) {
            for (Job job : mJobList)
                if (!job.isFinished() && job.getArrivalTime() < smallestArrivalTimeJob.getArrivalTime())
                    smallestArrivalTimeJob = job;
        }
        return smallestArrivalTimeJob;
    }

    protected Job findSmallestRequiredTime() {
        Job smallestRequiredTimeJob = null;
        for (Job job : mJobList) {
            if (!job.isFinished()) {
                smallestRequiredTimeJob = job;
                break;
            }
        }

        if (smallestRequiredTimeJob != null) {
            for (Job job : mJobList)
                if (!job.isFinished() && job.getRequiredTime() < smallestRequiredTimeJob.getRequiredTime() && job.getArrivalTime() <= mTime)
                    smallestRequiredTimeJob = job;
        }
        return smallestRequiredTimeJob;
    }

    protected void sortJobsByArrivalTime() {
        for (int i = 0; i < mJobList.size(); i++) {
            if (i < mJobList.size() - 1) {
                if (mJobList.get(i).getArrivalTime() > mJobList.get(i + 1).getArrivalTime()) {
                    Job j = mJobList.remove(i);
                    mJobList.add(i, mJobList.get(i));
                    mJobList.remove(i + 1);
                    mJobList.add(j);
                    i = -1;
                }
            }
        }
    }

}
