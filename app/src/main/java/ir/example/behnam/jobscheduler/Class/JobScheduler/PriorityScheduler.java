package ir.example.behnam.jobscheduler.Class.JobScheduler;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;

/**
 * Created by goldm on 25/05/2017.
 */

public class PriorityScheduler extends Scheduler {


    public PriorityScheduler() {
        super(EXECUTION_MODE_PREEMPTIVE);
    }

    @Override
    protected Job scheduleNextJob() {
        return null;
    }
}
