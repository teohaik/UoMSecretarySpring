package gr.uom.uomsecretaryspring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uom.uomsecretaryspring.dao.LessonDao;
import gr.uom.uomsecretaryspring.domain.Lesson;
import gr.uom.uomsecretaryspring.service.LessonService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Service
public class LessonServiceImpl implements LessonService {

	private LessonDao lessonDao;

	public void setLessonDao(LessonDao lessonDao) {
		this.lessonDao = lessonDao;
	}

	@Override
	@Transactional
	public void insert(Lesson lesson) {
		lessonDao.insert(lesson);
	}

	@Override
	@Transactional
	public void update(Lesson lesson) {
		lessonDao.update(lesson);
	}

	@Override
	@Transactional
	public void delete(Lesson lesson) {
		lessonDao.delete(lesson);
	}

	@Override
	@Transactional
	public void delete(int id) {
		lessonDao.delete(id);
	}

	@Override
	@Transactional
	public Lesson findById(int id) {
		return lessonDao.findById(id);
	}

	@Override
	@Transactional
	public List<Lesson> findAll() {
		return lessonDao.findAll();
	}

	@Override
	@Transactional
	public List<Lesson> findByIds(List<Integer> ids) {
		return lessonDao.findByIds(ids);
	}

	@Override
	@Transactional
	public List<Lesson> findByNotEnrolledStudent(String student) {
		return lessonDao.findByNotEnrolledStudent(student);
	}

	@Override
	@Transactional
	public List<Lesson> findByEnrolledStudent(String student) {
		return lessonDao.findByEnrolledStudent(student);
	}

	@Override
	@Transactional
	public List<Lesson> findByNotTeachProfessor(String professor) {
		return lessonDao.findByNotTeachProfessor(professor);
	}

	@Override
	@Transactional
	public List<Lesson> findByTeachProfessor(String professor) {
		return lessonDao.findByTeachProfessor(professor);
	}

}
