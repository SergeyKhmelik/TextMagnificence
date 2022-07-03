package dao;

import domain.Save;

/**
 * Created by koloturka on 28.09.2015.
 */
public interface SaveDao {

    void createSave(int idStory, String username, int startPage);

    Save readSave(int idStory, String username);

    void updateSave(int idStory, String username, int idPage);

    void deleteSave(int idStory, String username);

}
