package controllers;

import models.Meta;
import models.dao.GenericDAO;
import play.*;
import play.api.libs.Collections;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;
import java.util.List;

public class Application extends Controller {

    private static final GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result index() {
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        return ok(index.render(metas));
    }



    @Transactional
    public static Result criaMeta() {
        DynamicForm form = Form.form().bindFromRequest();

        String descricao = form.get("descricao");
        String prioridade = form.get("prioridades");
        String semana = form.get("semanas");

        Meta meta = new Meta(descricao, prioridade, semana);
        dao.persist(meta);

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        meta.ordenaMetas(metas);
        return  ok(index.render(metas));
    }

    @Transactional
    public static Result deletaMeta(Long id) {
        dao.delete(id);
        dao.flush();
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        return ok(index.render(metas));
    }

    @Transactional
    public static Result destacaMetaAlcancada(Long id) {
        dao.atualiza(id);
        dao.flush();
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        return ok(index.render(metas));
    }
}
