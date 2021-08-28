package get_inf_for_cron;

import Entity.User;

import java.io.*;
import java.text.ParseException;

public class WorkWithFile {
    private final InfForStatistic inf = new InfForStatistic();

    public void saveStatistic(String filePath, int x) throws FileNotFoundException {

        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("names of users that logged in it system last 5 hours: ");

            for (String q : inf.loggedInSystem()) {
                writer.write(q + ", ");
            }
            writer.append('\n');
            writer.append('\n');
            writer.write("count of users more than " + x + " years: " + inf.countThanMoreXYears(x) + "\n");
            writer.append('\n');

            writer.write("users that registered today: " + "\n");
            FileOutputStream fos = new FileOutputStream(filePath, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (User q : inf.registrToday()) {
                oos.writeObject(q);
            }

            System.out.println("all good");
            oos.close();
            fos.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}
