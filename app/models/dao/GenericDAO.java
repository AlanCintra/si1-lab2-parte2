package models.dao;

import models.Meta;
import play.db.jpa.JPA;

import javax.persistence.Query;
import java.util.List;

/**
 * Chamada genérica para acesso ao Banco de Dados
 */
public class GenericDAO {

    public boolean persist(Object e) {
        JPA.em().persist(e);
        return true;
    }

    public void flush() {
        JPA.em().flush();
    }

    public void merge(Object e) {
        JPA.em().merge(e);
    }

    public void atualiza(Long id) {
        Meta meta = (Meta) this.findByEntityId(id);
        meta.setAlcancada();
        JPA.em().merge(meta);
    }


    public <T> List<T> findAllByClassName(String className) {
        String hql = "FROM " + className;
        Query hqlQuery = JPA.em().createQuery(hql);
        return hqlQuery.getResultList();
    }

    public <T> List<T> findByAttributeName(String className,
                                           String attributeName, String attributeValue) {
        String hql = "FROM " + className + " c" + " WHERE c." + attributeName
                + " = '" + attributeValue + "'";
        Query hqlQuery = JPA.em().createQuery(hql);
        return hqlQuery.getResultList();
    }

    public Query createQuery(String query) {
        return JPA.em().createQuery(query);
    }

    public void delete(Long id) {
        JPA.em().remove(findByEntityId(id));
    }

    private Object findByEntityId(Long id) {
        return JPA.em().find(Meta.class, id);
    }
}
