package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_id")
    private Long categoryId;

    @Column(name= "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Team> categoryTeams =new HashSet<Team>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName, Set<Team> categoryTeams) {
        this.categoryName = categoryName;
        this.categoryTeams = categoryTeams;
    }

    public void setId(Long id) {
    }

}
