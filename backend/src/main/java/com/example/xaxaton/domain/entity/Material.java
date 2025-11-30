package com.example.xaxaton.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long course_id;
    private String file_link;
    private String title;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", file_link='" + file_link + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
