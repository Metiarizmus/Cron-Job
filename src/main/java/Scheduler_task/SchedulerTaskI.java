package Scheduler_task;

public interface SchedulerTaskI {
    boolean createTask(String nameOfTask, String timetable, String frequency, String pathToJar);
    boolean deleteTask();
    boolean changeTask();
}
