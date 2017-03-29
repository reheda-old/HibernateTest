package ua.pp.hak.hibernatetest.entity;

import javax.persistence.*;

/**
 * Created by rev on 3/29/2017.
 */

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_exams_students_student_id"))
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "FK_exams_subjects_subject_id"))
    private Subject subject;

    @Column(name = "grade")
    private int grade;


    public Exam() {
    }

    public Exam(Student student, Subject subject, int grade) {
        this.student = student;
        this.subject = subject;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", student=" + student +
                ", subject=" + subject +
                ", grade=" + grade +
                '}';
    }
}
