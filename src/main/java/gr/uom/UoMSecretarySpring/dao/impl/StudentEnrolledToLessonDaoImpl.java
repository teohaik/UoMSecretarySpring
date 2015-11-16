/**
 * 
 */
package gr.uom.UoMSecretarySpring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import gr.uom.UoMSecretarySpring.dao.StudentEnrolledToLessonDao;
import gr.uom.UoMSecretarySpring.domain.StudentEnrolledToLesson;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Repository
public class StudentEnrolledToLessonDaoImpl implements StudentEnrolledToLessonDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(StudentEnrolledToLesson studentEnrolledToLessons) {
		Session session = this.sessionFactory.getCurrentSession();
		if (checkIfExists(studentEnrolledToLessons))
			session.persist(studentEnrolledToLessons);
	}

	@Override
	public void update(StudentEnrolledToLesson studentEnrolledToLessons) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(studentEnrolledToLessons);
	}

	@Override
	public void delete(StudentEnrolledToLesson studentEnrolledToLessons) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(studentEnrolledToLessons);
	}

	@Override
	public void insert(List<StudentEnrolledToLesson> studentEnrolledToLessons) {
		for (StudentEnrolledToLesson studentEnrolledToLesson : studentEnrolledToLessons) {
			this.insert(studentEnrolledToLesson);
		}
	}

	@Override
	public void update(List<StudentEnrolledToLesson> studentEnrolledToLessons) {
		for (StudentEnrolledToLesson studentEnrolledToLesson : studentEnrolledToLessons) {
			this.update(studentEnrolledToLesson);
		}
	}

	@Override
	public void delete(List<StudentEnrolledToLesson> studentEnrolledToLessons) {
		for (StudentEnrolledToLesson studentEnrolledToLesson : studentEnrolledToLessons) {
			this.delete(studentEnrolledToLesson);
		}
	}

	public List<StudentEnrolledToLesson> findByStudent(String student) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("StudentEnrolledToLesson.findByStudent");
		query.setString("student", student);
		List<StudentEnrolledToLesson> studentEnrolledToLessons = query.list();
		return studentEnrolledToLessons;
	}

	@Override
	public StudentEnrolledToLesson findByStudentAndLessonId(String student, Integer lessonId) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("StudentEnrolledToLesson.findByStudentAndLessonId");
		query.setInteger("lessonId", lessonId);
		query.setString("student", student);
		return (StudentEnrolledToLesson) query.uniqueResult();
	}

	@Override
	public List<StudentEnrolledToLesson> findByLessonId(int lessonId) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("StudentEnrolledToLesson.findByLessonId");
		query.setInteger("lessonId", lessonId);
		List<StudentEnrolledToLesson> StudentEnrolledToLessonList = query.list();
		return StudentEnrolledToLessonList;
	}

	private boolean checkIfExists(StudentEnrolledToLesson studentEnrolledToLessons) {
		StudentEnrolledToLesson tmpStudentEnrolledToLessons = findByStudentAndLessonId(studentEnrolledToLessons.getStudentEnrolledToLessonPK().getStudent(), studentEnrolledToLessons.getStudentEnrolledToLessonPK().getLessonId());
		return tmpStudentEnrolledToLessons == null;
	}

}