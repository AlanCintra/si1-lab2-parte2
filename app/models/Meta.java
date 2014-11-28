package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alan on 21/11/2014.
 */
@Entity
public class Meta implements Comparable<Meta> {

    @Id
    @GeneratedValue
    private Long id;

    private String descricao;
    private String prioridade;
    private String semana;

    private int total;
    private boolean alcancada = false;

    public Meta(String descricao, String prioridade, String semana) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.semana = semana;
    }

    public Meta(){
    }

    public int totalDeMetasDaSemana1(List<Meta> metas) {
        total = 0;
        for(int i = 0; i <= metas.size(); i++) {
            if(metas.get(i).getSemana().contentEquals("1")) {
                total++;
            }
        }
        return total;
    }

    public int getTotal() {
        return total;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(Meta meta) {
        if(this.getSemana().equals(meta.getSemana())) {
            return this.getPrioridade().compareTo(meta.getPrioridade());
        }else {
            return this.getSemana().compareTo(meta.getSemana());
        }
    }

    public void ordenaMetas(List<Meta> metas) {
        Collections.sort(metas);
    }

    public boolean alcancada() {
        return alcancada;
    }

    public void setAlcancada() {
        this.alcancada = true;
    }
}
