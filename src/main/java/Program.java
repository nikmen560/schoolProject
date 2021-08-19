import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public void start() {
        mainMenu();
    }

    public void mainMenu() {
        boolean canceled = true;
        while (canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.HELLO_DISPLAY);
            System.out.println(Strings.MAIN_MENU);

            int mainMenuChoice;
            do {

                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(input + " is not a valid num");
                }
                mainMenuChoice = scanner.nextInt();

                switch (mainMenuChoice) {
                    case 1:
                        studentMenu();
                        break;
                    case 2:
                        studentGroupsMenu();
                        break;
                    case 3:
                        subjectMenu();
                    case 4:
                        teacherMenu();

                    case 5:
                        marksMenu();
                    case 0:
                        canceled = false;
                        break;
                    default:
                        System.out.println(Strings.TYPO_IN_MENU);
                }
            } while (mainMenuChoice < 0 && mainMenuChoice > 5);
        }
    }

    private void studentMenu() {
        boolean canceled = true;
        Scanner scanner = new Scanner(System.in);

        int studentMenuChoice;
        while (canceled) {
            do {

                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(Strings.IS_NOT_VALID_NUM);
                }
                System.out.println(Strings.STUDENT_MENU);
                studentMenuChoice = scanner.nextInt();
                switch (studentMenuChoice) {
                    case 1 -> {
                        new Student().show();
                    }
                    case 2 -> {
                        addStudent();
                    }
                    case 3 -> {
                        updateStudent();
                    }
                    case 4 -> {
                        removeStudent();
                    }
                    case 0 -> {
                        canceled = false;
                        mainMenu();
                    }

                    default -> System.out.println(Strings.TYPO_IN_MENU);
                }
            } while (studentMenuChoice < 0 && studentMenuChoice > 4);
        }
    }

    public void subjectMenu() {
        boolean canceled = true;
        while (canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.SUBJECTS_MENU);
            int subjectMenuChoice;
            do {

                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(Strings.IS_NOT_VALID_NUM);
                }
                subjectMenuChoice = scanner.nextInt();
                switch (subjectMenuChoice) {
                    case 1 -> {
                        new Subject().showSubjects();
                    }
                    case 2 -> {
                        addSubject();
                    }
                    case 3 -> {
                        updateSubject();

                    }
                    case 4 -> {
                        deleteSubject();
                    }
                    case 5 -> {
                        showSubjectsInGroup();
                    }
                    case 0 -> {
                        canceled = false;
                        mainMenu();
                    }
                    default -> System.out.println(Strings.TYPO_IN_MENU);
                }
            } while (subjectMenuChoice < 0 && subjectMenuChoice > 5);
        }

    }

    public void teacherMenu() {
        boolean canceled = true;
        while (canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.TEACHERS_MENU);

            int teacherMenuChoice;
            do {

                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(Strings.IS_NOT_VALID_NUM);
                }
                teacherMenuChoice = scanner.nextInt();
                switch (teacherMenuChoice) {
                    case 1 -> {
                        new Teacher().showTeachers();
                    }
                    case 2 -> {
                        addTeacher();
                    }
                    case 3 -> {
                        updateTeacher();

                    }
                    case 4 -> {
                        deleteTeacher();
                    }
                    case 0 -> {
                        canceled = false;
                        mainMenu();
                    }
                    default -> System.out.println(Strings.TYPO_IN_MENU);
                }
            } while (teacherMenuChoice < 0 && teacherMenuChoice > 4);
        }
    }

    public void studentGroupsMenu() {

        boolean canceled = true;
        while (canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.STUDENT_GROUP_MENU);

            int studentGroupMenuChoice;
            do {

                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(Strings.IS_NOT_VALID_NUM);
                }
                studentGroupMenuChoice = scanner.nextInt();
                switch (studentGroupMenuChoice) {
                    case 1:
                        showGroups();
                        break;
                    case 2:
                        addGroup();
                        break;

                    case 3:
                        updateGroup();
                        break;
                    case 4:
                        removeGroup();
                        break;
                    case 5:
                        showGroups();
                        showStudentsInGroup();
                        break;
                    case 0:
                        canceled = false;
                        mainMenu();
                        break;
                    default:
                        System.out.println(Strings.TYPO_IN_MENU);
                }
            } while (studentGroupMenuChoice < 0 && studentGroupMenuChoice > 4);
        }

    }

    public void marksMenu() {

        boolean canceled = true;
        while (canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.MARKS_MENU);

            int markChoiceMenu;
            do {

                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(Strings.IS_NOT_VALID_NUM);
                }
                markChoiceMenu = scanner.nextInt();

                switch (markChoiceMenu) {
                    case 1:
                        showMark();
                        break;
                    case 2:
                        addMark();
                        break;
                    case 0:
                        canceled = false;
                        mainMenu();
                        break;
                    default:
                        System.out.println(Strings.TYPO_IN_MENU);
                }
            } while (markChoiceMenu < 0 && markChoiceMenu > 4);
        }

    }

    public void showMark() {
        new Student().showStudents();
        Scanner scanner = new Scanner(System.in);
        int studentID = scanner.nextInt();
        ArrayList<Mark> marks = new Mark().getMarksByStudent(studentID);
        new Mark().showMarks(marks);

    }

    public void addMark() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(Strings.MARK_MENU_ADD_VALUE);
        int value = scanner.nextInt();
        new Student().showStudents();
        int studentID = scanner.nextInt();
        new Subject().showSubjects();
        int subjectID = scanner.nextInt();
        new StudentGroup().showGroups();
        int groupID = scanner.nextInt();

        Mark mark = new Mark(value, studentID, subjectID, groupID);
        mark.add();

    }

    public boolean addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_NAME);
        String name = scanner.nextLine();
        System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_SURNAME);
        String surname = scanner.nextLine();
        System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_STUDENT_NUMBER);
        int studentNumber = scanner.nextInt();
        System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_GROUP_NUMBER);
        int groupNumber = scanner.nextInt();
        Student student = new Student(name, surname, studentNumber, groupNumber);
        return student.add();
    }

    public boolean updateStudent() {
        Scanner scanner = new Scanner(System.in);
        new Student().showStudents();
        int studentID = scanner.nextInt();

        System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_NAME);

        String updatedName = scanner.next();
        System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_SURNAME);
        String updateSurname = scanner.next();
        System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_STUDENT_NUMBER);
        int updateStudentNumber = scanner.nextInt();
        System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_GROUP_NUMBER);
        int updateGroupNumber = scanner.nextInt();
        Student newStudent = new Student(updatedName, updateSurname, updateStudentNumber, updateGroupNumber);
        return newStudent.update(studentID);
    }

    public boolean removeStudent() {
        new Student().showStudents();
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return new Student().remove(id);
    }

    public void showGroups() {
        new StudentGroup().showGroups();
    }

    public boolean addGroup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Strings.STUDENT_GROUPS_MENU_ADD_GROUP_NUMBER);
        int groupNumber = scanner.nextInt();
        System.out.println(Strings.STUDENT_GROUPS_MENU_ADD_GROUP_SEMESTER);
        int semesterNumber = scanner.nextInt();
        Semester semester = new Semester(semesterNumber);
        StudentGroup studentGroup = new StudentGroup(groupNumber, semester);
        studentGroup.addStudentGroup();
        return true;

    }

    public boolean updateGroup() {
        Scanner scanner = new Scanner(System.in);
        new StudentGroup().showGroups();
        int studentGroupID = scanner.nextInt();
        System.out.println(Strings.STUDENT_GROUPS_MENU_UPDATE_GROUP_NUMBER);
        int updateNumber = scanner.nextInt();
        System.out.println(Strings.STUDENT_GROUPS_MENU_UPDATE_GROUP_SEMESTER);
        int updateSemester = scanner.nextInt();

        return new StudentGroup().updateGroup(studentGroupID, updateNumber, updateSemester);
    }

    public boolean removeGroup() {

        new StudentGroup().showGroups();
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return new StudentGroup().deleteGroup(id);
    }

    ////////////////////////////
    public void showStudentsInGroup() {
        new StudentGroup().showGroups();
        Scanner scanner = new Scanner(System.in);
        int groupID = scanner.nextInt();

        StudentGroup foundedStudentGroup = new StudentGroup().getStudentGroup(groupID);

        Student student = new Student();
        student.showStudentsInGroup(foundedStudentGroup);
    }
    ///////////////////////////////////

    public boolean addSubject() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Strings.SUBJECT_MENU_ADD_SUBJECT_NAME);
        String subjectName = scanner.nextLine();
        System.out.println(Strings.SUBJECT_MENU_ADD_SUBJECT_GROUPNUMBER);
        int groupNumber = scanner.nextInt();
        System.out.println(Strings.SUBJECT_MENU_ADD_SUBJECT_TEACHER);
        String teacherName = scanner.next();
        Teacher teacher = new Teacher("", teacherName);
        Subject subject = new Subject(subjectName, groupNumber, teacher);
        return subject.addSubject();
    }

    public boolean updateSubject() {
        Scanner scanner = new Scanner(System.in);
        new Subject().showSubjects();
        int subjectID = scanner.nextInt();
        System.out.println(Strings.SUBJECT_MENU_UPDATE_SUBJECT_NAME);
        String updateName = scanner.next();
        System.out.println(Strings.SUBJECT_MENU_UPDATE_SUBJECT_SEMESTER);
        int updateSemester = scanner.nextInt();
        System.out.println(Strings.SUBJECT_MENU_UPDATE_SUBJECT_TEACHER);
        String updateTeacherName = scanner.next();
        return new Subject().updateSubject(subjectID, updateName, updateSemester, updateTeacherName);
    }

    public boolean deleteSubject() {
        new Subject().showSubjects();
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return new Subject().deleteSubject(id);
    }

    public void showSubjectsInGroup() {
        new StudentGroup().showGroups();
        Scanner scanner = new Scanner(System.in);
        int groupID = scanner.nextInt();

        StudentGroup foundedStudentGroup = new StudentGroup().getStudentGroup(groupID);

        StudyProgram studyProgram = new StudyProgram();
        studyProgram.showStudyProgram(foundedStudentGroup);
    }

    public boolean addTeacher() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Strings.TEACHER_MENU_ADD_TEACHER_NAME);
        String name = scanner.nextLine();
        System.out.println(Strings.TEACHER_MENU_ADD_TEACHER_SURNAME);
        String surname = scanner.nextLine();
        Teacher teacher = new Teacher(name, surname);
        teacher.addTeacher();
        return true;
    }

    public boolean updateTeacher() {
        Scanner scanner = new Scanner(System.in);
        new Teacher().showTeachers();
        int teacherID = scanner.nextInt();
        System.out.println(Strings.TEACHER_MENU_UPDATE_TEACHER_NAME);
        String updateName = scanner.next();
        System.out.println(Strings.TEACHER_MENU_UPDATE_TEACHER_SURNAME);
        String updateSurname = scanner.next();
        return new Teacher().updateTeacher(teacherID, updateName, updateSurname);
    }

    public boolean deleteTeacher() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return new Teacher().deleteTeacher(id);
    }


}
