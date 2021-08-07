import java.util.Scanner;

public class Program {

    public void start() {

    }

    public void mainMenu() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Strings.HELLO_DISPLAY);
            System.out.println(Strings.MAIN_MENU);

            int mainMenuChoice = scanner.nextInt();
            switch (mainMenuChoice) {
                case 1:
                    studentMenu();
                    break;
            }
        }
    }

    private void studentMenu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(Strings.STUDENT_MENU);
            int studentMenuChoice = scanner.nextInt();
            switch (studentMenuChoice) {
                case 1:
                    new Student().showStudents();
                    break;
                case 2:
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_NAME);
                    String name = scanner.nextLine();
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_SURNAME);
                    String surname = scanner.nextLine();
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_STUDENT_NUMBER);
                    int studentNumber = scanner.nextInt();
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_GROUP_NUMBER);
                    int groupNumber = scanner.nextInt();
                    Student student = new Student(name, surname, studentNumber, groupNumber);
                    student.addStudent();
                    break;
                case 3 :
                    new Student().showStudents();
                    int studentID = scanner.nextInt();
                    System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_NAME);
                    String updateName = scanner.nextLine();
                    System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_SURNAME);
                    String updateSurname = scanner.nextLine();
                    System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_STUDENT_NUMBER);
                    int updateStudentNumber = scanner.nextInt();
                    System.out.println(Strings.STUDENT_MENU_UPDATE_STUDENT_GROUP_NUMBER);
                    int updateGroupNumber = scanner.nextInt();
                    new Student().updateStudent(studentID, updateName, updateSurname, updateStudentNumber, updateGroupNumber);
                    break;
                case 4 :
                    // delete student;
                    break;
                default:
                    System.out.println("Enter a right number");

            }
        }
    }


}
