public class Student extends Human
{
    protected int level;

    public Student(String firstname, String surname, String patronymic, int age, int level, Gender gender)
    {
        super(firstname, surname, patronymic, age, gender);
        this.level = level;
    }

    @Override
    public void nextYear()
    {
        super.nextYear();
        this.level++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", level=" + level +
                '}';
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
