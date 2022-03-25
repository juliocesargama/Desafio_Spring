package br.com.meli.Desafio_Spring.entity;

public enum UF {
        AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MG, MS, MT, PA, PB, PE, PI, PR, RJ, RN, RO, RR, RS, SC, SE, SP, TO;

        public static boolean isUF(String value) {
                for (UF uf : UF.values()) {
                        if ( uf.name().equalsIgnoreCase(value) ) return true;
                }
                return false;
        }
}