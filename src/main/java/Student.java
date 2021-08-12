import java.sql.*;
import java.util.ArrayList;

public class Student {
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

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
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

    public void getStudentById(int studentID) {
        String query = "SELECT * FROM student WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            displayStudent(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addStudent() {
        String query = "INSERT INTO student(name,surname,studentNumber,groupNumber) VALUES(?,?,?,?)";
        long id = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, this.name);
            ps.setString(2, this.surname);
            ps.setInt(3, this.studentNumber);
            ps.setInt(4, this.groupNumber);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id != 0;

    }

    public boolean updateStudent(int id, String name, String surname, int studentNumber, int groupNumber) {
        String query = "UPDATE student SET name =?, surname=?, studentNumber=?, groupNumber=? WHERE id=?";

        int affectedrows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, studentNumber);
            ps.setInt(4, groupNumber);
            ps.setInt(5, id);
            affectedrows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedrows != 0;

    }

    public boolean deleteStudent(int id) {
        String query = "DELETE FROM student WHERE id=?";

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
             PreparedStatement ps = conn.prepareStatement(query);) {

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

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentNumber=" + studentNumber +
                ", groupNumber=" + groupNumber;
    }
}
