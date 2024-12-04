import br.gov.caixa.campo_minado.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesteDeVizinho {

    private Campo campo;

    @BeforeEach
    void inicia_campo(){
        campo = new Campo(2,2);
    }

    @Test
    void testar_adicionar_vizinho(){
        Campo vizinho = new Campo(3,1);
        assertEquals(campo.adicionar_vizinho(vizinho), true);
    }

}
