import static org.fest.assertions.Assertions.*;

import models.Meta;
import models.dao.GenericDAO;
import org.junit.Test;

import java.util.List;

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
    public void deveSalvarMetaNaSemana3() throws  Exception {
        Meta meta = new Meta("Estudar SI1", "Baixa", "3");
        dao.persist(meta);

        assertThat(meta.getSemana()).isEqualTo("3");
    }

    @Test
    public void deveSalvarMetaNaSemana4() throws  Exception {
        Meta meta = new Meta("Estudar Física Moderna", "Baixa", "4");
        dao.persist(meta);

        assertThat(meta.getSemana()).isEqualTo("4");
    }

    @Test
    public void deveSalvarMetaNaSemana5() throws  Exception {
        Meta meta = new Meta("Estudar Álgebra Linear", "Baixa", "5");
        dao.persist(meta);

        assertThat(meta.getSemana()).isEqualTo("5");
    }

    @Test
    public void deveSalvarMetaNaSemana6() throws  Exception {
        Meta meta = new Meta("Estudar Probabilidade", "Baixa", "6");
        dao.persist(meta);

        assertThat(meta.getSemana()).isEqualTo("6");
    }

    @Test
    public void deveRemoverMetaDoBD() throws  Exception {
        Meta meta = new Meta("Estudar Probabilidade", "Baixa", "6");
        dao.persist(meta);
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());

        assertThat(metas.size()).isEqualTo(1);

        dao.delete(meta.getId());
        dao.flush();
        assertThat(metas.size()).isEqualTo(0);
    }

}
