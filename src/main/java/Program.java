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

                case 0:
                    break;
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
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_NAME);
                    String name = scanner.nextLine();
                    // TODO: something went wrong here
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_SURNAME);
                    String surname = scanner.nextLine();
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_STUDENT_NUMBER);
                    int studentNumber = scanner.nextInt();
                    System.out.println(Strings.STUDENT_MENU_ADD_STUDENT_GROUP_NUMBER);
                    int groupNumber = scanner.nextInt();
                    Student student = new Student(name, surname, studentNumber, groupNumber);
                    student.addStudent();
                }
                case 3 -> {
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
                }
//                case 4 -> canceled = true;
                case 0 -> {
                    canceled = true;
                    mainMenu();
                }

                // delete student;
                default -> System.out.println("Enter a right number");
            }
        }
    }
    public void studentGroupsMenu() {

    }


}
