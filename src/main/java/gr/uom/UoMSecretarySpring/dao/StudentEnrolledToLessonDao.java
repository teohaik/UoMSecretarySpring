/**
 * 
 */
package gr.uom.uomsecretaryspring.dao;

import java.util.List;

import gr.uom.uomsecretaryspring.domain.StudentEnrolledToLesson;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
public interface StudentEnrolledToLessonDao {

	void insert(StudentEnrolledToLesson studentEnrolledToLessons);
	void update(StudentEnrolledToLesson studentEnrolledToLessons);
	void delete(StudentEnrolledToLesson studentEnrolledToLessons);

	void insert(List<StudentEnrolledToLesson> studentEnrolledToLessons);
	void update(List<StudentEnrolledToLesson> studentEnrolledToLessons);
	void delete(List<StudentEnrolledToLesson> studentEnrolledToLessons);

	List<StudentEnrolledToLesson> findByStudent(String student);
	List<StudentEnrolledToLesson> findByLessonId(int lessonId);
	
	StudentEnrolledToLesson findByStudentAndLessonId(String student, Integer lessonId);
}
