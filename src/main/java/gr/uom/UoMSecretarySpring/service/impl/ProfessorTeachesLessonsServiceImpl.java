/**
 * 
 */
package gr.uom.uomsecretaryspring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uom.uomsecretaryspring.dao.ProfessorTeachesLessonsDao;
import gr.uom.uomsecretaryspring.domain.ProfessorTeachesLessons;
import gr.uom.uomsecretaryspring.service.ProfessorTeachesLessonsService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Service
public class ProfessorTeachesLessonsServiceImpl implements ProfessorTeachesLessonsService {

	private ProfessorTeachesLessonsDao professorTeachesLessonsDao;

	public void setProfessorTeachesLessonsDao(ProfessorTeachesLessonsDao professorTeachesLessonsDao) {
		this.professorTeachesLessonsDao = professorTeachesLessonsDao;
	}

	@Override
	@Transactional
	public void insert(ProfessorTeachesLessons professorTeachesLessons) {
		professorTeachesLessonsDao.insert(professorTeachesLessons);
	}

	@Override
	@Transactional
	public void update(ProfessorTeachesLessons professorTeachesLessons) {
		professorTeachesLessonsDao.update(professorTeachesLessons);
	}

	@Override
	@Transactional
	public void delete(ProfessorTeachesLessons professorTeachesLessons) {
		professorTeachesLessonsDao.delete(professorTeachesLessons);
	}

	@Override
	@Transactional
	public void insert(List<ProfessorTeachesLessons> professorTeachesLessons) {
		professorTeachesLessonsDao.insert(professorTeachesLessons);
	}

	@Override
	@Transactional
	public void update(List<ProfessorTeachesLessons> professorTeachesLessons) {
		professorTeachesLessonsDao.update(professorTeachesLessons);
	}

	@Override
	@Transactional
	public void delete(List<ProfessorTeachesLessons> professorTeachesLessons) {
		professorTeachesLessonsDao.delete(professorTeachesLessons);
	}

	@Override
	@Transactional
	public ProfessorTeachesLessons findByProfessorAndLessonId(String professor, Integer lessonId) {
		return professorTeachesLessonsDao.findByProfessorAndLessonId(professor, lessonId);
	}

}
