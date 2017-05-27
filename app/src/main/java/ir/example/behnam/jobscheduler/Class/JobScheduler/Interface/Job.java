package ir.example.behnam.jobscheduler.Class.JobScheduler.Interface;

/**
 * Created by goldm on 25/05/2017.
 */

public interface Job {

    void execute(int currentTime, int assignedTime);

    String getName();

    Integer getStartTime();

    int getArrivalTime();

    int getRequiredTime();

    int getRemainingTime();

    int getElapsedTime();

    int getWaitTime(int currentSystemTime);

    boolean isFinished();

}
