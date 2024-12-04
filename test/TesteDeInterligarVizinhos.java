import br.gov.caixa.campo_minado.modelo.Campo;
import br.gov.caixa.campo_minado.modelo.Tabuleiro;
import org.junit.jupiter.api.Test;

public class TesteDeInterligarVizinhos {

    @Test
    void interligarVizinhos(){

        Campo c00 = new Campo(0,0);
        Campo c01 = new Campo(0,1);
        Campo c02 = new Campo(0,2);
        Campo c10 = new Campo(1,0);
        Campo c11 = new Campo(1,1);
        Campo c12 = new Campo(1,2);
        Campo c20 = new Campo(2,0);
        Campo c21 = new Campo(2,1);
        Campo c22 = new Campo(2,2);

        Tabuleiro tabuleiro = new Tabuleiro(3,3,1);

        System.out.println(c00.getVizinhos());

    }

}
