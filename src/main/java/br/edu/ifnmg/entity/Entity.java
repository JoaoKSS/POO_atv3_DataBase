package br.edu.ifnmg.entity;

public abstract class Entity {

    private Long id;
    private boolean excluded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluido(boolean excluded) {
        this.excluded = excluded;
    }
    

}
