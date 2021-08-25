
import get_inf_for_cron.WorkWithFile;
import java.io.FileNotFoundException;

public class MainForStatistic {
    public static void main(String[] args) {

        WorkWithFile w = new WorkWithFile();
        try {
            w.saveStatistic("G:/input.txt", 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}