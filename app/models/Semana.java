package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 28/11/2014.
 */
@Entity
public class Semana {

    @Id
    @GeneratedValue
    private Long id;

    private String semana;

    @OneToMany
    @JoinTable
    private List<Meta> metas;

    public Semana() {
        this.metas = new ArrayList<Meta>();
    }

    public Semana(String semana) {
        this.semana = semana;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }
}
