package com.alan.entity;

public abstract class Entity {
    private static int nonRepeatedId;
    private int id;

    public Entity() {
        this.id = nonRepeatedId;
        nonRepeatedId++;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" +
                "id=" + id +
                '}';
    }
}
