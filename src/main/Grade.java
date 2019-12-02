package main;

public class Grade implements Comparable<Grade> {
    private Double grade = 0.00;

    Grade(Double g) {
        grade = g;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Grade g) {
        return (this.grade.compareTo(g.getGrade()));
    }

    @Override
    public boolean equals(Object o) {
        Grade g;
        g = (Grade) o;
        if (this.grade.equals(g.getGrade())) {
            return true;
        }
        return false;
    }
}
