package com.mukesh.securityex.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Users {
    @Id
    private Integer id ;
    private String userName;
    private String password;

}
