package com.example.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity

public class Customer {

    @Id
     public Long id;
     public String name;
     public Long phoneNumber;
     public String scheme;
     public String password;

     public Long getID() {
         return id;
     }

     public void setID(Long id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public Long getPhoneNumber(){
         return phoneNumber;
     }
     public void setPhoneNumber(Long phoneNumber){
         this.phoneNumber = phoneNumber;
    }

    public String getPassword(){
         return password;
    }

    public void setPassword(String password){
         this.password = password;
    }

     @Override
     public String toString() {
        return " "+id+" "+name+" "+ phoneNumber+" "+ scheme;
     }

     @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
     private Set<Role> roles = new HashSet<>();

     public  Set<Role> getRoles(){
         return  roles;
     };

     public void setRoles(Set<Role> roles){
         this.roles = roles;
     }
}
