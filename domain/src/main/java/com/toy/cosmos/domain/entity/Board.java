package com.toy.cosmos.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toy.cosmos.domain.common.Status;
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
    @Size(max = 50)
    String title;

    @NotNull
    String content;

    Integer hits;

    Integer liked;

    @Enumerated(EnumType.STRING)
    Status.Board status;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Comment> comments;
}
