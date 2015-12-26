/**
 * 
 */
package gr.uom.uomsecretaryspring.dao.impl;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import gr.uom.uomsecretaryspring.dao.LessonDao;
import gr.uom.uomsecretaryspring.domain.Lesson;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Repository
public class LessonDaoImpl implements LessonDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(Lesson lesson) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(lesson);
	}

	@Override
	public void update(Lesson lesson) {
		this.sessionFactory.getCurrentSession().merge(lesson);
	}

	@Override
	public void delete(Lesson lesson) {
		this.sessionFactory.getCurrentSession().delete(lesson);
	}

	@Override
	public void delete(int id) {
		Lesson lesson = findById(id);
		if (lesson != null)
			delete(lesson);
	}

	@Override
	public Lesson findById(int id) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findById");
		query.setInteger("id", id);
		return (Lesson) query.uniqueResult();
	}

	@Override
	public List<Lesson> findAll() {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findAll");
		return (List<Lesson>) query.list();
	}

	@Override
	public List<Lesson> findByIds(List<Integer> ids) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findByIds");
		query.setParameterList("ids", ids);
		return (List<Lesson>) query.list();
	}

	@Override
	public List<Lesson> findByNotEnrolledStudent(String student) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findByNotEnrolledStudent");
		query.setString("student", student);
		return (List<Lesson>) query.list();
	}

	@Override
	public List<Lesson> findByEnrolledStudent(String student) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findByEnrolledStudent");
		query.setString("student", student);
		return (List<Lesson>) query.list();
	}

	@Override
	public List<Lesson> findByNotTeachProfessor(String professor) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findByNotTeachProfessor");
		query.setString("professor", professor);
		return (List<Lesson>) query.list();
	}

	@Override
	public List<Lesson> findByTeachProfessor(String professor) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("Lesson.findByTeachProfessor");
		query.setString("professor", professor);
		return (List<Lesson>) query.list();
	}

}
