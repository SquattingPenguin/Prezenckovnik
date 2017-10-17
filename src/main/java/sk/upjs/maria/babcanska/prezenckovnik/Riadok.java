package sk.upjs.maria.babcanska.prezenckovnik;

import java.time.LocalDateTime;

public class Riadok {
   private Long id;
   private LocalDateTime datum;
   private Long poradieUdalosti;

   public Long getPoradieUdalosti() {
      return poradieUdalosti;
   }

   public void setPoradieUdalosti(final Long poradieUdalosti) {
      this.poradieUdalosti = poradieUdalosti;
   }

   private String osoba;
   private String nazovPredmetu;

   public Long getId() {
      return id;
   }

   public void setId(final Long id) {
      this.id = id;
   }

   public LocalDateTime getDatum() {
      return datum;
   }

   public void setDatum(final LocalDateTime datum) {
      this.datum = datum;
   }

   public String getOsoba() {
      return osoba;
   }

   public Riadok(final Long id, final LocalDateTime datum, final String osoba, final String nazovPredmetu, Long poradieUdalosti) {
      this.id = id;
      this.datum = datum;
      this.osoba = osoba;
      this.nazovPredmetu = nazovPredmetu;
      this.poradieUdalosti =poradieUdalosti;
   }

   public Riadok(){

   }

   public void setOsoba(final String osoba) {
      this.osoba = osoba;
   }

   public String getNazovPredmetu() {
      return nazovPredmetu;
   }

   public void setNazovPredmetu(final String nazovPredmetu) {
      this.nazovPredmetu = nazovPredmetu;
   }

   @Override
   public String toString() {
      return "Riadok{" +
            "id=" + id +
            ", datum=" + datum +
            ", osoba='" + osoba + '\'' +
            ", nazovPredmetu='" + nazovPredmetu + '\'' +
            '}';
   }
}
