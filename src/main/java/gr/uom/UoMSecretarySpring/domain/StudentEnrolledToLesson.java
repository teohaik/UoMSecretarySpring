/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uom.uomsecretaryspring.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Georgios Digkas <mai153@uom.edu.gr>
 */
@Entity
@Table(name = "student_enrolled_to_lesson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentEnrolledToLesson.findAll", query = "SELECT s FROM StudentEnrolledToLesson s"),
    @NamedQuery(name = "StudentEnrolledToLesson.findByStudent", query = "SELECT s FROM StudentEnrolledToLesson s WHERE s.studentEnrolledToLessonPK.student = :student"),
    @NamedQuery(name = "StudentEnrolledToLesson.findByLessonId", query = "SELECT s FROM StudentEnrolledToLesson s WHERE s.studentEnrolledToLessonPK.lessonId = :lessonId"),
    @NamedQuery(name = "StudentEnrolledToLesson.findByGrade", query = "SELECT s FROM StudentEnrolledToLesson s WHERE s.grade = :grade"),
    @NamedQuery(name = "StudentEnrolledToLesson.findByStudentAndLessonId", query = "SELECT s FROM StudentEnrolledToLesson s WHERE s.studentEnrolledToLessonPK.lessonId = :lessonId AND s.studentEnrolledToLessonPK.student = :student")})
public class StudentEnrolledToLesson implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentEnrolledToLessonPK studentEnrolledToLessonPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "grade")
    private Double grade;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lesson lesson;
    @JoinColumn(name = "student", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public StudentEnrolledToLesson() {
    	//Empty constructor for setting values
    }

    public StudentEnrolledToLesson(StudentEnrolledToLessonPK studentEnrolledToLessonPK) {
        this.studentEnrolledToLessonPK = studentEnrolledToLessonPK;
    }

    public StudentEnrolledToLesson(String student, int lessonId) {
        this.studentEnrolledToLessonPK = new StudentEnrolledToLessonPK(student, lessonId);
    }

    public StudentEnrolledToLessonPK getStudentEnrolledToLessonPK() {
        return studentEnrolledToLessonPK;
    }

    public void setStudentEnrolledToLessonPK(StudentEnrolledToLessonPK studentEnrolledToLessonPK) {
        this.studentEnrolledToLessonPK = studentEnrolledToLessonPK;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentEnrolledToLessonPK != null ? studentEnrolledToLessonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentEnrolledToLesson)) {
            return false;
        }
        StudentEnrolledToLesson other = (StudentEnrolledToLesson) object;
        if ((this.studentEnrolledToLessonPK == null && other.studentEnrolledToLessonPK != null) || (this.studentEnrolledToLessonPK != null && !this.studentEnrolledToLessonPK.equals(other.studentEnrolledToLessonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gr.uom.uomsecretaryspring.domain.StudentEnrolledToLesson[ studentEnrolledToLessonPK=" + studentEnrolledToLessonPK + " ]";
    }
    
}
