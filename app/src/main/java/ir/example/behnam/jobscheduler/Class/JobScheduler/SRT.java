package ir.example.behnam.jobscheduler.Class.JobScheduler;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;

/**
 * Created by goldm on 25/05/2017.
 */

public class SRT extends Scheduler {


    public SRT() {
        super(EXECUTION_MODE_NON_PREEMPTIVE);
    }

    @Override
    protected Job scheduleNextJob() {
        return null;
    }
}
