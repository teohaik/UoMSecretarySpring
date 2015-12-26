/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uom.uomsecretaryspring.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Georgios Digkas <mai153@uom.edu.gr>
 */
@Embeddable
public class StudentEnrolledToLessonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "student")
    private String student;
    @Basic(optional = false)
    @Column(name = "lesson_id")
    private int lessonId;

    public StudentEnrolledToLessonPK() {
    	//Empty constructor for setting values
    }

    public StudentEnrolledToLessonPK(String student, int lessonId) {
        this.student = student;
        this.lessonId = lessonId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (student != null ? student.hashCode() : 0);
        hash += (int) lessonId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentEnrolledToLessonPK)) {
            return false;
        }
        StudentEnrolledToLessonPK other = (StudentEnrolledToLessonPK) object;
        if ((this.student == null && other.student != null) || (this.student != null && !this.student.equals(other.student))) {
            return false;
        }
        if (this.lessonId != other.lessonId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gr.uom.uomsecretaryspring.domain.StudentEnrolledToLessonPK[ student=" + student + ", lessonId=" + lessonId + " ]";
    }
    
}
