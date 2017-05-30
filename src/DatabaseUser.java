import java.sql.*;

/**
 * Created by clint on 27-05-2017.
 */
public class DatabaseUser {
    public Connection connection;
    public int checkIfUser(String name, String pas)
    {
        try{
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "users";
            String driver = "com.mysql.jdbc.Driver";
            String user = "root";
            String pass = "ninjanissen";

            Class.forName(driver).newInstance();
            this.connection = DriverManager.getConnection(url + dbName, user, pass);

            Statement st = connection.createStatement();
            ResultSet Users = st.executeQuery("Select * from users");

            while (Users.next())
            {
                System.out.println(Users.getString(1));
                if(Users.getString(1).equals(name) && Users.getString(2).equals(pas))
                {
                    System.out.println(Users.getString(1));
                    return Integer.parseInt(Users.getString(3));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

       /* try {
//            Statement st = con.createStatement();
           ResultSet Users = st.executeQuery("Select * from Users");

          //  Scanner User = new Scanner(new File("Users.txt"));
            while (Users.next())
            {
                if(Users.getString(1).equals(name) && Users.getString(2).equals(pas))
                {

                            return Integer.parseInt(Users.getString(3));
                        }

            }

            } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return -1;
    }
}
