package Task1;

import java.io.Serializable;

public class Worker implements Serializable {

    private String surname;
    private int age;
    private String position;
    private double salaryRate;

    public Worker(String surname, int age, String position, double salaryRate) {
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.salaryRate = salaryRate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalaryRate() {
        return salaryRate;
    }

    public void setSalaryRate(double salaryRate) {
        this.salaryRate = salaryRate;
    }

    @Override
    public String toString() {
        return  "Фамилия: " + surname +
                ", Возраст: " + age +
                ", Должность: " + position +
                ", Ставка ЗП: " + salaryRate + "$.";
    }
}
