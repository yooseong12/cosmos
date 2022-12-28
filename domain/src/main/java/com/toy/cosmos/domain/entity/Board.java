package com.toy.cosmos.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @NotNull
    @Column(unique = true)
    @Size(max = 50)
    String title;

    @NotNull
    String content;

    Integer hits;

    Integer liked;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<AttachedFile> attachedFiles;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Comment> comments;

}
