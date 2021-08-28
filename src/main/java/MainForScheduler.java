import Scheduler_task.SchedulerTaskImp;
import connect_service.PropertyInf;

public class MainForScheduler {
    public static void main(String[] args) {
        PropertyInf propertyInf = new PropertyInf();
        String pathToJar = propertyInf.getPath().getProperty("PATH_TO_JAR");

        SchedulerTaskImp s = new SchedulerTaskImp();
        System.out.println(s.createTask("TestCronJob","minute", "2", pathToJar));

    }
}



