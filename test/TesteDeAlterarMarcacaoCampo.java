import br.gov.caixa.campo_minado.modelo.Campo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteDeAlterarMarcacaoCampo {
    @Test
    void testeAlterarMarcacao(){

        Campo campo = new Campo(2,1);
        campo.alternarMarcacao();
        campo.alternarMarcacao();


    }
}
