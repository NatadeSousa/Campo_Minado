import br.gov.caixa.campo_minado.modelo.Campo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteDeExibirCampo {

    @Test
    void testarExibicaoDeCampo(){
        Campo campo = new Campo(2,2);
        assertEquals(campo.toString(), " ? ");

//        campo.alternarMarcacao();
//        assertEquals(campo.toString(), " X ");

        campo.aberto = true;
        campo.minar();
//        campo.abrirCampo();
        assertEquals(campo.toString(), " * ");
        campo.retirarMina();
        assertEquals(campo.toString(), "   ");

    }

}
