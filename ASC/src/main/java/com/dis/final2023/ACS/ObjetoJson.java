package com.dis.final2023.ACS;

import java.util.List;

public class ObjetoJson {
    List<ClassDato1> data;

    public ObjetoJson(List<ClassDato1> data) {
        this.data = data;
    }

    public List<ClassDato1> getData() {
        return data;
    }

    public void setData(List<ClassDato1> data) {
        this.data = data;
    }
}
