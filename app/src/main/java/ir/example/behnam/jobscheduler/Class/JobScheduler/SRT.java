package ir.example.behnam.jobscheduler.Class.JobScheduler;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Job.NormalJob;

/**
 * Created by goldm on 25/05/2017.
 */

public class SRT extends Scheduler {


    public SRT() {
        super(EXECUTION_MODE_NON_PREEMPTIVE);
    }

    @Override
    protected Job scheduleNextJob() {
        Job nextJob = findSmallestRemainingTime();

        if (nextJob != null && !nextJob.isFinished()) {
            if (nextJob.getArrivalTime() <= mTime) {
                return nextJob;
            } else {
                return new NormalJob("-", 0, 1);
            }
        }
        return null;
    }
}
