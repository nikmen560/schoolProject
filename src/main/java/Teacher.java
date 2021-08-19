import java.sql.*;

public class Teacher implements Showable<Teacher>{
    private String name;
    private String surname;

    public Teacher(String name,  String surname) {
        this.name = name;
        this.surname = surname;
    }
    public Teacher() {

    }

    public String getName() {
        return name;
    }



    public String getSurname() {
        return surname;
    }

    public void showTeachers() {
        String query = "SELECT * FROM teacher";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)
        ) {
            displayTeacher(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTeacher(ResultSet rs) throws SQLException {

        while (rs.next()) {

            System.out.println(rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getString("surname") + "\t"
            );
        }
    }

    public void getTeacherById(int studentID)  {
        String query = "SELECT * FROM teacher WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            displayTeacher(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addTeacher() {
        String query = "INSERT INTO teacher(name,surname) VALUES(?,?)";
        long id = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, this.name);
            ps.setString(2, this.surname);

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

    public boolean updateTeacher(int id, String name, String surname) {
        String query = "UPDATE teacher SET name =?, surname=? WHERE id=?";

        int affectedrows = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, id);
            affectedrows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedrows != 0;

    }

    public boolean deleteTeacher(int id) {
        String query = "DELETE FROM teacher WHERE id=?";

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
    public void show() {

    }

    @Override
    public Teacher get(int id) {
        String name;
        String surname;
        Teacher teacher = null;

        String query = "SELECT * FROM  teacher WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                name = rs.getString("name");
                surname = rs.getString("surname");
                teacher = new Teacher(name, surname);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
