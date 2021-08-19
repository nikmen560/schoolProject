import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudyProgram {
    private StudentGroup studentGroup;
    private Map<Semester, List<Subject>> subjectsForSemester;

    public void showStudyProgram(StudentGroup studentGroup) {
        ArrayList<Subject> groupSubjects = getSubjectsByStudentGroup(studentGroup);
        for (Subject subject : groupSubjects) {
            System.out.println(subject);
        }
    }

    private ArrayList<Subject> getSubjectsByStudentGroup(StudentGroup studentGroup) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subject WHERE groupNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

            ps.setInt(1, studentGroup.getGroupNumber());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(rs.getString("name"), rs.getInt("groupNumber"), new Teacher("", rs.getString("teacher"))));
            }
//            displayGroup(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

}
