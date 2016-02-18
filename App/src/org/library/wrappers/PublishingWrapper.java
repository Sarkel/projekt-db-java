package org.library.wrappers;

/**
 * Created by sebas on 17.02.2016.
 */
public class PublishingWrapper {
    public static class Publishing {
        public Integer id;
        public String nazwa;
        public String kraj;

        public Publishing(Integer id, String nazwa, String kraj){
            this.id = id;
            this.kraj = kraj;
            this.nazwa = nazwa;
        }
    }
}
