package com.ICS.ImageClassifier.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

    public String username;

    public String email;

    public String password;
}
