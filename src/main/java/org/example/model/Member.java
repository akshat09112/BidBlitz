package org.example.model;

public class Member {
    private String id;
    private String name;
    private Integer coins;

    public Member(String id, String name, Integer coins) {
        this.id = id;
        this.name = name;
        this.coins = coins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }
}
