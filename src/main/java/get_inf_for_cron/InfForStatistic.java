package get_inf_for_cron;

import Entity.User;
import serviceJDBC.UserJDBC;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//names of users that logged in it system last 5 hours, count of users more than I years, users that registered today
public class InfForStatistic {

    private List<User> users = null;

    {
        try {
            users = new UserJDBC().selectQuery(Entity.User.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Date timeNow() throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formater.parse(formater.format(new Date()));
    }

    public List<String> loggedInSystem() throws ParseException {
        List<String> nameUsers = new ArrayList<>();

        for (User q : users) {
            long different = timeNow().getTime() - q.getLastLogin().getTime();
            if (different < 3600 * 1000 * 5) {
                nameUsers.add(q.getName());
            }
        }

        return nameUsers;
    }

    public int countThanMoreXYears(int years) {
        int c = 0;

        int x = -years;
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.YEAR, x);
        Date xYearsAgo = cal.getTime();

        for (User q : users) {
            if (q.getRegistrationDate().before(xYearsAgo)) {
                c++;
            }
        }

        return c;
    }

    public List<User> registrToday() throws ParseException {
        List<User> usersToday = new ArrayList<>();

        for (User q : users) {
            long different = timeNow().getTime() - q.getRegistrationDate().getTime();
            if (different < 3600 * 1000 * 24) {
                usersToday.add(q);
            }
        }

        return usersToday;
    }

}
