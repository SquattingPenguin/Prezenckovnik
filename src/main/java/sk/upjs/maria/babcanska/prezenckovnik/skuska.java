package sk.upjs.maria.babcanska.prezenckovnik;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class skuska {
   private JList list1;
   private JTextField osobaTextField;
   private JButton pridatOsobuButton;
   private JPanel Prezenckovnik;

   private RiadokDao riadokDao;

   public static void main(final String[] args) {
      JFrame frame = new JFrame("skuska");
      frame.setContentPane(new skuska().Prezenckovnik);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }

   //   private void createUIComponents() {
   //      // TODO: place custom component creation code here
   //   }
   //

   public skuska() {
      riadokDao = RiadokDaoFactory.INSTANCE.getRiadokDao();
      List<Riadok> riadky = riadokDao.getAll();
      String[] poleRiadkov = new String[riadky.size()];
      for (int i = 0; i < riadky.size(); i++) {
         poleRiadkov[i] = riadky.get(i).toString();
      }
      list1.setListData(poleRiadkov);
   }

   private void createUIComponents() {

      pridatOsobuButton = new JButton();
      pridatOsobuButton.addActionListener(new AbstractAction() {
         @Override
         public void actionPerformed(final ActionEvent e) {
            Riadok riadok = new Riadok();
            String osoba = osobaTextField.getText();
            riadok.setOsoba(osoba);
            riadokDao.pridajRiadok(riadok);
         }
      });
   }

   //      public vratFormatovanyDatum(){
   //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");
   //         return datum.format();
   //      }
}
