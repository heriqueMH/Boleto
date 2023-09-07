import java.time.LocalDate;

class App {
    public static void main(String[] args) {
        LocalDate vencimento = LocalDate.of(2023, 4, 6);

        Boleto boleto = new Boleto("Antonio Ferrreira", 3000.0, vencimento, "ACME Corp. Inc.", 123, 45678901);

        System.out.println("Dados do Boleto antes do pagamento: ");

        LocalDate pagamento = LocalDate.of(2023, 4, 11); // pagamento 5 dias apos o vencimento
        int diasAtraso = boleto.calcularAtraso(pagamento);
        if (diasAtraso > 0) {
            boleto.calcularJuros(diasAtraso);
            System.out.println("Dias de Atraso: " + diasAtraso);
        }

        boleto.mostrarDados();
        boleto.pagar();

        System.out.println("\nDados do Boleto após o pagamento:");
        boleto.mostrarDados();

        // Compromisso
        LocalDate dataCompromisso = LocalDate.of(2023, 10, 15);
        Boleto compromisso = new Boleto("Maria Silva", 1500.0, dataCompromisso, "Empresa XYZ", 456, 98765432);
        

        System.out.println("\nDados do Compromisso:");
        compromisso.mostrarDados();
        System.out.println();

        System.out.println("Adiantando o comrpomisso...");
        compromisso.pagar();
        compromisso.mostrarDados();
    }
}

/* 
   Matheus Henrique de Olviera Santos
   TIA - 42208149
 */
