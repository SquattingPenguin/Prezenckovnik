package sk.upjs.maria.babcanska.prezenckovnik;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MemoryRiadokDao implements RiadokDao {
   List<Riadok> riadky = new ArrayList<>();
   private long lastId = 0l;

   @Override
   public List<Riadok> getAll() {
      return riadky;
   }

   @Override
   public Riadok vymazPodlaIdRiadok(Long id) {
      for (Riadok riadok : riadky) {
         if (riadok.getId().equals(id)) {
            riadky.remove(id);
            return riadok;
         }
      }
      //riadky.removeIf(riadok -> riadok.getId() == id);
      return null;
   }

   public MemoryRiadokDao() {
      Riadok riadok = new Riadok();
      riadok.setId(0L);
      riadok.setNazovPredmetu("PAZ1C");
      riadok.setOsoba("Maria");
      riadok.setDatum(LocalDateTime.of(2017, Month.OCTOBER, 3, 9, 58));

      Riadok riadok1 = new Riadok();
      riadok1.setId(1L);
      riadok1.setNazovPredmetu("PAZ1C");
      riadok1.setOsoba("Simon");
      riadok1.setDatum(LocalDateTime.of(2017, Month.OCTOBER, 3, 8, 55));

      riadky.add(riadok);
      riadky.add(riadok1);
   }

   //CREATE
   @Override
   public Riadok pridajRiadok(Riadok riadok) {
      riadok.setId(++lastId);
      riadky.add(riadok);
      return riadok;
   }

}
