package ua.pp.hak.hibernatetest.report;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ua.pp.hak.hibernatetest.entity.Exam;

import java.util.List;

/**
 * Created by rev on 3/29/2017.
 */
public class Report {

    public List<ExamResult> getExamResults(String firstName, String lastName, Session session) {
        String query = "select new ua.pp.hak.hibernatetest.report.ExamResult(s.lastName, sj.name, e.grade)" +
                "from Exam e, Student s, Subject sj " +
                "where e.student.id = s.id and e.subject.id = sj.id " +
                "and s.firstName = :firstName and s.lastName = :lastName";

        Query hibernateQuery = session.createQuery(query);
        hibernateQuery.setParameter("firstName", firstName);
        hibernateQuery.setParameter("lastName", lastName);

        return hibernateQuery.getResultList();

    }

    public List<ExamResult> getExamResultsLight(String firstName, String lastName, Session session) {
        String query = "select new ua.pp.hak.hibernatetest.report.ExamResult(e.student.lastName, e.subject.name, e.grade)" +
                "from Exam e " +
                "where e.student.firstName = :firstName and e.student.lastName = :lastName";

        Query hibernateQuery = session.createQuery(query);
        hibernateQuery.setParameter("firstName", firstName);
        hibernateQuery.setParameter("lastName", lastName);

        return hibernateQuery.getResultList();

    }

    public List<Exam> getExamResultsWithCriteria(String firstName, String lastName, Session session) {
        Criteria criteria = session.createCriteria(Exam.class);
        criteria.setMaxResults(10);
        criteria.createAlias("student", "s");
        criteria.add(
                Restrictions.and(
                        Restrictions.eq("s.firstName", firstName),
                        Restrictions.eq("s.lastName", lastName)
                )
        );

        return criteria.list();

    }


}
