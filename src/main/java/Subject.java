import java.sql.*;

public class Subject {
    private String name;
    private int groupNumber;
    private Teacher teacher;

    public Subject(String name, int groupNumber, Teacher teacher) {
        this.name = name;
        this.groupNumber = groupNumber;
        this.teacher = teacher;
    }

    public Subject() {
    }
    public Subject get(int id) {
        Subject subject = null;
        String name;
        int teacherID;
        int groupid;
        Teacher teacher = null;

        String query = "SELECT * FROM  subject WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                name = rs.getString("name");
                teacherID = rs.getInt("teacherid");
                teacher = new Teacher().get(teacherID);
                groupid = rs.getInt("groupid");

                subject = new Subject(name, groupid, teacher);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    public String getName() {
        return name;
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
                    rs.getString("name") + "\t"
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

    public boolean addSubject() {
        String query = "INSERT INTO subject(name,groupNumber,teacher) VALUES(?,?,?)";
        long id = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, this.name);
            ps.setInt(2, this.groupNumber);
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
        return id != 0;
    }

    public boolean updateSubject(int id, String name, int groupNumber, String teacherSurname) {
        String query = "UPDATE subject SET name =?, groupNumber=?, teacher=? WHERE id=?";

        int affectedrows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setInt(2, this.groupNumber);
            ps.setString(3, teacherSurname);
            ps.setInt(4, id);
            affectedrows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedrows != 0;

    }

    public boolean deleteSubject(int id) {
        String query = "DELETE FROM subject WHERE id=?";

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


    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}
