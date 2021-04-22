public class Teacher extends Human
{
    protected String subject;
    protected int exp;

    public Teacher(String firstname, String surname, String patronymic, int age, String subject, int exp, Gender gender)
    {
        super(firstname, surname, patronymic, age, gender); //вызов родительского конструктора должен быть 1 действием в конструкторе наследника
        this.subject = subject;
        this.exp = exp;
    }

    @Override
    public void nextYear()
    {
        super.nextYear();
        this.exp++;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", subject='" + subject + '\'' +
                ", exp=" + exp +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
