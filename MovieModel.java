package moviereview;

import java.sql.*;

public class MovieModel {
    String dbURL = "jdbc:mysql://localhost/movie_db";
    String dbUsername = "root";
    String dbPassword = "";

    Connection conn; Statement stmt;

    public MovieModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("Connection Successful");
        } 
        catch (Exception e) {
            System.out.println("Connection Failed " + e.getMessage());
        }
    }

    public void insertData(String judul, double alur, double penokohan, double akting, double score) {
        try {
            System.out.println(alur);
            System.out.println(penokohan);
            System.out.println(akting);

            String query = "INSERT INTO `movie` (`title`,`plot`,`character`,`acting`,`score`) " + 
            "VALUES('" + judul + "','" + alur + "','" + penokohan + "','" + akting + "','" + score + "')";

            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            System.out.println("Input Success");
        } catch (Exception e) {
            System.out.println("Input Failed");
        }
    }

    public void updateData(String judul, double alur, double penokohan, double akting, double score) {
        try {
            String query = "UPDATE `movie` SET title='" + judul + "',plot='" + alur + "',character='" + penokohan + "',acting='" + 
            akting + "',score='" + score + "' WHERE title='" + judul;

            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            System.out.println("Input Success");
        } catch (Exception e) {
            System.out.println("Input Failed");
        }
    }


    public String[][] readData() {
        String data[][] = new String[100][5];

        try {
            int totalData = 0;
            String query = "SELECT * FROM `movie`";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while(resultSet.next()) {
                data[totalData][0] = resultSet.getString("title");
                data[totalData][1] = resultSet.getString("plot");
                data[totalData][2] = resultSet.getString("character");
                data[totalData][3] = resultSet.getString("acting");
                data[totalData][4] = resultSet.getString("score");
                totalData++;
            }
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");   
        }
        return data;
    }

    public void deleteData(String title) {
        try {
            String query = "DELETE FROM `movie` WHERE title = '" + title + "'";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
