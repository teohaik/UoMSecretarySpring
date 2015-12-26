/**
 * 
 */
package gr.uom.uomsecretaryspring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import gr.uom.uomsecretaryspring.dao.ProfessorTeachesLessonsDao;
import gr.uom.uomsecretaryspring.domain.ProfessorTeachesLessons;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Repository
public class ProfessorTeachesLessonsDaoImpl implements ProfessorTeachesLessonsDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(ProfessorTeachesLessons professorTeachesLessons) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(professorTeachesLessons);
	}

	@Override
	public void update(ProfessorTeachesLessons professorTeachesLessons) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(professorTeachesLessons);
	}

	@Override
	public void delete(ProfessorTeachesLessons professorTeachesLessons) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(professorTeachesLessons);
	}

	@Override
	public void insert(List<ProfessorTeachesLessons> professorTeachesLessons) {
		for (ProfessorTeachesLessons professorTeachesLessons2 : professorTeachesLessons) {
			this.insert(professorTeachesLessons2);
		}
	}

	@Override
	public void update(List<ProfessorTeachesLessons> professorTeachesLessons) {
		for (ProfessorTeachesLessons professorTeachesLessons2 : professorTeachesLessons) {
			this.update(professorTeachesLessons2);
		}
	}

	@Override
	public void delete(List<ProfessorTeachesLessons> professorTeachesLessons) {
		for (ProfessorTeachesLessons professorTeachesLessons2 : professorTeachesLessons) {
			this.delete(professorTeachesLessons2);
		}
	}

	@Override
	public ProfessorTeachesLessons findByProfessorAndLessonId(String professor, Integer lessonId) {
		Query query = this.sessionFactory.getCurrentSession().getNamedQuery("ProfessorTeachesLessons.findByProfessorAndLessonId");
		query.setString("professor", professor);
		query.setInteger("lessonId", lessonId);
		return (ProfessorTeachesLessons) query.uniqueResult();
	}



}
