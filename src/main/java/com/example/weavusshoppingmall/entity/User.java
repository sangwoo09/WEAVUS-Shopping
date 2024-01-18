package com.example.weavusshoppingmall.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.time.LocalDate;

@Data
//@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class User {

    private String id;
    private String password;
    private String userEmail;
    private int cardNumber;
    private int cardCvc;
    private String userName;
    private String userEnName;
    private LocalDate createdDate;
    private String isActive;


}
