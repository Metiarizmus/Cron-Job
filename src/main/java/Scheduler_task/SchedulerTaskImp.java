package Scheduler_task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SchedulerTaskImp implements SchedulerTaskI {

    //I use this method because it maybe can fix a problem with scheduler task of windows
    private String getTimeAdd() {
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MINUTE, 2);
        timeStamp = sdf.format(c.getTime());
        return timeStamp;
    }

    @Override
    public boolean createTask(String nameOfTask, String timetable, String frequency, String pathToJar) {
        //Schtasks /Create /SC HOURLY /MO 5 /TN cron job /TR C:\Users\Николай\Desktop\cron_job.jar
        List<String> commands = new ArrayList<>();

        commands.add("schtasks.exe");
        commands.add("/CREATE");
        commands.add("/SC");
        commands.add(timetable);
        commands.add("/MO");
        commands.add(frequency);
        commands.add("/ST");
        commands.add(getTimeAdd());
        commands.add("/TN");
        commands.add(nameOfTask);
        commands.add("/TR");
        commands.add(pathToJar);



        ProcessBuilder builder = new ProcessBuilder(commands);
        Process p = null;
        try {
            p = builder.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert p != null;
        if (p.exitValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTask() {
        return false;
    }

    @Override
    public boolean changeTask() {
        return false;
    }
}
