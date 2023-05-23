package org.example.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Entity<ID extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345L;

    private ID id;

    public Entity(){

    }

    public Entity(ID id){
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
