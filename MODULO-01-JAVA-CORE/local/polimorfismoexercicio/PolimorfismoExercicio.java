
import java.util.List;
import java.util.logging.Logger;

abstract class Veiculo {
    static final Logger logger = Logger.getLogger(Veiculo.class.getName());
    abstract void acelerar();
    void frear() { logger.info("Freando..."); }
    void frear(int potencia) {
        if (potencia > 50) {
            logger.info("Derrapando...");
        } else {
            logger.info("Freando suavemente");

        }
    };
}

class Carro extends Veiculo implements Dirigivel
 {
    @Override
    void acelerar() { logger.info("Carro acelerando"); }
    void acelerar(int potencia) { logger.info(() -> "Acelerando com " + potencia + "cv"); }
    @Override
    public void virar() { logger.info("Carro virando"); }

}

class Moto extends Veiculo {
    @Override
    void acelerar() { logger.info("Moto acelerando"); }
}

interface Dirigivel {
    void virar();
}

public class PolimorfismoExercicio {
    public static void main(String[] args) {
        List<Veiculo> veiculos = List.of(new Carro(), new Moto());
        veiculos.forEach(Veiculo::acelerar); // Polimorfismo
    
        Carro carro = new Carro();
        carro.acelerar(100);
        carro.frear(80);
        carro.virar();
        carro.frear();
    }
}
