import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connection {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/amigosdedonbosco", "root", "juice777");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from usuarios");
            while (resultSet.next()){
                System.out.println(resultSet.getString("email"));
            }

        } catch (Exception e){
            e.printStackTrace();

        }

    }
}
