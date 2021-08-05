import java.sql.*;

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

    public void getStudentById(int studentID)  {
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

    public long addStudent() {
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
        return id;
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


}
