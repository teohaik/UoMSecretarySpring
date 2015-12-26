/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uom.uomsecretaryspring.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Georgios Digkas <mai153@uom.edu.gr>
 */
@Entity
@Table(name = "professor_teaches_lessons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfessorTeachesLessons.findAll", query = "SELECT p FROM ProfessorTeachesLessons p"),
    @NamedQuery(name = "ProfessorTeachesLessons.findByProfessor", query = "SELECT p FROM ProfessorTeachesLessons p WHERE p.professorTeachesLessonsPK.professor = :professor"),
    @NamedQuery(name = "ProfessorTeachesLessons.findByLessonId", query = "SELECT p FROM ProfessorTeachesLessons p WHERE p.professorTeachesLessonsPK.lessonId = :lessonId"),
    @NamedQuery(name = "ProfessorTeachesLessons.findByStartedFrom", query = "SELECT p FROM ProfessorTeachesLessons p WHERE p.startedFrom = :startedFrom"),
    @NamedQuery(name = "ProfessorTeachesLessons.findByProfessorAndLessonId", query = "SELECT p FROM ProfessorTeachesLessons p WHERE p.professorTeachesLessonsPK.professor = :professor AND p.professorTeachesLessonsPK.lessonId = :lessonId")})
public class ProfessorTeachesLessons implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProfessorTeachesLessonsPK professorTeachesLessonsPK;
    @Basic(optional = false)
    @Column(name = "started_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedFrom;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lesson lesson;
    @JoinColumn(name = "professor", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public ProfessorTeachesLessons() {
    	//Empty constructor for setting values
    }

    public ProfessorTeachesLessons(ProfessorTeachesLessonsPK professorTeachesLessonsPK) {
        this.professorTeachesLessonsPK = professorTeachesLessonsPK;
    }

    public ProfessorTeachesLessons(ProfessorTeachesLessonsPK professorTeachesLessonsPK, Date startedFrom) {
        this.professorTeachesLessonsPK = professorTeachesLessonsPK;
        this.startedFrom = startedFrom;
    }

    public ProfessorTeachesLessons(String professor, int lessonId) {
        this.professorTeachesLessonsPK = new ProfessorTeachesLessonsPK(professor, lessonId);
    }

    public ProfessorTeachesLessonsPK getProfessorTeachesLessonsPK() {
        return professorTeachesLessonsPK;
    }

    public void setProfessorTeachesLessonsPK(ProfessorTeachesLessonsPK professorTeachesLessonsPK) {
        this.professorTeachesLessonsPK = professorTeachesLessonsPK;
    }

    public Date getStartedFrom() {
        return startedFrom;
    }

    public void setStartedFrom(Date startedFrom) {
        this.startedFrom = startedFrom;
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
        hash += (professorTeachesLessonsPK != null ? professorTeachesLessonsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfessorTeachesLessons)) {
            return false;
        }
        ProfessorTeachesLessons other = (ProfessorTeachesLessons) object;
        if ((this.professorTeachesLessonsPK == null && other.professorTeachesLessonsPK != null) || (this.professorTeachesLessonsPK != null && !this.professorTeachesLessonsPK.equals(other.professorTeachesLessonsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gr.uom.uomsecretaryspring.domain.ProfessorTeachesLessons[ professorTeachesLessonsPK=" + professorTeachesLessonsPK + " ]";
    }
    
}
