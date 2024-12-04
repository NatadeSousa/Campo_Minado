import br.gov.caixa.campo_minado.excecoes.ExplosionException;
import br.gov.caixa.campo_minado.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TesteDeVizinhancaSegura {

    Campo campo;

    @BeforeEach
    void criar_campo(){
        campo = new Campo(2,2);
    }

    @Test
    void testarVizinhanca(){
        Campo vizinho = new Campo(1,3);
        Campo vizinho1 = new Campo(1,1);
        Campo vizinho2 = new Campo(2,1);
        Campo vizinho3 = new Campo(3,3);
        Campo vizinho4 = new Campo(4,4);
        campo.adicionar_vizinho(vizinho);
        campo.adicionar_vizinho(vizinho1);
        campo.adicionar_vizinho(vizinho2);
        campo.adicionar_vizinho(vizinho3);
        vizinho3.adicionar_vizinho(vizinho4);

        assertEquals(vizinho3.verificarSegurancaVizinhanca(), false);
        assertThrows(ExplosionException.class, () -> {
            campo.minar();
            campo.abrirCampo();
        });
    }

}
