import java.sql.*;

public class Subject {
    private String name;
    private Semester semester;
    private Teacher teacher;

    public Subject(String name, Semester semester, Teacher teacher) {
        this.name = name;
        this.semester = semester;
        this.teacher = teacher;
    }

    public void showSubjects() {
        String query = "SELECT * FROM subject";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)
        ) {
            displaySubject(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaySubject(ResultSet rs) throws SQLException {

        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getInt("semester") + "\t" +
                    rs.getInt("teacher")
            );
        }
    }

    public void getSubjectById(int studentID)  {
        String query = "SELECT * FROM subject WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            displaySubject(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long addSubject() {
        String query = "INSERT INTO subject(name,semester,teacher) VALUES(?,?,?)";
        long id = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, this.name);
            ps.setInt(2, this.semester.getSemesterNumber());
            ps.setString(3, this.teacher.getSurname());

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

    public boolean updateSubject(int id, String name, int semesterNumber, String teacherSurname) {
        String query = "UPDATE student SET name =?, semester=?, teacher=? WHERE id=?";

        int affectedrows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setInt(2, semesterNumber);
            ps.setString(3, teacherSurname);
            ps.setInt(4, id);
            affectedrows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedrows != 0;

    }

}
