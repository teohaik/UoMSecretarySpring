/**
 * 
 */
package gr.uom.UoMSecretarySpring.domain.wrapper;

import java.util.List;

import gr.uom.UoMSecretarySpring.domain.StudentEnrolledToLesson;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
public class StudentEnrolledToLessonWrapper {

	private List<StudentEnrolledToLesson> studentsEnrolledToLessonList;

	public StudentEnrolledToLessonWrapper() {}

	public List<StudentEnrolledToLesson> getStudentsEnrolledToLessonList() {
		return studentsEnrolledToLessonList;
	}

	public void setStudentsEnrolledToLessonList(List<StudentEnrolledToLesson> studentsEnrolledToLessonList) {
		this.studentsEnrolledToLessonList = studentsEnrolledToLessonList;
	}
	
}
