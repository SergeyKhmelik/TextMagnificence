package service;

public interface SaveService {

    int getLastSave(int idStory, String username);

    void upsertSave(int idStory, String username, int idPage);

    void deleteSave(int idStory, String username);

    boolean isViewablePage(int idStory, String username, int idPage);

}
