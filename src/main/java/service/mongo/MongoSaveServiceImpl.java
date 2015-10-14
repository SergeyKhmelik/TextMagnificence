package service.mongo;

import dao.GameManagementDao;
import dao.SaveDao;
import domain.Save;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SaveService;

/**
 * Created by koloturka on 28.09.2015.
 */

@Service
public class MongoSaveServiceImpl implements SaveService {

    @Autowired
    private SaveDao saveDao;

    @Autowired
    private GameManagementDao gameManagementDao;

    @Override
    public int getLastSave(int idStory, String username) {
        Save save = saveDao.readSave(idStory, username);
        if(save == null){
            return gameManagementDao.readStory(idStory).getStartPage();
        }
        return save.getPages().get(save.getPages().size() - 1);
    }

    @Override
    public void upsertSave(int idStory, String username, int idPage) {
        if(saveDao.readSave(idStory, username) == null){
            saveDao.createSave(idStory, username, idPage);
        } else {
            saveDao.updateSave(idStory, username, idPage);
        }
    }

    @Override
    public void deleteSave(int idStory, String username) {
        saveDao.deleteSave(idStory, username);
    }

    @Override
    public boolean isViewablePage(int idStory, String username, int idPage) {
        Save save = saveDao.readSave(idStory, username);
        if(save == null){
            return false;
        }
        return save.getPages().contains(idPage);
    }

}
