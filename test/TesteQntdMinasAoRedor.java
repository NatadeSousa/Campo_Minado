import br.gov.caixa.campo_minado.modelo.Campo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TesteQntdMinasAoRedor {

    @Test
    void testarQntdMinasAoRedor(){
        Campo campo = new Campo(2,2);

        Campo vizinho11 = new Campo(1,1);
        Campo vizinho12 = new Campo(1,2);
        Campo vizinho13 = new Campo(1,3);
        Campo vizinho21 = new Campo(2,1);
        Campo vizinho33 = new Campo(3,3);
        campo.adicionar_vizinho(vizinho11);
        campo.adicionar_vizinho(vizinho12);
        campo.adicionar_vizinho(vizinho13);
        campo.adicionar_vizinho(vizinho21);
        campo.adicionar_vizinho(vizinho33);

        vizinho11.minar();
        vizinho12.minar();
        vizinho13.minar();

        campo.abrirCampo();

        assertEquals(campo.getQntdMinasAoRedor(), 3);

    }

}
