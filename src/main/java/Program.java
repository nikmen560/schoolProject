import java.util.Scanner;

public class Program {

    public void start() {
        mainMenu();
    }

    public void mainMenu() {
        boolean canceled = false;
        while (!canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.HELLO_DISPLAY);
            System.out.println(Strings.MAIN_MENU);

            int mainMenuChoice = scanner.nextInt();
            switch (mainMenuChoice) {
                case 1:
                    canceled = true;
                    studentMenu();
                    break;
                case 2:
                    canceled = true;
                    studentGroupsMenu();
                    break;
                case 3:
                    canceled = true;
                    subjectMenu();
                case 4:
                    canceled = true;
                    teacherMenu();

                case 0:
                    canceled = true;
                    break;
                default:
                    System.out.println(Strings.TYPO_IN_MENU);
            }
        }
    }

    private void studentMenu() {
        boolean canceled = false;
        Scanner scanner = new Scanner(System.in);

        while (!canceled) {

            System.out.println(Strings.STUDENT_MENU);
            int studentMenuChoice = scanner.nextInt();
            switch (studentMenuChoice) {
                case 1 -> {
                    new Student().showStudents();
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
                    mainMenu();
                }

                default -> System.out.println(Strings.TYPO_IN_MENU);
            }
        }
    }

    public void subjectMenu() {
        boolean canceled = false;
        while (!canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.SUBJECTS_MENU);

            int subjectMenuChoice = scanner.nextInt();
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
                case 0 -> mainMenu();
                default -> System.out.println(Strings.TYPO_IN_MENU);
            }
        }
    }

    public void teacherMenu() {
        boolean canceled = false;
        while (!canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.TEACHERS_MENU);

            int teacherMenuChoice = scanner.nextInt();
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
                    canceled = true;
                    mainMenu();
                }
                default -> System.out.println(Strings.TYPO_IN_MENU);
            }
        }
    }
    public void studentGroupsMenu() {

        boolean canceled = false;
        while (!canceled) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.STUDENT_GROUP_MENU);

            int studentGroupMenuChoice= scanner.nextInt();
            switch (studentGroupMenuChoice) {
                case 1:
                    new StudentGroup().showGroups();
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
                case 0:
                    mainMenu();
                    break;
                default:
                    System.out.println(Strings.TYPO_IN_MENU);
            }
        }

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
        return student.addStudent();
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
        return new Student().updateStudent(studentID, updatedName, updateSurname, updateStudentNumber, updateGroupNumber);
    }

    public boolean removeStudent() {
        new Student().showStudents();
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return new Student().deleteStudent(id);

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

    public boolean addSubject() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Strings.SUBJECT_MENU_ADD_SUBJECT_NAME);
        String subjectName = scanner.nextLine();
        System.out.println(Strings.SUBJECT_MENU_ADD_SUBJECT_SEMESTER);
        int semesterNumber = scanner.nextInt();
        System.out.println(Strings.SUBJECT_MENU_ADD_SUBJECT_TEACHER);
        String teacherName = scanner.next();
        Semester semester = new Semester(semesterNumber);
        Teacher teacher = new Teacher("",  teacherName);
        Subject subject = new Subject(subjectName, semester, teacher);
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
