package com.x.quant.udemy.rabbitmq.rabbit_with_springboot;

import java.io.Serializable;

public class Person implements Serializable {

    private Long id;
    private String name;


    // Constructors
    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
