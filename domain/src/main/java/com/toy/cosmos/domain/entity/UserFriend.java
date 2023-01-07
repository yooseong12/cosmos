package com.toy.cosmos.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toy.cosmos.domain.common.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "friendId"})
})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFriend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @Positive
    Long friendId;

    @Enumerated(EnumType.STRING)
    Status.UserFriend status;

}