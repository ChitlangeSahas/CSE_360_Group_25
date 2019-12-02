package main;

public class Grade {
    private Double grade = 0.00;

    Grade(Double g)
    {
        grade = g;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
