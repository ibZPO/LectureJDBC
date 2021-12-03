package edu.ib;



import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {

        ArrayList<MovieCharacter> movieCharacters= new ArrayList<>();
        movieCharacters.add(new MovieCharacter(300,"Nikodem","Dyzma",45));
        movieCharacters.add(new MovieCharacter(301,"Stanislaw","Wokulski",40));


        StringBuilder url=new StringBuilder();
        url.append("jdbc:mysql://");
        url.append("localhost:3306/");
        url.append("dbMovie?");
        url.append("useUnicode=true&characterEncoding=utf-8");
        url.append("&user=root");
        url.append("&password=eduibtest");;

        String urlConnection=url.toString();


        try{

            Connection connection=DriverManager.getConnection(urlConnection);

            PreparedStatement showTableStm=connection.prepareStatement("SHOW TABLES");
            ResultSet rsTables=showTableStm.executeQuery();

            while(rsTables.next()){
                String answer=rsTables.getString(1);
                System.out.println(answer);
            }
            showTableStm.close();
            rsTables.close();


           PreparedStatement selectAllStm=connection.prepareStatement(
                   "SELECT * FROM table_mcharacters;");
           ResultSet rsTableContent=selectAllStm.executeQuery();
//            while(rsTableContent.next()){
//                String answer=rsTableContent.getString("last");
//                System.out.println(answer);
//            }
            printResultSet(rsTableContent);

            String insertString="INSERT INTO table_mcharacters" +
                    "(id,first,last,age)"+
                    "VALUES"+
                    "(200,'John','Doe',23);";
            PreparedStatement insertStm=connection.prepareStatement(insertString);
            int rowAffected=insertStm.executeUpdate();

            System.out.println("Affected rows= " + rowAffected);

            PreparedStatement insertCheckStm=connection.prepareStatement(
                    "SELECT * FROM table_mcharacters;");
            ResultSet rsTableCheck=insertCheckStm.executeQuery();
            printResultSet(rsTableCheck);

            String deleteString="DELETE FROM table_mcharacters WHERE last='Doe';";
            PreparedStatement deleteStm=connection.prepareStatement(deleteString);
            rowAffected=deleteStm.executeUpdate();
            System.out.println("Affected rows= " + rowAffected);

            System.out.println("--------------------------------------");
            String query="INSERT INTO table_mcharacters (id,first,last,age) VALUES (?,?,?,?);";
            PreparedStatement multipleInsertStm=connection.prepareStatement(query);
            for(MovieCharacter mc : movieCharacters){
                multipleInsertStm.setInt(1,mc.getId());
                multipleInsertStm.setString(2,mc.getFirst());
                multipleInsertStm.setString(3, mc.getLast());
                multipleInsertStm.setInt(4,mc.getAge());
                multipleInsertStm.addBatch();
            }
            multipleInsertStm.executeBatch();


            PreparedStatement insertMultipleCheckStm=connection.prepareStatement(
                    "SELECT * FROM table_mcharacters;");
            ResultSet rsMultipleTableCheck=insertMultipleCheckStm.executeQuery();
            printResultSet(rsMultipleTableCheck);

//            CachedRowSet  cachedRowSet;
//            cachedRowSet= new CachedRowSetWrapper();
//            cachedRowSet.populate(rsMultipleTableCheck);





        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static  void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd=resultSet.getMetaData();
        int columnCount=rsmd.getColumnCount();
        while(resultSet.next()){

            for(int i=1; i<=columnCount; i++){
                String colVal=resultSet.getString(i);
                System.out.print(colVal + "\t");
            }
            System.out.println();
        }

    }

}
