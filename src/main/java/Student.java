public class Student {
    private String name;
    private String surname;
    private int studentNumber;
    private Semester semester;

    public Student(String name, String surname, int studentNumber, Semester semester) {
        this.name = name;
        this.surname = surname;
        this.studentNumber = studentNumber;
        this.semester = semester;
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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentNumber=" + studentNumber +
                ", semester=" + semester +
                '}';
    }
}
