package com.example.springbootdieta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name = "gender")
    private boolean gender;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name ="user_role",
            joinColumns = @JoinColumn(name="USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ROLE_ID", referencedColumnName="ID"))
    private Set<Role> roles = new HashSet<>();


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @OrderBy("date")
//    private Set<Weight> weight = new HashSet<>();

//    public void addWeight(Weight weight){
//        this.weight.add(weight);
//    }
}


