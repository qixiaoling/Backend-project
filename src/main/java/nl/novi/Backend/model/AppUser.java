package nl.novi.Backend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (columnDefinition = "serial")
    private Long User_id;
    @Column
    @NotNull
    private String userName;
    @Column
    private String password;
    @NotNull
    @Email
    @Column
    private String email;
    @NotNull
    @Min(10)
    private String feedback;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            joinColumns = { @JoinColumn },
            inverseJoinColumns = {@JoinColumn}
    )
    private Set<Role> roles;

    public AppUser() {

    }

    public AppUser(Long user_id, String userName, String password, String email) {
        User_id = user_id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Long getUser_id() {
        return User_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
