package io.github.gabrielhgcamargo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "login field shouldn't be null!")
    private String login;

    @Column
    @NotEmpty(message = "login password shouldn't be null!")
    private String password;

    @Column
    private boolean admin;

}
