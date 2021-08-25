import Scheduler_task.SchedulerTaskImp;

public class MainForScheduler {
    public static void main(String[] args) {
        
        SchedulerTaskImp s = new SchedulerTaskImp();
        System.out.println(s.createTask("TestCronJob","minute", "2", "\"C:\\Users\\Николай\\IdeaProjects\\cron_job\\out\\artifacts\\cron_job_jar\\cron_job.jar\""));

    }
}
