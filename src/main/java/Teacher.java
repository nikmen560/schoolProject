public class Teacher {
    private String name;
    private String lastName;
    private String surname;

    public Teacher(String name, String lastName, String surname) {
        this.name = name;
        this.lastName = lastName;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurname() {
        return surname;
    }
}
