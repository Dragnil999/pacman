package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String user = "postgres";
    private static final String password = "admin";
    private static Connection connection;
    private static PreparedStatement statement;

    public static boolean connection(String ip, String port){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/postgres", user, password);
            System.out.println("Connection successful ");
            createTable();
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public static void createTable(){
        try {
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS public.leaderboard (name character varying, score integer)");
            statement.execute();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void addToDB(String name, int score) {
        try {
            statement = connection.prepareStatement("INSERT INTO public.leaderboard (name, score) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setInt(2, score);
            statement.execute();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static void removeDB(){
        try {
            statement = connection.prepareStatement("DELETE FROM public.leaderboard");
            statement.execute();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static List<String> readDB() {
        List<String> data = new ArrayList<>();
        try {
            Statement stat = connection.createStatement();
            ResultSet resultSet = stat.executeQuery("SELECT * FROM public.leaderboard ORDER BY score DESC LIMIT 10");
            while (resultSet.next()) {
                data.add(resultSet.getString("name") + "     " + resultSet.getInt("score"));
            }
            resultSet.close();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return data;
    }

    public static void closeDB(){
        try{
            statement.close();
            connection.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
