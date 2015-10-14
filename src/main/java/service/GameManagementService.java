package service;

import dao.mongo.enum_attributes.Attribute;
import domain.*;
import exceptions.NoSuchDataCouplingException;
import exceptions.NoSuchDataException;

import java.util.List;
import java.util.Map;

/**
 * @author Koloturka
 * @version 25.08.2015 17:11
 */
public interface GameManagementService {
	/*
		Stories
	*/
	Story createStory(Story story);

	Story readStory(int idStory) throws NoSuchDataException;

	List<Story> readStories();

	Story updateStory(int idStory, Map<? extends Attribute, Object> updates);

	Story upsertStory(int idStory, Story story) throws NoSuchDataException;

	/*
		Chapters
	*/
	Chapter insertChapter(Chapter chapter) throws NoSuchDataException, NoSuchDataCouplingException;

	Chapter readChapter(int idStory, int idChapter) throws NoSuchDataException, NoSuchDataCouplingException;

	List<Chapter> readChapters(int idStory) throws NoSuchDataException;

	//TODO update chapter control update fields!
	Chapter updateChapter(int idStory, int idChapter, Map<Attribute, Object> updates) throws NoSuchDataException, NoSuchDataCouplingException;

	Chapter upsertChapter(int idStory, int idChapter, Chapter chapter) throws NoSuchDataException, NoSuchDataCouplingException;

	String deleteChapter(int idStory, int idChapter) throws NoSuchDataException, NoSuchDataCouplingException;

	int getChapterIdByPage(int idPage);


	/*
		Pages
	*/
	Page insertPage(int idStory, Page page) throws NoSuchDataException, NoSuchDataCouplingException;

	Page readPage(int idStory, int idChapter, int idPage) throws NoSuchDataException, NoSuchDataCouplingException;

	List<Page> readPages(int idStory, int idChapter) throws NoSuchDataException, NoSuchDataCouplingException;

	//TODO UPDATE control
	Page updatePage(int idStory, int idChapter, int idPage, Map<Attribute, Object> updates) throws NoSuchDataException, NoSuchDataCouplingException;

	Page upsertPage(int idStory, int idChapter, int idPage, Page page) throws NoSuchDataException, NoSuchDataCouplingException;

	String deletePage(int idStory, int idChapter, int idPage) throws NoSuchDataException, NoSuchDataCouplingException;

	/*
		Screens
	*/
	Screen insertScreen(int idStory, int idChapter, int idPage, Screen screen) throws NoSuchDataException, NoSuchDataCouplingException;

	Screen readScreen(int idStory, int idChapter, int idPage, int idScreen) throws NoSuchDataException, NoSuchDataCouplingException;

	List<Screen> readScreens(int idStory, int idChapter, int idPage) throws NoSuchDataException, NoSuchDataCouplingException;

	Screen updateScreen(int idPage, int idScreen, Map<Attribute, Object> updates);

	Screen upsertScreen(int idStory, int idChapter, int idPage, int idScreen, Screen screen) throws NoSuchDataException, NoSuchDataCouplingException;

	String deleteScreen(int idStory, int idChapter, int idPage, int idScreen) throws NoSuchDataException, NoSuchDataCouplingException;

	/*
		Answers
	*/
	Answer insertAnswer(int idPage, int idScreen, Answer answer);

	Answer readAnswer(int idPage, int idScreen, String text);

	List<Answer> readAnswers(int idPage, int idScreen);

	String deleteAnswer(int idPage, int idScreen, String text);

}
