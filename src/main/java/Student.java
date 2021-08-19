import java.sql.*;
import java.util.ArrayList;

public class Student implements Showable<Student>{
    private String name;
    private String surname;
    private int studentNumber;
    private int groupNumber;

    public Student(String name, String surname, int studentNumber, int groupNumber) {
        this.name = name;
        this.surname = surname;
        this.studentNumber = studentNumber;
        this.groupNumber = groupNumber;
    }

    public Student() {}

    public int getStudentNumber() {
        return studentNumber;
    }

    @Override
    public Student get(int id) {
        Student student = null;
        String studentName;
        String studentSurname;
        int studentNumber;
        int groupNumber;

        String query = "SELECT * FROM  student WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                studentName = rs.getString("name");
                studentSurname = rs.getString("surname");
                studentNumber = rs.getInt("studentNumber");
                groupNumber = rs.getInt("groupNumber");
                student = new Student(studentName, studentSurname, studentNumber, groupNumber);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;

    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", studentNumber=" + studentNumber +
                        ", groupNumber=" + groupNumber;
    }

    @Override
    public void show() {
        String query = "SELECT * FROM student";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)
        ) {
            displayStudent(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add() {
        String query = "INSERT INTO student(name,surname,studentNumber,groupNumber) VALUES(?,?,?,?)";
        boolean isExecuted = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, this.name);
            ps.setString(2, this.surname);
            ps.setInt(3, this.studentNumber);
            ps.setInt(4, this.groupNumber);

            int executed = ps.executeUpdate();
            if (executed != 0) {
                isExecuted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExecuted;
    }

    @Override
    public boolean update(int id) {
        boolean isExecuted = false;
        String query = "UPDATE student SET name =?, surname=?, studentNumber=?, groupNumber=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, studentNumber);
            ps.setInt(4, groupNumber);
            ps.setInt(5, id);
            int executed = ps.executeUpdate();
            if (executed != 0) {
                isExecuted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExecuted;

    }

    @Override
    public boolean remove(int id) {
        String query = "DELETE FROM student WHERE id=?";
        boolean isExecuted = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            int executed = ps.executeUpdate();
            if (executed != 0) {
                isExecuted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExecuted;
    }

    public void showStudents() {
        String query = "SELECT * FROM student";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)
        ) {
            displayStudent(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayStudent(ResultSet rs) throws SQLException {

        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getString("surname") + "\t" +
                    rs.getInt("studentNumber") + "\t" +
                    rs.getInt("groupNumber")
            );
        }
    }




    public void showStudentsInGroup(StudentGroup studentGroup) {
        ArrayList<Student> students = getStudentsInGroup(studentGroup);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private ArrayList<Student> getStudentsInGroup(StudentGroup studentGroup) {

        ArrayList<Student> students = new ArrayList<>();
        Student student;
        String studentName;
        String studentSurname;
        int studentNumber;
        int groupNumber;

        String query = "SELECT * FROM  student WHERE groupNumber=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentGroup.getGroupNumber());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                studentName = rs.getString("name");
                studentSurname = rs.getString("surname");
                studentNumber = rs.getInt("studentNumber");
                groupNumber = rs.getInt("groupNumber");
                student = new Student(studentName, studentSurname, studentNumber, groupNumber);
                students.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


}
