import play.*;

import models.Meta;
import models.dao.GenericDAO;
import play.db.jpa.JPA;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Collections;
import java.util.List;


public class Global extends GlobalSettings {

    public static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("Aplicação inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Meta meta = new Meta("Fazer a parte 2 do lab2 de SI1", "1", "1");
                Meta meta2 = new Meta("Fazer a parte 1 do lab2 de SI1", "2", "1");
                Meta meta3 = new Meta("Estudar PLP", "3", "1");
                Meta meta4 = new Meta("Fazer a parte 3 do Lab2 de SI1", "1", "2");
                Meta meta5 = new Meta("Estudar PLP", "2", "2");
                Meta meta6 = new Meta("Esudar Probabilidade", "4", "2");
                Meta meta7 = new Meta("Estudar Probabilidade", "2", "3");
                Meta meta8 = new Meta("Estudar Lógica", "3", "3");
                Meta meta9 = new Meta("Estudar Física Moderna", "3", "3");
                Meta meta10 = new Meta("Estudar Álgebra Linear", "3", "3");

                dao.persist(meta);
                dao.persist(meta2);
                dao.persist(meta3);
                dao.persist(meta4);
                dao.persist(meta5);
                dao.persist(meta6);
                dao.persist(meta7);
                dao.persist(meta8);
                dao.persist(meta9);
                dao.persist(meta10);

                dao.flush();
            }});
    }

    @Override
    public void onStop(Application app){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Logger.info("Aplicação finalizando...");
                List<Meta> metas = dao.findAllByClassName(Meta.class.getName());

                for (Meta meta : metas) {
                    dao.delete(meta.getId());
                }
            }});
    }
}
