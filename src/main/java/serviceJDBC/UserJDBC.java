package serviceJDBC;

import annotation.DBTable;
import connect_service.DAOFactory;
import connect_service.PropertyInf;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC {
    private final PropertyInf property = new PropertyInf();

    private final Connection daoFactory = DAOFactory.getInstance().getConnection();



    public <T> List<T> selectQuery(Class<T> type) throws SQLException {
        List<T> list = new ArrayList<>();

        try (Connection conn = daoFactory) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rst = stmt.executeQuery(property.getSqlQuery().getProperty("GET_ALL"))) {
                    while (rst.next()) {
                        T t = type.newInstance();
                        loadResultSetIntoObject(rst, t);// Point 4
                        list.add(t);
                    }
                }
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Unable to get the records: " + e.getMessage(), e);
            }
        }
        return list;
    }

    private static void loadResultSetIntoObject(ResultSet rst, Object object)
            throws IllegalArgumentException, IllegalAccessException, SQLException {
        Class<?> zclass = object.getClass();
        for (Field field : zclass.getDeclaredFields()) {
            field.setAccessible(true);
            DBTable column = field.getAnnotation(DBTable.class);
            Object value = rst.getObject(column.columnName());
            Class<?> type = field.getType();
            if (isPrimitive(type)) {//check primitive type(Point 5)
                Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
                value = boxed.cast(value);
            }
            field.set(object, value);
        }
    }

    private static boolean isPrimitive(Class<?> type) {
        return (type == int.class || type == long.class || type == double.class || type == float.class
                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }

    private static Class<?> boxPrimitiveClass(Class<?> type) {
        if (type == int.class) {
            return Integer.class;
        } else if (type == long.class) {
            return Long.class;
        } else if (type == double.class) {
            return Double.class;
        } else if (type == float.class) {
            return Float.class;
        } else if (type == boolean.class) {
            return Boolean.class;
        } else if (type == byte.class) {
            return Byte.class;
        } else if (type == char.class) {
            return Character.class;
        } else if (type == short.class) {
            return Short.class;
        }
        else {
            String string = "class '" + type.getName() + "' is not a primitive";
            throw new IllegalArgumentException(string);
        }
    }


}
