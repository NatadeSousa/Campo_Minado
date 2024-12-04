import br.gov.caixa.campo_minado.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteDeAbrirCampo {

    Campo campo;

    @BeforeEach
    void criar_campo(){
        campo = new Campo(2,2);
    }

    @Test
    void testarAbrirCampoEVizinhos(){
        Campo vizinho = new Campo(1,3);
        Campo vizinho1 = new Campo(1,1);
        Campo vizinho2 = new Campo(2,1);
        Campo vizinho3 = new Campo(3,3);
        vizinho3.minar();
        campo.adicionar_vizinho(vizinho);
        campo.adicionar_vizinho(vizinho1);
        campo.adicionar_vizinho(vizinho2);
        campo.adicionar_vizinho(vizinho3);
        campo.abrirCampo();

        assertFalse(vizinho1.isAberto() && vizinho2.isAberto() && vizinho3.isAberto());


    }

}
