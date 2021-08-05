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

    public void getStudents(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {

            String buffer = resultSet.getInt("id") +
                   resultSet.getString("name") +
                     resultSet.getString("surname") +
                    resultSet.getInt("studentNumber") +
                     resultSet.getInt("groupNumber");
            System.out.println(buffer);

        }
    }

    public void getStudentById(ResultSet rs, int index) throws SQLException {
        while (rs.next()) {

            String buffer = rs.getInt("studentNumber") + " " + rs.getString("name");
            System.out.println(buffer);
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

}
