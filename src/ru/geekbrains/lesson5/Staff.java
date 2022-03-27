package ru.geekbrains.lesson5;

public class Staff {
    private String fullName;
    private String position;
    private String email;
    private int phone;
    private int salary;
    private int age;

    public Staff() {
        this.fullName = "null";
        this.position = "null";
        this.email = "null";
        this.phone = 0;
        this.salary = 0;
        this.age = 0;

    }

    public Staff(String fullName, String position, String email, int phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        if (age >= 16) {
            this.age = age;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 16) {
            this.age = age;
        }
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
