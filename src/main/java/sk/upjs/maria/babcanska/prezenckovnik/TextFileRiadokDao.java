package sk.upjs.maria.babcanska.prezenckovnik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TextFileRiadokDao implements RiadokDao {

   private File subor;
   private DateTimeFormatter formatter;

   public TextFileRiadokDao(final File subor) {
      this.subor = subor;
   }

   //zapisuje do suborov...

   @Override
   public List<Riadok> getAll() {
      List<Riadok> vysledok = new ArrayList<>();
      try (Scanner scanner = new Scanner(subor)) {
         scanner.nextLine();

         while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Riadok riadok = new Riadok();
            Scanner citacRiadku = new Scanner(line);
            citacRiadku.useDelimiter(",");
            riadok.setId(citacRiadku.nextLong());

            String datum = citacRiadku.next();
            if (!datum.equals("null")) {
               formatter = DateTimeFormatter.ofPattern("d.M.yyyy H:m");
               LocalDateTime dateTime = LocalDateTime.parse(datum, formatter);
               riadok.setDatum(dateTime);
            }

            riadok.setPoradieUdalosti(citacRiadku.nextLong());
            riadok.setNazovPredmetu(citacRiadku.next());
            riadok.setOsoba(citacRiadku.next());
            vysledok.add(riadok);

         }
         return vysledok;
      } catch (FileNotFoundException e) {
         //e.printStackTrace();
         try {
            subor.createNewFile();
         } catch (IOException e1) {
            e1.printStackTrace();
            new TextFileRiadokDaoException("Subor sa po nenajdeni ani nepodarilo vytvorit." + subor.getPath(), e1);
         }
      }

      return null;
   }

   @Override
   public Riadok vymazPodlaIdRiadok(final Long id) {
      List<Riadok> vsetky = getAll();
      Riadok zmazany = null;

      for (Iterator<Riadok> it = vsetky.iterator(); it.hasNext(); ) {
         Riadok r = it.next();

         if (r.getId().equals(id)) {
            it.remove();
            zmazany = r;
            break;
         }
      }

      try (PrintWriter pw = new PrintWriter(subor)) {
         pw.println("id,datum,poradie udalosti,nazov predmetu,osoba");
         for (Riadok r : vsetky) {
            zapisRiadok(pw, r);
         }
      } catch (Exception e) {
         throw new TextFileRiadokDaoException("Neviem najst subor");
      }

      return zmazany;
   }

   @Override
   public Riadok pridajRiadok(final Riadok riadok) {
      checkRiadok(riadok);
      List<Riadok> riadky = getAll();
      Long maxId = Long.MIN_VALUE;

      for (Riadok riadok1 : riadky) {
         if (riadok1.getId() > maxId) {
            maxId = riadok1.getId();
         }
      }
      riadok.setId(++maxId);

      //moznost 1
      // vytvoris FileWriter a potom PrintWritera jemu das do konstruktoru  FileWriter
      // a vtedy ti PrintWriter nemaze pri printe
      try (FileWriter fileWriter = new FileWriter(subor, true);) {

         PrintWriter printWriter = new PrintWriter(fileWriter);
         zapisRiadok(printWriter, riadok);

      } catch (IOException e) {
         e.printStackTrace();
         throw new TextFileRiadokDaoException("Neviem otvorit subor s riadkami" + subor.getPath() + "na zapis");
      }

      return riadok;
   }

   private boolean checkRiadok(Riadok riadok) {
      if (riadok.getNazovPredmetu() == null || riadok.getPoradieUdalosti() == null || riadok.getOsoba() == null) {
         throw new TextFileRiadokDaoException("Tieto vecicky su povinne");
      }
      return true;
   }

   public void zapisRiadok(PrintWriter printWriter, Riadok riadok) {
      printWriter.print(riadok.getId());
      printWriter.print(",");

      if (riadok.getDatum() != null) {
         printWriter.print(riadok.getDatum().format(formatter));
      } else {
         printWriter.print("null");
      }
      printWriter.print(",");
      printWriter.print(riadok.getPoradieUdalosti());
      printWriter.print(",");
      printWriter.print(riadok.getNazovPredmetu());
      printWriter.print(",");
      printWriter.println(riadok.getOsoba());
   }

}
