package org.bancoDigital.util;

public class CPFUtils {

    public static String recebeCPF(String cpf){
        while (!tamanhpCPF(cpf)) {
            System.out.println("Cpf inválido.");
            cpf = InputScanner.lerString("CPF: ");            
        }

        return cpf;
    }

    public static boolean tamanhpCPF(String cpf){
        String numeros = cpf.replaceAll("[^0-9]", "");
        return numeros.length() == 11;
    }
    public static String formatarCPF(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
    
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }
        
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (10 - i) * Character.getNumericValue(cpf.charAt(i));
            }
            
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;
            
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (11 - i) * Character.getNumericValue(cpf.charAt(i));
            }
            
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;
            
            return (Character.getNumericValue(cpf.charAt(9)) == digito1) && 
                   (Character.getNumericValue(cpf.charAt(10)) == digito2);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
