enum Gender {
    MALE,
    FEMALE
}

public class Human
{
    protected String firstname;
    protected String surname;
    protected String patronymic;
    protected int age;
    protected Gender gender;

    public Human(String firstname, String surname, String patronymic, int age, Gender gender) {
        this.firstname = firstname;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
    }

    public void nextYear()
    {
        this.age++;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
