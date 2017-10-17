package sk.upjs.maria.babcanska.prezenckovnik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class RiadokDaoTest {
   @Test
   void pridajRiadok() {
   }

   @Test
   public void pridajRiadokTest() {

      //Takto to urobit aj inde
      RiadokDao riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();
      int velkost = riadokDao.getAll().size();
      riadokDao.pridajRiadok(new Riadok(15L, LocalDateTime.now(), "Gursky", "PAZ1C", 5L));
      assertTrue(riadokDao.getAll().size() == velkost + 1);
   }

   @Test
   public void pridajZleDataTest() {
      Riadok riadok = new Riadok();
      riadok.setOsoba("Tomas");
      RiadokDao riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();
      assertThrows(TextFileRiadokDaoException.class, () -> riadokDao.pridajRiadok(riadok));
      // assertTrue(false);
   }

   public void vymazPodlaIdRiadok() {
      RiadokDao riadokDao = new MemoryRiadokDao();
      riadokDao.vymazPodlaIdRiadok(2L);
      List<Riadok> riadky = riadokDao.getAll();

      for (Riadok riadok : riadky) {
         assertTrue(!riadok.getId().equals(2L));
      }
   }

   @Test
   public void getAllTest() {
      RiadokDao riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();
      List<Riadok> riadky = riadokDao.getAll();
      assertNotNull(riadky);

      if (riadky != null) {
         assertTrue(riadky.size() > 0);
      }
   }

   @Test
   public void mamNejakeData()
   {

      RiadokDao riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();
      List<Riadok> riadky = riadokDao.getAll();

      assertTrue(riadky.size()>0);

   }
}