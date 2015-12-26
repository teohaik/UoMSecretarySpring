/**
 * 
 */
package gr.uom.uomsecretaryspring.dao;

import java.util.List;

import gr.uom.uomsecretaryspring.domain.Lesson;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
public interface LessonDao {
	void insert(Lesson lesson);
	void update(Lesson lesson);
	void delete(Lesson lesson);
	void delete(int id);
	Lesson findById(int id);
	List<Lesson> findAll();
	List<Lesson> findByIds(List<Integer> ids);
	List<Lesson> findByNotEnrolledStudent(String student);
	List<Lesson> findByEnrolledStudent(String student);
	List<Lesson> findByNotTeachProfessor(String professor);
	List<Lesson> findByTeachProfessor(String professor);
}
