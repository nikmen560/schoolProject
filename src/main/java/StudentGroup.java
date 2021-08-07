import java.sql.*;
import java.util.List;

public class StudentGroup {
    private int groupNumber;
    private int grade;
    private List<Subject> subjects;
    private List<Student> students;
    private Semester semester;
    private int studyYears;


    public void displayGroup(ResultSet rs) throws SQLException{

        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getInt("groupNumber") + "\t" +
                    rs.getInt("semester")
            );
        }
    }
    public void showGroups() {
        String query = "SELECT * FROM studentgroup";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)
        ) {
            displayGroup(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getGroupById(int groupID)  {
        String query = "SELECT * FROM studentgroup WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, groupID);
            ResultSet rs = ps.executeQuery();
            displayGroup(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showStudentsFromGroup(int groupNumber) {
        String query = "SELECT * FROM student WHERE groupNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, groupNumber);
            ResultSet rs = ps.executeQuery();
            Student student = new Student("a", "b", 213, 213);
            student.displayStudent(rs);
//            displayGroup(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
