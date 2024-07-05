package org.example.tallerpoo.db;

import java.sql.*;

public class DB {

    private Connection con;
    private PreparedStatement pStatement = null;
    private Statement statement = null;
    private static DB DBInstance;

    private DB() {
        try {
            setCon(con = DriverManager.getConnection("jdbc:mysql://localhost/TallerPoo", "admin","admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DB getDBInstance() {
        if(DBInstance == null){
            DBInstance = new DB();
        }
        return DBInstance;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void insertRegistry(String name, String desc, String employeeName, String employeeLastName, String category, String date, String price){

        try {
            pStatement = con.prepareStatement("insert into register(name, description, id_employee, id_category, work_date, price) values (?,?,?,?,?,?)");

            pStatement.setString(1, name);
            pStatement.setString(2,desc);
            pStatement.setString(3, String.valueOf(getEmployeeByName(employeeName, employeeLastName)));
            pStatement.setString(4, String.valueOf(getCategoryByName(category)));
            pStatement.setString(5,date);
            pStatement.setString(6,price);


            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertEmployee(String name, String lastName){
        try {
            pStatement = con.prepareStatement("insert into employee(name, lastname)" +" values (?,?)");
            pStatement.setString(1,name);
            pStatement.setString(2,lastName);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCategory(String name){
        try {
            pStatement = con.prepareStatement("insert into category(name)" + " values (?)");
            pStatement.setString(1,name);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getEmployeeByName(String name, String lastName){

        int id = -1;

        try {
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select id from employee where name = " + name + " and lastname = " + lastName);

            id = (int) resultSet.getObject(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;

    }

    public int getCategoryByName(String name){
        int id = -1;

        try {
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select id from category where name = " + name);

            id = (int) resultSet.getObject(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


}
