/**
 * 
 */
package gr.uom.UoMSecretarySpring.dao;

import java.util.List;

import gr.uom.UoMSecretarySpring.domain.ProfessorTeachesLessons;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
public interface ProfessorTeachesLessonsDao {

	void insert(ProfessorTeachesLessons professorTeachesLessons);
	void update(ProfessorTeachesLessons professorTeachesLessons);
	void delete(ProfessorTeachesLessons professorTeachesLessons);

	void insert(List<ProfessorTeachesLessons> professorTeachesLessons);
	void update(List<ProfessorTeachesLessons> professorTeachesLessons);
	void delete(List<ProfessorTeachesLessons> professorTeachesLessons);

	ProfessorTeachesLessons findByProfessorAndLessonId(String professor, Integer lessonId);
}
