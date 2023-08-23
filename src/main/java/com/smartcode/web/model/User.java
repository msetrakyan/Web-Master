package com.smartcode.web.model;

import java.math.BigDecimal;

public class User {

    private Integer id;

    private String name;

    private String lastName;

    private String middleName;

    private String username;

    private Integer age;

    private String password;

    private BigDecimal balance;

    public User() {
    }

    public User(Integer id, String name, String lastName, String middleName, String username, Integer age, String password, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = username;
        this.age = age;
        this.password = password;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
