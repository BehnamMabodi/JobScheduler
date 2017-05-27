package ir.example.behnam.jobscheduler.Class.JobScheduler.Job;

/**
 * Created by goldm on 25/05/2017.
 */

public class PriorityJob extends NormalJob implements ir.example.behnam.jobscheduler.Class.JobScheduler.Interface.PriorityJob {

    protected Integer mPriority;

    public PriorityJob(String name, int arrivalTime, int requiredTime, int priority) {
        super(name, arrivalTime, requiredTime);
        mPriority = priority;
    }

    @Override
    public int getPriority() {
        return mPriority;
    }
}
