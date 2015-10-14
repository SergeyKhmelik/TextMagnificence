package service;

import domain.Save;
import org.springframework.stereotype.Service;

/**
 * Created by koloturka on 28.09.2015.
 */
public interface SaveService {

    int getLastSave(int idStory, String username);

    void upsertSave(int idStory, String username, int idPage);

    void deleteSave(int idStory, String username);

    boolean isViewablePage(int idStory, String username, int idPage);

}
