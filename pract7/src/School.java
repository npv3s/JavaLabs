import java.util.ArrayList;
import java.util.List;

public class School extends Building {
    protected int index;
    protected String title;
    protected List<Teacher> teachers;
    protected List<Student> students;

    public School(String address, int floorCount, int index, String title, List<Teacher> teachers, List<Student> students) {
        super(address, floorCount);
        this.index = index;
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

    public School(String address, int floorCount, int index, String title) {
        this(address, floorCount, index, title, new ArrayList<>(), new ArrayList<>());
    }

    public boolean addEntity(Human human) {
        if (human instanceof Teacher)
            teachers.add((Teacher) human);
        else if (human instanceof Student)
            students.add((Student) human);
        else {
            System.out.println("not a school entity " + human);
        }

        return false;
    }

    public int nextYearAll() {
        int k = 0;

        for (int i = teachers.size() - 1; i >= 0; i--) {
            teachers.get(i).nextYear();
            if (teachers.get(i).getAge() > 65) {
                System.out.println(teachers.get(i) + " ушел на пенсию");
                teachers.remove(i);
                k++;
            }
        }

        for (int i = students.size() - 1; i >= 0; i--) {
            students.get(i).nextYear();
            if (students.get(i).getLevel() > 5) {
                System.out.println(students.get(i) + " выпустился в армию");
                students.remove(i);
                k++;
            }
        }

        return k;
    }

    @Override
    public String toString() {
        return "School{" +
                "index=" + index +
                ", title='" + title + '\'' +
                ", teachers=" + teachers +
                ", students=" + students +
                ", address='" + address + '\'' +
                ", floorCount=" + floorCount +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

