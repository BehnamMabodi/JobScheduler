package ir.example.behnam.jobscheduler.Class.JobScheduler.Job;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;

/**
 * Created by goldm on 25/05/2017.
 */

public class NormalJob implements Job {

    protected int mArrivalTime;
    protected int mRequiredTime;
    private int mStartTime = -1;
    private int mElapsedTime;
    private String mName;

    public NormalJob(String name, int arrivalTime, int requiredTime) {
        mName = name;
        mArrivalTime = arrivalTime;
        mRequiredTime = requiredTime;
    }


    @Override
    public void execute(int currentTime, int assignedTime) {
        if (mStartTime == -1)
            mStartTime = currentTime;

        addElapsedTime(assignedTime);


    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public Integer getStartTime() {
        return mStartTime;
    }

    protected void addElapsedTime(int assignedTime) {
        mElapsedTime += assignedTime;
        if (mElapsedTime >= mRequiredTime)
            mElapsedTime = mRequiredTime;
    }

    @Override
    public int getArrivalTime() {
        return mArrivalTime;
    }

    @Override
    public int getRequiredTime() {
        return mRequiredTime;
    }

    @Override
    public int getRemainingTime() {
        return mRequiredTime - mElapsedTime;
    }

    @Override
    public int getElapsedTime() {
        return mElapsedTime;
    }

    @Override
    public int getWaitTime() {
        if (mStartTime != -1)
            return mStartTime - mArrivalTime;
        return mStartTime;
    }

    @Override
    public boolean isFinished() {
        return mElapsedTime >= mRequiredTime;
    }


}
