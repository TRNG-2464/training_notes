package com.revature.streams.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ExamplePipeline {
    public static void main(String[] args) {
        Classroom classroom = new Classroom();

        System.out.println("\t\t::: For Each Loop :::");
        for(Student s : classroom.getStudents())
            System.out.println(s);

        System.out.println("\t\t::: Stream Iteration :::");
        classroom.getStudents().stream()
                .forEach(System.out::println);

        System.out.println("\t\t::: Filter :::");
        classroom.getStudents().stream()
                .filter(s -> s.getFirstName().contains("a"))
                .forEach(System.out::println);

        System.out.println("\t\t::: Highest Average Test Score :::");
        Integer maxScore =
                classroom.getStudents().stream()
                        .map(Student::getAverageScore)
                        .max((a,b) -> a-b).orElseThrow();
        System.out.println(maxScore);

        System.out.println("\t\t::: Student(s) with Highest Average Test Score :::");
        classroom.getStudents().stream()
                .filter(s -> Objects.equals(s.getAverageScore(), maxScore))
                .forEach(System.out::println);
    }
}

/*
 * The 'Classroom' maintains a list of Students
 */
class Classroom {
    private List<Student> students;

    // Here, our default constructor performs the setup for the example
    public Classroom () {
        students = new ArrayList<Student> ();
        students.add(new Student("Abigail", "Zest"));
        students.add(new Student("Bryan", "Yeoman"));
        students.add(new Student("Carissa", "Xat"));
        students.add(new Student("Dilip", "Weir"));
        students.add(new Student("Edna", "Vickers"));
        students.add(new Student("Farosh", "Untz"));
        students.add(new Student("Gabriel", "Thompson"));
        students.add(new Student("Helga", "Steel"));

        // we call this utility method to give our students a random grade
        StudentGradeGenerator.setRandomStudentGrades(5,students);
    }

    public List<Student> getStudents() { return students; }
}

/*
 * 'Student' is a single POJO entity, maintained as a list in our
 * Classroom. Students have a list of grades (testScores) that will
 * be used in our Stream Pipeline to display some aggregate data
 */
class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    private List<Integer> testScores;

    public Student(String firstName, String lastName) {
        super();
        this.studentId = StudentIdGenerator.generateStudentId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.testScores = new ArrayList<Integer>();
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId() throws IllegalAccessException { throw new IllegalAccessException(); }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<Integer> getTestScores() { return testScores; }
    public boolean addTestScore(Integer value) { return testScores.add(value); }

    public Integer getAverageScore() {
        int sum = 0;
        for (Integer s : testScores) sum += s;

        return sum / testScores.size();
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", testScores=" + testScores + "," + "average test score="+getAverageScore()+"]";
    }
}

/*
 * This class includes a utility method that we are using to randomly generate student grades
 *
 * In a production environment - imagine that grades are provided via a student's actual
 * performance on evaluations
 */
class StudentGradeGenerator {
    public static void setRandomStudentGrades(long amount, List<Student> students) {
        Random rand = new Random();
        for (Student s : students)
            for (int i = 0; i < amount; i++) s.addTestScore(rand.nextInt(50) + 50);
    }
}

/*
 * This class provides a utility method used to generate unique ID values for each student.
 *
 * In a production environment, another mechanism (like your Database) may be providing
 * these IDs instead
 */
class StudentIdGenerator {
    private static Long id = Long.valueOf(1000);
    public static Long generateStudentId() { return id++; }
}
