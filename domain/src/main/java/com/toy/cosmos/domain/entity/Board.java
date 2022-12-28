package com.toy.cosmos.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(unique = true)
    @Size(max = 50)
    String title;

    @NotNull
    String content;

    @NotNull
    String writer;

    Integer hits;

    Integer like;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
