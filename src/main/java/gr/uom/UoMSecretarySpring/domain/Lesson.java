/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uom.UoMSecretarySpring.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Georgios Digkas <mai153@uom.edu.gr>
 */
@Entity
@Table(name = "lesson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lesson.findAll", query = "SELECT l FROM Lesson l"),
    @NamedQuery(name = "Lesson.findById", query = "SELECT l FROM Lesson l WHERE l.id = :id"),
    @NamedQuery(name = "Lesson.findByDescription", query = "SELECT l FROM Lesson l WHERE l.description = :description"),
    @NamedQuery(name = "Lesson.findByEcts", query = "SELECT l FROM Lesson l WHERE l.ects = :ects"),
    @NamedQuery(name = "Lesson.findByName", query = "SELECT l FROM Lesson l WHERE l.name = :name"),
    @NamedQuery(name = "Lesson.findBySemester", query = "SELECT l FROM Lesson l WHERE l.semester = :semester"),
    @NamedQuery(name = "Lesson.findByNotTeachProfessor", query = "SELECT l FROM Lesson l WHERE l.id NOT IN (SELECT p.professorTeachesLessonsPK.lessonId FROM ProfessorTeachesLessons p WHERE p.professorTeachesLessonsPK.professor = :professor)"),
	@NamedQuery(name = "Lesson.findByTeachProfessor", query = "SELECT l FROM Lesson l WHERE l.id IN (SELECT p.professorTeachesLessonsPK.lessonId FROM ProfessorTeachesLessons p WHERE p.professorTeachesLessonsPK.professor = :professor)"),
	@NamedQuery(name = "Lesson.findByIds", query = "SELECT l FROM Lesson l WHERE l.id IN :ids")})
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "ECTS")
    private Integer ects;
    @Column(name = "name")
    private String name;
    @Column(name = "semester")
    private Character semester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private Collection<StudentEnrolledToLesson> studentEnrolledToLessonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private Collection<ProfessorTeachesLessons> professorTeachesLessonsCollection;

    public Lesson() {
    }

    public Lesson(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSemester() {
        return semester;
    }

    public void setSemester(Character semester) {
        this.semester = semester;
    }

    @XmlTransient
    public Collection<StudentEnrolledToLesson> getStudentEnrolledToLessonCollection() {
        return studentEnrolledToLessonCollection;
    }

    public void setStudentEnrolledToLessonCollection(Collection<StudentEnrolledToLesson> studentEnrolledToLessonCollection) {
        this.studentEnrolledToLessonCollection = studentEnrolledToLessonCollection;
    }

    @XmlTransient
    public Collection<ProfessorTeachesLessons> getProfessorTeachesLessonsCollection() {
        return professorTeachesLessonsCollection;
    }

    public void setProfessorTeachesLessonsCollection(Collection<ProfessorTeachesLessons> professorTeachesLessonsCollection) {
        this.professorTeachesLessonsCollection = professorTeachesLessonsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lesson)) {
            return false;
        }
        Lesson other = (Lesson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gr.uom.UoMSecretarySpring.domain.Lesson[ id=" + id + " ]";
    }
    
}
