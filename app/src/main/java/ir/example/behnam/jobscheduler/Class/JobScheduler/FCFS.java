package ir.example.behnam.jobscheduler.Class.JobScheduler;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Job.NormalJob;

/**
 * Created by goldm on 25/05/2017.
 */

public class FCFS extends Scheduler {

    @Override
    protected Job scheduleNextJob() {
        for (Job job : mJobList) {
            if (!job.isFinished()) {
                if (job.getArrivalTime() <= mTime) {
                    return job;
                } else {
                    return new NormalJob("-" , 0 , 1);
                }
            }
        }
        return null;
}
}
