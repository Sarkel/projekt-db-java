package org.library.wrappers;

/**
 * Created by sebas on 17.02.2016.
 */
public class AddressWrapper {
    public static class Address{
        public String ulica;
        public Integer numerDomu;
        public Integer numerMieszkania;
        public String kodPocztowy;
        public String miejscowosc;
        public String kraj;

        public Address(String ulica, Integer numerDomu, Integer numerMieszkania, String kodPocztowy,
                       String miejscowosc, String kraj){
            this.kodPocztowy = kodPocztowy;
            this.kraj = kraj;
            this.miejscowosc = miejscowosc;
            this.numerDomu = numerDomu;
            this.numerMieszkania = numerMieszkania;
            this.ulica = ulica;
        }
    }
}
