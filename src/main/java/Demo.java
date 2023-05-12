import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    static final String DB_URL = "jdbc:mysql://localhost:3306/simpleJdbc";
    static final String USER = "root";
    static final String PASS = "root";
    static final String SELECT_QUERY = "SELECT id, first_name, last_name, salary FROM EMPLOYEE";
    static final String INSERT_QUERY = "insert into EMPLOYEE VALUES(3,'TAVNEET','',2400);";

    public static void main(String[] args) {
        List<Employee> employees = fetchRecords();
        System.out.println(employees);
        insertRecords(3,"tavneet","idk",2400);
        System.out.println(fetchRecords());
//        updateRecords();
//        System.out.println(fetchRecords());
//        deleteRecords(3);
//        System.out.println(fetchRecords());;
    }

    private static List<Employee> fetchRecords() {


        // connection you have to open
        // you should write and know sql queries
        // result set row ----> employee---->mapping responsibilty
        // close
        List<Employee> list = new ArrayList<>();
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleJdbc", USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_QUERY);
            ) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setSalary(rs.getInt("salary"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                list.add(emp);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static void insertRecords(int id, String firstName,String lastName, int salary ) {
        String s = String.format("insert into EMPLOYEE VALUES(%d,'%s','%s',%d)",id,firstName,lastName,salary);

        String INSERT_QUERY =  String.format("insert into EMPLOYEE VALUES(%d,'%s','%s',%d)",id,firstName, lastName, salary);

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ) {
            stmt.executeUpdate(INSERT_QUERY);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateRecords() {

        String UPDATE_QUERY =  "UPDATE EMPLOYEE SET SALARY=2500 WHERE ID=1";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(UPDATE_QUERY);
            System.out.println("Records updated with id 1 into the table...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteRecords(int id) {

        String DELETE_QUERY =  "DELETE FROM EMPLOYEE WHERE ID="+id;

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(DELETE_QUERY);
            System.out.println("Records deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
