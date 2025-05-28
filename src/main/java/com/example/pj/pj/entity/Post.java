//package com.example.pj.pj.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.*;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
//public class Post{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
////
//    @Column(name = "title", nullable = false)
//    private String title;
//
//    @Column(name = "describtion", nullable = false)
//    private String describtion; // Consider renaming to "description" if it's a typo.
//
//    @Column(name = "content", nullable = false)
//    private String content;
//
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();
//}
//
