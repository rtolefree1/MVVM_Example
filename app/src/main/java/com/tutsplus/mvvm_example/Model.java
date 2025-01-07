package com.tutsplus.mvvm_example;

import androidx.annotation.Nullable;

public class Model {
    @Nullable
    String email, password, userName;

    // Constructor
    public Model(@Nullable String email, @Nullable String password){
        this.email = email;
        this.password = password;
    }

    // Getter and setter methods
    // for email variables
    @Nullable
    public String getEmail(){
        return email;
    }

    public void setEmail(@Nullable String email){
        this.email = email;
    }

    // getter and setter methods
    // for password variable
    @Nullable
    public String getPassword(){
        return password;
    }

    public void setPassword(@Nullable String password){
        this.password = password;
    }

    @Nullable
    public String getUsername(){
        return userName;
    }

    public void setUserName(@Nullable String userName){
        this.userName = userName;
    }
}
