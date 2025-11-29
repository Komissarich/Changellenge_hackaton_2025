package com.example.xaxaton.domain.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "schedule")
    private String schedule;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "difficulty")
    private String difficulty;
    @Column(name = "duration")
    private String duration;
    @Column(name = "author_id")
    private Long author_id;
    @Column(name = "cover_link")
    private String cover_link;


//    @ManyToMany
//    @JoinTable(
//            name = "course_teachers",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id")
//    )
//    private Set<Teacher> teachers = new HashSet<>();
//
//    public Set<Teacher> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(Set<Teacher> teachers) {
//        this.teachers = teachers;
//    }

    public String getCover_link() {
        return cover_link;
    }

    public void setCover_link(String cover_link) {
        this.cover_link = cover_link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", schedule='" + schedule + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", duration='" + duration + '\'' +
                ", author_id=" + author_id +
                ", cover_link='" + cover_link + '\'' +
                '}';
    }
}
