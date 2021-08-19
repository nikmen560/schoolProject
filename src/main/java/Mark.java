import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mark implements Showable<Mark>{
    private int id;
    private int value;
    private Student student;
    private Subject subject;
    private int studentgroupid;
    private int studentId;
    private int subjectId;


    public Mark() {}

    public Mark(int value, Student student, Subject subject, int studentgroupid) {
        this.value = value;
        this.student = student;
        this.subject = subject;
        this.studentgroupid = studentgroupid;
    }

    public Mark(int value, int studentID, int subjectID, int studentgroupid) {
        this.value = value;
        this.studentId = studentID;
        this.subjectId = subjectID;
        this.studentgroupid = studentgroupid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void show() {

    }

    public void showMarks(ArrayList<Mark> marks) {
        for(Mark mark :marks) {
            System.out.println(mark);
        }
    }
    public ArrayList<Mark> getMarksByStudent(int studentID) {
        ArrayList<Mark> marks = new ArrayList<>();
        String query = "SELECT * FROM  mark WHERE studentid=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                value = rs.getInt("value");
                int subjectID = rs.getInt("subjectid");
                int studentGroupID = rs.getInt("studentgroupid");
                student = new Student().get(studentID);
                subject = new Subject().get(subjectID);
                marks.add(new Mark(value, student, subject, studentGroupID));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }

    @Override
    public Mark get(int id) {

        Mark mark = null;
        int value;
        Student student = null;
        Subject subject = null;
        int studentgroupid;

        String query = "SELECT * FROM  mark WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                value = rs.getInt("value");
                int studentID = rs.getInt("studentid");
                int subjectID = rs.getInt("subjectid");
                int studentGroupID = rs.getInt("studentgroupid");
                student = new Student().get(studentID);
                subject = new Subject().get(subjectID);
                mark = new Mark(value, student, subject, studentGroupID);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mark;
    }


    @Override
    public boolean add() {
        String query = "INSERT INTO mark(value,studentid,subjectid,studentgroupid) VALUES(?,?,?,?)";
        boolean isExecuted = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, this.value);
            ps.setInt(2, this.studentId);
            ps.setInt(3, this.subjectId);
            ps.setInt(4, this.studentgroupid);

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
        String query = "UPDATE mark SET value=?, studentid=?, subjectid=?, studentgroupid=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, this.value);
            ps.setInt(2, this.student.getStudentNumber());
            ps.setString(3, this.subject.getName());
            ps.setInt(4, this.studentgroupid);
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
        String query = "DELETE FROM mark WHERE id=?";
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

    @Override
    public String toString() {
        return "Mark{" +
                ", value=" + value +
                ", student=" + student +
                ", subject=" + subject.getName() +
                '}';
    }
}
