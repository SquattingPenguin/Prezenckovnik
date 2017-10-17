package sk.upjs.maria.babcanska.prezenckovnik;

import java.io.File;

public enum RiadokDaoFactory {

   // 1 instancia RiadokDao
   INSTANCE;

   //tovaren vyrabajuca RiadokDao
   //

   //chceme iba jednu instanciu RiadokDaoFactory - singleton - objekt, kt. sa nachadza v ramci jedneho
   //procesu existuje iba 1 instancia tejto triedy

   //urobime to cez enum

   public RiadokDao getRiadokDao() {
      return getTextFileRiadokDao();
   }

   private MemoryRiadokDao getMemoryRiadokDao() {
      return new MemoryRiadokDao();
   }

   //tu stelujeme s akym suborom budeme robit
   private TextFileRiadokDao getTextFileRiadokDao() {
      File suborSRiadkami = new File("riadky.csv");
      return new TextFileRiadokDao(suborSRiadkami);
   }
}
