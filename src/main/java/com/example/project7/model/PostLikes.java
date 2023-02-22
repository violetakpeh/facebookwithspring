package com.example.project7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="likes")
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postLikeId;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
