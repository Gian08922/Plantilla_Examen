package ufv.dis.final2022test.bien;

public class Inforecibida {
    private String entity;
    private int id;

    public Inforecibida(String entity, int id) {
        this.entity = entity;
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
