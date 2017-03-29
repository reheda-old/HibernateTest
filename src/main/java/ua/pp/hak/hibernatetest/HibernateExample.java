package ua.pp.hak.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ua.pp.hak.hibernatetest.entity.Exam;
import ua.pp.hak.hibernatetest.entity.Student;
import ua.pp.hak.hibernatetest.entity.Subject;
import ua.pp.hak.hibernatetest.report.ExamResult;
import ua.pp.hak.hibernatetest.report.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rev on 3/29/2017.
 */
public class HibernateExample {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();


        Session session = sessionFactory.openSession();

        //start transaction
        Transaction transaction = session.beginTransaction();

//        insertData(session);

/*        Student student = session.get(Student.class, 1);
        Set<Exam> exams = student.getExams();
        student.setAge(40);*/

        Query query = session.createQuery("from Student ");
        List<Student> students = query.getResultList();


        Report report = new Report();
        List<ExamResult> examResults = report.getExamResults("John", "Wayne", session);


        List<Exam> examList = report.getExamResultsWithCriteria("John", "Wayne", session);

        //end transaction
        transaction.commit();

//        close seesion
        session.close();
        sessionFactory.close();
    }

    private static void insertData(Session session) {
        List<Student> students = insertStudents(session);
        List<Subject> subjects = insertSubjects(session);
        insertExams(session, students, subjects);
    }

    private static List<Exam> insertExams(Session session, List<Student> students, List<Subject> subjects) {
        List<Exam> list = new ArrayList<Exam>();

        list.add(new Exam(students.get(0), subjects.get(0), 5));
        list.add(new Exam(students.get(0), subjects.get(1), 4));
        list.add(new Exam(students.get(0), subjects.get(2), 5));

        for (int i = 0; i < list.size(); i++) {
            session.save(list.get(i));
        }
        return list;
    }

    private static List<Subject> insertSubjects(Session session) {
        List<Subject> list = new ArrayList<Subject>();
        list.add(new Subject("History"));
        list.add(new Subject("Math"));
        list.add(new Subject("Biology"));

        for (int i = 0; i < list.size(); i++) {
            session.save(list.get(i));
        }
        return list;
    }

    private static List<Student> insertStudents(Session session) {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("John", "Wayne", 40));
        list.add(new Student("Tommy", "Gun", 25));
        list.add(new Student("Katty", "White", 23));

        for (int i = 0; i < list.size(); i++) {
            session.save(list.get(i));
        }
        return list;
    }
}
