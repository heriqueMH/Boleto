import java.time.LocalDate;

class Boleto {
    private String sacado;
    private double valor;
    private LocalDate vencimento;
    private String cedente;
    private int agencia;
    private int conta;
    private boolean status;
    private double juros;

    public Boleto(String sacado, double valor, LocalDate vencimento, String cedente, int agencia, int conta) {
        this.sacado = sacado;
        this.valor = valor;
        this.vencimento = vencimento;
        this.cedente = cedente;
        this.agencia = agencia;
        this.conta = conta;
        this.status = false;
        this.juros = 0.0;
    }

    public int calcularAtraso(LocalDate data) {
        int diasAtraso = (int) Math.max(0, vencimento.until(data).getDays());
        return diasAtraso;
    }

    public double calcularJuros(int diasAtraso) {
        double jurosBase = valor * 0.05; // 5% de juros
        double moraDiaria = valor * 0.001; // 0.1% de mora diária
        juros = jurosBase + (moraDiaria * diasAtraso);
        return juros;
    }

    public double calcularValorAPagar() {
        double valorPagar = valor + juros;
        return valorPagar;
    }

    public void pagar() {
        LocalDate hoje = LocalDate.now();
        int diasAtraso = (int) Math.max(0, vencimento.until(hoje).getDays()); // Qualquer valor negativo será ajustado para zero, indicando que não há atraso.

        if (diasAtraso > 0) {
            status = false; // Boleto está atrasado
        } else {
            status = true; // Boleto foi pago em dia
        }

        status = true;
        valor = 0;
        juros = 0;

    }

    public String statusBoleto(boolean status, int diasAtraso) {
        if (status) {
            return "Status: Boleto pago.";
        } else if (diasAtraso == 0) {
            return "Status: Boleto não foi pago.";
        } else if (diasAtraso > 0) {
            return "Status: Boleto atrasado.";
        } else {
            return "Status: Boleto com dados inválidos.";
        }
    }
    

    public void mostrarDados() {
        System.out.println("Dados do Boleto:");
        System.out.println("Sacado: " + sacado);
        System.out.println("Valor: R$" + valor);
        System.out.println("Vencimento: " + vencimento);
        System.out.println("Cedente: " + cedente);
        System.out.println("Agência: " + agencia);
        System.out.println("Conta: " + conta);
        System.out.println(statusBoleto(status, calcularAtraso(vencimento)));
        System.out.println("Juros: R$" + juros);
        System.out.println("O valor a pagar é de: R$" + calcularValorAPagar());
        System.out.println("-----------------------------------");
    }
}
