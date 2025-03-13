package com.example.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class CustomerDTO {
    @Id
    Long id;
    String name;
    Long phoneNumber;
    String scheme;
    @ElementCollection
    Set<Role> role;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber(){
        return phoneNumber;
    }

    public  void setPhoneNumber(Long phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getRole(){
        return role;
    }

    public void setRole(Set<Role> role){
        this.role = role;
    }

    public String getScheme(){
        return scheme;
    }

    public void setScheme(String scheme){
        this.scheme = scheme;
    }

}



