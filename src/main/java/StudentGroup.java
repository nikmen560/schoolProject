import java.sql.*;
import java.util.List;

public class StudentGroup {
    private int groupNumber;
    private int grade;
    private List<Subject> subjects;
    private List<Student> students;
    private Semester semester;
    private int studyYears;

    public int getGroupNumber() {
        return groupNumber;
    }

    public StudentGroup(int groupNumber, Semester semester) {
        this.groupNumber = groupNumber;
        this.semester = semester;
    }

    public StudentGroup() {

    }

    public void displayGroup(ResultSet rs) throws SQLException {

        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getInt("groupNumber") + "\t" + "semester: \t" +
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
    public void showStudentInGroup(int groupNum){
        String query = "SELECT * FROM student WHERE groupNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, groupNum);
            ResultSet rs = ps.executeQuery();
            new Student().displayStudent(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getGroupNumById(int groupID) {
        String query = "SELECT * FROM studentgroup WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, groupID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int groupNum = rs.getInt("groupNumber");
                showStudentInGroup(groupNum);
            }

//             showStudentInGroup(groupNum);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StudentGroup getStudentGroup(int id) {

        StudentGroup currStudentGroup = null;
        int currGroupNum = 0;
        Semester currSemester = null;

        String query = "SELECT * FROM studentgroup WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                currGroupNum = rs.getInt("groupNumber");
                currSemester = new Semester(rs.getInt("semester"));
                currStudentGroup = new StudentGroup(currGroupNum, currSemester);
            }

//             showStudentInGroup(groupNum);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currStudentGroup;
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

    public void addStudentGroup() {
        String query = "INSERT INTO studentgroup (groupNumber,semester) VALUES(?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, this.groupNumber);
            ps.setInt(2, this.semester.getSemesterNumber());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateGroup(int groupID, int groupNumber, int semester) {
        String query = "UPDATE studentgroup SET groupNumber =?, semester=? WHERE id=?";

        int affectedrows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, groupNumber);
            ps.setInt(2, semester);
            ps.setInt(3, groupID);
            affectedrows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedrows != 0;
    }

    public boolean deleteGroup(int id) {
        String query = "DELETE FROM studentgroup WHERE id=?";

        int affectedrows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            affectedrows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedrows != 0;
    }


}
