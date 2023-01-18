package com.toy.cosmos.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toy.cosmos.domain.common.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    Board board;

    @Enumerated(EnumType.STRING)
    Status.Comment status;
}
