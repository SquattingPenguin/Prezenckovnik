package sk.upjs.maria.babcanska.prezenckovnik;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RiadokFxModel {
   //Chces vyplnit meno a udalost
   private int pocetChyb = 2;
   private StringProperty udalost = new SimpleStringProperty();
   private StringProperty ucastnik = new SimpleStringProperty();
   private StringProperty poradie = new SimpleStringProperty("1");

   public String getUdalost() {
      return udalost.get();
   }

   public StringProperty udalostProperty() {
      return udalost;
   }

   public void setUdalost(final String udalost) {
      this.udalost.set(udalost);
   }

   public String getUcastnik() {
      return ucastnik.get();
   }

   public StringProperty ucastnikProperty() {
      return ucastnik;
   }

   public void setUcastnik(final String ucastnik) {
      this.ucastnik.set(ucastnik);
   }

   public String getPoradie() {
      return poradie.get();
   }

   public StringProperty poradieProperty() {
      return poradie;
   }

   public void setPoradie(final String poradie) {
      this.poradie.set(poradie);
   }

   public Riadok getRiadok() {
      Riadok riadok = new Riadok();
      riadok.setNazovPredmetu(getUdalost());
      riadok.setOsoba(getUcastnik());
      riadok.setPoradieUdalosti(Long.parseLong(getPoradie()));
      riadok.setDatum(LocalDateTime.now());
      return riadok;
   }

   public int getPocetChyb() {
      return pocetChyb;
   }

   public void setPocetChyb(int pocetChyb) {
      this.pocetChyb = pocetChyb;
   }
}
