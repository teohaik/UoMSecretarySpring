/**
 * 
 */
package gr.uom.UoMSecretarySpring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uom.UoMSecretarySpring.dao.StudentEnrolledToLessonDao;
import gr.uom.UoMSecretarySpring.domain.StudentEnrolledToLesson;
import gr.uom.UoMSecretarySpring.service.StudentEnrolledToLessonService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Service
public class StudentEnrolledToLessonServiceImpl implements StudentEnrolledToLessonService {

	private StudentEnrolledToLessonDao studentEnrolledToLessonDao;

	public void setStudentEnrolledToLessonDao(StudentEnrolledToLessonDao studentEnrolledToLessonDao) {
		this.studentEnrolledToLessonDao = studentEnrolledToLessonDao;
	}

	@Override
	@Transactional
	public void insert(StudentEnrolledToLesson studentEnrolledToLessons) {
		studentEnrolledToLessonDao.insert(studentEnrolledToLessons);
	}

	@Override
	@Transactional
	public void update(StudentEnrolledToLesson studentEnrolledToLessons) {
		studentEnrolledToLessonDao.update(studentEnrolledToLessons);
	}

	@Override
	@Transactional
	public void delete(StudentEnrolledToLesson studentEnrolledToLessons) {
		studentEnrolledToLessonDao.delete(studentEnrolledToLessons);
	}

	@Override
	@Transactional
	public void insert(List<StudentEnrolledToLesson> studentEnrolledToLessons) {
		studentEnrolledToLessonDao.insert(studentEnrolledToLessons);
	}

	@Override
	@Transactional
	public void update(List<StudentEnrolledToLesson> studentEnrolledToLessons) {
		studentEnrolledToLessonDao.update(studentEnrolledToLessons);
	}

	@Override
	@Transactional
	public void delete(List<StudentEnrolledToLesson> studentEnrolledToLessons) {
		studentEnrolledToLessonDao.delete(studentEnrolledToLessons);
	}

	@Override
	@Transactional
	public List<StudentEnrolledToLesson> findByStudent(String student) {
		return studentEnrolledToLessonDao.findByStudent(student);
	}

	@Override
	@Transactional
	public StudentEnrolledToLesson findByStudentAndLessonId(String student, Integer lessonId) {
		return studentEnrolledToLessonDao.findByStudentAndLessonId(student, lessonId);
	}

	@Override
	@Transactional
	public List<StudentEnrolledToLesson> findByLessonId(int lessonId) {
		return studentEnrolledToLessonDao.findByLessonId(lessonId);
	}

}
