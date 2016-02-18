package org.library.wrappers;

/**
 * Created by sebas on 17.02.2016.
 */
public class UserWrapper {
    public static class User {
        public Integer id;
        public String email;
        public String nazwisko;
        public String imie;
        public String avatar;
        public String typ;
        public Boolean aktywny;

        public User(Integer id, String email, String nazwisko, String imie, String avatar, String typ,
                    Boolean aktywny){
            this.aktywny = aktywny;
            this.typ = typ;
            this.id = id;
            this.email = email;
            this.nazwisko = nazwisko;
            this.imie = imie;
            this.avatar = avatar;
        }
    }

    public static class UserDetails{
        public User user;
        public Double debet;
        public AddressWrapper.Address address;
        public PhoneWrapper.Phone phone;

        public UserDetails(User user, AddressWrapper.Address address, PhoneWrapper.Phone phone, Double debet){
            this.user = user;
            this.address = address;
            this.phone = phone;
            this.debet = debet;
        }
    }

    public static class LoginData {
        public Integer id;
        public String email;
        public String haslo;
        public String seed;
        public String typ;
        public String avatar;

        public LoginData(Integer id, String email, String haslo, String seed, String typ, String avatar){
            this.avatar = avatar;
            this.email = email;
            this.haslo = haslo;
            this.seed = seed;
            this.id = id;
            this.typ = typ;
        }
    }

    public static class UserId {
        private static UserId ourInstance = new UserId();

        public static UserId getInstance() {
            return ourInstance;
        }

        private UserId() {
        }

        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
