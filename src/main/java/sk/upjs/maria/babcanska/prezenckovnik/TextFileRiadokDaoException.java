package sk.upjs.maria.babcanska.prezenckovnik;

public class TextFileRiadokDaoException extends DaoException {

   //RuntimeException nemusis chytat, ani davat do throws

   public TextFileRiadokDaoException(final String message) {
      super(message);
   }

   public TextFileRiadokDaoException(final String message, final Throwable cause) {
      super(message, cause);
   }
}
