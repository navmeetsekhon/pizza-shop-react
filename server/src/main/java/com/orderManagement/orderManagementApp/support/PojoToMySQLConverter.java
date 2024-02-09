package com.orderManagement.orderManagementApp.support;

import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderItems;
import com.orderManagement.orderManagementApp.model.UserCart;
import org.hibernate.mapping.Column;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Column;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PojoToMySQLConverter {
    public static String convertToCreateStatement(Class<?> pojoClass, String tableName) {
        StringBuilder createStatement = new StringBuilder("CREATE TABLE ");
        createStatement.append(tableName).append(" (");
        String primaryColumn = "";
        Field[] fields = pojoClass.getDeclaredFields();
        Field[] fieldsSuperClass = pojoClass.getSuperclass().getDeclaredFields();
        List<Field> fields1 = new ArrayList<>();
        fields1.addAll(Arrays.asList(fields));
        fields1.addAll(Arrays.asList(fieldsSuperClass));
        for (Field field : fields1) {
            Annotation[] annotations = field.getAnnotations();
            String fieldName = field.getName();
            boolean primaryField = false;
            if (Objects.nonNull(annotations)) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Id) {
                        primaryField = true;
                    } else if (annotation instanceof Column) {
                        fieldName = (String) AnnotationUtils.getValue(annotation, "value");
                    }
                }
            }
            if (primaryField) {
                primaryColumn = fieldName;
            }
            Class<?> fieldType = field.getType();
            int mysqlDataType = getMySQLDataType(fieldType);

            createStatement.append('`').append(fieldName).append('`').append(" ").append(getMySQLDataTypeName(mysqlDataType));

            createStatement.append(", ");
        }
        createStatement.append(" PRIMARY KEY (`").append(primaryColumn).append("`)").append(");");
        return createStatement.toString();
    }

    private static int getMySQLDataType(Class<?> fieldType) {
        if (fieldType == String.class) {
            return Types.VARCHAR;
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return Types.INTEGER;
        } else if (fieldType == long.class || fieldType == Long.class) {
            return Types.BIGINT;
        } else if (fieldType == float.class || fieldType == Float.class) {
            return Types.FLOAT;
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Types.DOUBLE;
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Types.TINYINT;
        } else if (fieldType == Timestamp.class) {
            return Types.TIMESTAMP;
        }
        // Handle other data types accordingly

        return Types.NULL;
    }

    private static String getMySQLDataTypeName(int mysqlDataType) {
        switch (mysqlDataType) {
            case Types.VARCHAR:
                return "VARCHAR(60) NOT NULL";
            case Types.INTEGER:
                return "INT NOT NULL";
            case Types.BIGINT:
                return "BIGINT NOT NULL";
            case Types.FLOAT:
                return "FLOAT NOT NULL";
            case Types.DOUBLE:
                return "DOUBLE NOT NULL";
            case Types.TINYINT:
                return "TINYINT DEFAULT '0'";
            case Types.TIMESTAMP:
                return "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP";
            // Handle other data types accordingly
        }

        return "UNKNOWN";
    }

    public static void main(String[] args) {
        // Example usage
        class MyPojo {
            private String name;
            private int age;
            private boolean isActive;
        }

        String createStatement = convertToCreateStatement(Order.class, "order_items_table");
        System.out.println(createStatement);
    }
}
