package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_category")
    private Long idCategory;

    @Column(name= "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Team> categoryTeams =new HashSet<Team>();

    public Category() {
    }

    public Category(Long idCategory, String categoryName, Set<Team> categoryTeams) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryTeams = categoryTeams;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Team> getCategoryTeams() {
        return categoryTeams;
    }

    public void setCategoryTeams(Set<Team> categoryTeams) {
        this.categoryTeams = categoryTeams;
    }
}
