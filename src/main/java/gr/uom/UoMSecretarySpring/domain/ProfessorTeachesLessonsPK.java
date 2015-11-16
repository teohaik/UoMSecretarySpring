/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uom.UoMSecretarySpring.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Georgios Digkas <mai153@uom.edu.gr>
 */
@Embeddable
public class ProfessorTeachesLessonsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "professor")
    private String professor;
    @Basic(optional = false)
    @Column(name = "lesson_id")
    private int lessonId;

    public ProfessorTeachesLessonsPK() {
    }

    public ProfessorTeachesLessonsPK(String professor, int lessonId) {
        this.professor = professor;
        this.lessonId = lessonId;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
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
        hash += (professor != null ? professor.hashCode() : 0);
        hash += (int) lessonId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfessorTeachesLessonsPK)) {
            return false;
        }
        ProfessorTeachesLessonsPK other = (ProfessorTeachesLessonsPK) object;
        if ((this.professor == null && other.professor != null) || (this.professor != null && !this.professor.equals(other.professor))) {
            return false;
        }
        if (this.lessonId != other.lessonId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gr.uom.UoMSecretarySpring.domain.ProfessorTeachesLessonsPK[ professor=" + professor + ", lessonId=" + lessonId + " ]";
    }
    
}
