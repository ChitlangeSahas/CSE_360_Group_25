package main;

public class Grade {
    private String column_number = null;
    private Double grade = 0.00;

    public String getColumn_number() {
        return column_number;
    }

    Grade(Double g)
    {
        grade = g;
    }

    public void setColumn_number(String column_number) {
        this.column_number = column_number;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
