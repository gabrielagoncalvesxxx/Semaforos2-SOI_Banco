import java.util.Random;
import java.util.concurrent.Semaphore;

public class Controler {
    private static final Semaphore semaforoSaque = new Semaphore(1);
    private static final Semaphore semaforoDeposito = new Semaphore(1);

    public static void executarSimulacao() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int tipoTransacao = random.nextInt(2); // 0 para saque, 1 para depósito
            new Thread(new Transacao(tipoTransacao)).start();
        }
    }

    static class Transacao implements Runnable {
        private final int tipo; // 0 para saque, 1 para depósito

        Transacao(int tipo) {
            this.tipo = tipo;
        }

        @Override
        public void run() {
            try {
                if (tipo == 0) { // Saque
                    semaforoSaque.acquire();
                    View.mostrarProcessandoTransacao(tipo);
                    Thread.sleep(1000); 
                    semaforoSaque.release();
                } else { // Depósito
                    semaforoDeposito.acquire();
                    View.mostrarProcessandoTransacao(tipo);
                    Thread.sleep(1000); 
                    semaforoDeposito.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
