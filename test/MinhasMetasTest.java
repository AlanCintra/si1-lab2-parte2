
import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;

import controllers.Application;
import models.Meta;
import models.dao.GenericDAO;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alan on 21/11/2014.
 */
public class MinhasMetasTest extends AbstractTest {

    private GenericDAO dao = new GenericDAO();

    @Test
    public void deveIniciarSemMetas() throws Exception {
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas).isEmpty();
    }

    @Test
    public void deveSalvarMetaNoBD() {
        Meta meta = new Meta("Estudar PLP", "Baixa", "1");
        dao.persist(meta);

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);
    }

    @Test
    public void deveSalvarMetaNaSemana1() throws  Exception {
        Meta meta = new Meta("Estudar PLP", "Baixa", "1");
        dao.persist(meta);

        assertThat(meta.getSemana()).isEqualTo("1");
    }

    @Test
    public void deveSalvarMetaNaSemana2() throws  Exception {
        Meta meta = new Meta("Estudar Lógica", "Baixa", "2");
        dao.persist(meta);

        assertThat(meta.getSemana()).isEqualTo("2");
    }

    @Test
    public void deveRemoverMetaDoBD() throws  Exception {
        Map<String, String> form = new HashMap<>();
        form.put("descricao", "Estudar SI1");
        form.put("prioridades", "1");
        form.put("semanas", "1");
        callAction(controllers.routes.ref.Application.criaMeta(), fakeRequest().withFormUrlEncodedBody(form));

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());

        assertThat(metas.size()).isEqualTo(1);

        callAction(controllers.routes.ref.Application.deletaMeta(metas.get(0).getId()), fakeRequest());
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void deveDestacarMeta() throws  Exception {
        Map<String, String> form = new HashMap<>();
        form.put("descricao", "Estudar SI1");
        form.put("prioridades", "1");
        form.put("semanas", "1");
        callAction(controllers.routes.ref.Application.criaMeta(), fakeRequest().withFormUrlEncodedBody(form));

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());

        assertThat(metas.get(0).alcancada()).isFalse();
        metas.get(0).setAlcancada();
        assertThat(metas.get(0).alcancada()).isTrue();
    }
}
