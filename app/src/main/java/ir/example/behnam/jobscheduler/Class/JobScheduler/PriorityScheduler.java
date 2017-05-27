package ir.example.behnam.jobscheduler.Class.JobScheduler;

import ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.Job;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Job.NormalJob;
import ir.example.behnam.jobscheduler.Class.JobScheduler.Job.PriorityJob;

/**
 * Created by goldm on 25/05/2017.
 */

public class PriorityScheduler extends Scheduler {


    public PriorityScheduler() {
        super(EXECUTION_MODE_NON_PREEMPTIVE);
    }

    @Override
    protected Job scheduleNextJob() {
        Job nextJob = findSmallestPriority();

        if (nextJob != null && !nextJob.isFinished()) {
            if (nextJob.getArrivalTime() <= mTime) {
                return nextJob;
            } else {
                return new NormalJob("-", 0, 1);
            }
        }
        return null;
    }


    protected Job findSmallestPriority() {
        PriorityJob smallestPriorityJob = null;
        for (Job job : mJobList) {
            if (!job.isFinished()) {
                smallestPriorityJob = (PriorityJob) job;
                break;
            }
        }

        if (smallestPriorityJob != null) {
            for (Job job : mJobList)
                if (!job.isFinished() && ((PriorityJob) job).getPriority() < smallestPriorityJob.getPriority() && job.getArrivalTime() <= mTime)
                    smallestPriorityJob = (PriorityJob) job;
        }
        return smallestPriorityJob;
    }
}
