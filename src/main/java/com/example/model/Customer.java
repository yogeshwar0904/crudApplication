package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Customer {

    @Id

     public Long id;
     public String name;
     public Long phoneNumber;
     public String scheme;

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

     @Override
     public String toString() {
        return " "+id+" "+name+" "+ phoneNumber+" "+ scheme;
     }
}
