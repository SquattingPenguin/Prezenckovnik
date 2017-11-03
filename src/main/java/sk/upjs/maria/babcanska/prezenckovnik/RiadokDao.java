package sk.upjs.maria.babcanska.prezenckovnik;

import java.util.List;

public interface RiadokDao {
   List<Riadok> getAll();

   Riadok vymazPodlaIdRiadok(Long id);

   //CREATE
   Riadok pridajRiadok(Riadok riadok) throws DaoException;
}
