public class View {
    public static void mostrarIntroducao() {
        System.out.println("Iniciando a Simulação de Transações Bancárias...");
    }

    public static void mostrarProcessandoTransacao(int tipo) {
        String tipoTransacao = tipo == 0 ? "Saque" : "Depósito";
        System.out.println("Processando " + tipoTransacao + ".");
    }
}
