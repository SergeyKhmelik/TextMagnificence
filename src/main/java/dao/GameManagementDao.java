package dao;

import dao.mongo.enum_attributes.Attribute;
import domain.*;
import exceptions.NoSuchDataException;

import java.util.List;
import java.util.Map;

/**
 * @author Koloturka
 * @version 25.08.2015 17:16
 */
public interface GameManagementDao {

	int createStory(Story story);

	Story readStory(int idStory);

	List<Story> readStories();

	int updateStory(int idStory, Map<? extends Attribute, Object> updates);

	int upsertStory(int idStory, Story story);

	int insertChapter(Chapter chapter);

	Chapter readChapter(int idChapter) throws NoSuchDataException;

	List<Chapter> readChapters(int idStory);

	int updateChapter(int idChapter, Map<Attribute, Object> updates);

	int upsertChapter(int idChapter, Chapter chapter);

	int deleteChapter(int idChapter);

	int getChapterIdByPage(int idPage);

	int insertPage(Page page);

	Page readPage(int idPage) throws NoSuchDataException;

	List<Page> readPages(List<Integer> pagesList);

	List<Page> readPages(int idChapter);

	int updatePage(int idPage, Map<Attribute, Object> updates);

	int upsertPage(int idPage, Page page);

	int deletePage(int idPage);

	int insertScreen(int idPage, Screen screen);

	Screen readScreen(int idPage, int idScreen) throws NoSuchDataException;

	List<Screen> readScreens(int idPage);

	int updateScreen(int idPage, int idScreen, Map<Attribute, Object> updates);

	int upsertScreen(int idPage, int idScreen, Screen screen);

	int deleteScreen(int idPage, int idScreen);

	int insertAnswer(int idPage, int idScreen, Answer answer);

	Answer readAnswer(int idPage, int idScreen, String text);

	List<Answer> readAnswers(int idPage, int idScreen);

	int deleteAnswer(int idPage, int idScreen, String text);

}
