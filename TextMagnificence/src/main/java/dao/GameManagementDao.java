package dao;

import dao.db_attributes.*;
import entity.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by koloturka on 22.07.15.
 */
public interface GameManagementDao {

    int createStory(Story story);

    int updateStory(int idStory, HashMap<Attribute, String> updates);

    int insertChapter(int storyId, Chapter chapter);

    int updateChapter(int idStory, int idChapter, HashMap<Attribute, String> updates);

    int deleteChapter(int storyId, int idChapter);

    int insertPage(Page page);

    int updatePage(int idPage, HashMap<Attribute, String> updates);

    int deletePage(int idPage);

    int insertScreen(int idPage, Screen screen);

    int updateScreen(int idPage, int idScreen, HashMap<Attribute, String> updates);

    int deleteScreen(int idPage, int idScreen);

    int insertAnswer(int idPage, int idScreen, Answer answer);

    int deleteAnswer(int idPage, int idScreen, String text);

    ArrayList<Story> readStories();

    ArrayList<Chapter> readChapters(int idStory);

    ArrayList<Page> readPages(int idStory);

    ArrayList<Screen> readScreens(int idPage);
}
