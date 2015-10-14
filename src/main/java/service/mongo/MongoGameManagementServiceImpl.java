package service.mongo;

import dao.GameManagementDao;
import dao.mongo.enum_attributes.Attribute;
import domain.*;
import exceptions.NoSuchDataCouplingException;
import exceptions.NoSuchDataException;
import org.springframework.stereotype.Service;
import service.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by koloturka on 04.08.15.
 */
@Service
public class MongoGameManagementServiceImpl implements GameManagementService {

	private GameManagementDao gameManagementDao;

	@Autowired
	public void setGameManagementDao(GameManagementDao gameManagementDao) {
		this.gameManagementDao = gameManagementDao;
	}


	/*
	Stories
	 */
	@Override
	public Story createStory(Story story) {
		gameManagementDao.createStory(story);
		return gameManagementDao.readStory(story.getIdStory());
	}

	@Override
	public Story readStory(int idStory) throws NoSuchDataException {
		return getStoryIfExists(idStory);
	}

	@Override
	public List<Story> readStories() {
		return gameManagementDao.readStories();
	}

	@Override
	public Story updateStory(int idStory, Map<? extends Attribute, Object> updates) {
		gameManagementDao.updateStory(idStory, updates);
		return gameManagementDao.readStory(idStory);
	}

	public Story upsertStory(int idStory, Story story) throws NoSuchDataException{
		gameManagementDao.upsertStory(idStory, story);
		return gameManagementDao.readStory(idStory);
	}


	/*
	Chapters
	 */
	@Override
	public Chapter insertChapter(Chapter chapter) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(chapter.getIdStory());
		checkChapterPagesOnCoupling(chapter.getIdChapter(), chapter.getStartPages());
		gameManagementDao.insertChapter(chapter);
		return gameManagementDao.readChapter(chapter.getIdChapter());
	}

	@Override
	public Chapter readChapter(int idStory, int idChapter) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		return getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
	}

	@Override
	public List<Chapter> readChapters(int idStory) throws NoSuchDataException {
		getStoryIfExists(idStory);
		return gameManagementDao.readChapters(idStory);
	}

	//TODO update chapter control update fields!
	@Override
	public Chapter updateChapter(int idStory, int idChapter, Map<Attribute, Object> updates) throws NoSuchDataException, NoSuchDataCouplingException {
		gameManagementDao.updateChapter(idChapter, updates);
		return gameManagementDao.readChapter(idChapter);
	}

	@Override
	public Chapter upsertChapter(int idStory, int idChapter, Chapter chapter) throws NoSuchDataException, NoSuchDataCouplingException {
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		gameManagementDao.upsertChapter(idChapter, chapter);
		return gameManagementDao.readChapter(idChapter);
	}

	@Override
	public String deleteChapter(int idStory, int idChapter) throws NoSuchDataException, NoSuchDataCouplingException {
		Chapter chapter = getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		gameManagementDao.deleteChapter(idChapter);
		return "Chapter " + chapter.getIdChapter() + " \"" + chapter.getName() + "\" has been successfully deleted.";
	}

	@Override
	public int getChapterIdByPage(int idPage) {
		return gameManagementDao.getChapterIdByPage(idPage);
	}


	/*
	Pages
	 */
	@Override
	public Page insertPage(int idStory, Page page) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, page.getIdChapter());
		gameManagementDao.insertPage(page);
		return gameManagementDao.readPage(page.getIdPage());
	}

	@Override
	public Page readPage(int idStory, int idChapter, int idPage) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		return getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
	}

	@Override
	public List<Page> readPages(int idStory, int idChapter) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		return gameManagementDao.readPages(idChapter);
	}

	//TODO UPDATE control
	@Override
	public Page updatePage(int idStory, int idChapter, int idPage, Map<Attribute, Object> updates) throws NoSuchDataException, NoSuchDataCouplingException {
		gameManagementDao.updatePage(idPage, updates);
		return gameManagementDao.readPage(idPage);
	}

	@Override
	public Page upsertPage(int idStory, int idChapter, int idPage, Page page) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
		gameManagementDao.upsertPage(idPage, page);
		return gameManagementDao.readPage(idPage);
	}

	@Override
	public String deletePage(int idStory, int idChapter, int idPage) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
		gameManagementDao.deletePage(idPage);
		return "Page " + idPage + " has been successfully deleted.";
	}

	/*
	Screens
	 */
	@Override
	public Screen insertScreen(int idStory, int idChapter, int idPage, Screen screen) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
		gameManagementDao.insertScreen(idPage, screen);
		return gameManagementDao.readScreen(idPage, screen.getIdScreen());
	}

	@Override
	public Screen readScreen(int idStory, int idChapter, int idPage, int idScreen) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
		return getScreenIfExists(idPage, idScreen);
	}

	@Override
	public List<Screen> readScreens(int idStory, int idChapter, int idPage) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		return getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage).getScreens();
	}

	//TODO UPDATE control screen
	@Override
	public Screen updateScreen(int idPage, int idScreen, Map<Attribute, Object> updates) {
		gameManagementDao.updateScreen(idPage, idScreen, updates);
		return gameManagementDao.readScreen(idPage, idScreen);
	}

	@Override
	public Screen upsertScreen(int idStory, int idChapter, int idPage, int idScreen, Screen screen) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
		getScreenIfExists(idPage, idScreen);
		gameManagementDao.upsertScreen(idPage, idScreen, screen);
		return gameManagementDao.readScreen(idPage, idScreen);
	}

	@Override
	public String deleteScreen(int idStory, int idChapter, int idPage, int idScreen) throws NoSuchDataException, NoSuchDataCouplingException {
		getStoryIfExists(idStory);
		getChapterIfExistsAndBelongsToCurrentStory(idStory, idChapter);
		getPageIfExistsAndBelongsToCurrentChapter(idChapter, idPage);
		getScreenIfExists(idPage, idScreen);
		gameManagementDao.deleteScreen(idPage, idScreen);
		return "Screen " + idScreen + " has been successfully deleted.";
	}


	/*
	Answers
	 */
	@Override
	public Answer insertAnswer(int idPage, int idScreen, Answer answer) {
		gameManagementDao.insertAnswer(idPage, idScreen, answer);
		return gameManagementDao.readAnswer(idPage, idScreen, answer.getText());
	}

	@Override
	public Answer readAnswer(int idPage, int idScreen, String text) {
		return gameManagementDao.readAnswer(idPage, idScreen, text);
	}

	@Override
	public List<Answer> readAnswers(int idPage, int idScreen) {
		return gameManagementDao.readAnswers(idPage, idScreen);
	}

	@Override
	public String deleteAnswer(int idPage, int idScreen, String text) {
		gameManagementDao.deleteAnswer(idPage, idScreen, text);
		return "Answer '" + text + "' has been successfully deleted.";
	}

	private Story getStoryIfExists(int idStory) {
		Story result = gameManagementDao.readStory(idStory);
		if (result == null) {
			throw new NoSuchDataException(Story.class, idStory);
		}
		return result;
	}

	private Chapter getChapterIfExistsAndBelongsToCurrentStory(int idStory, int idChapter) {
		Chapter chapter = gameManagementDao.readChapter(idChapter);
		if (chapter == null) {
			throw new NoSuchDataException(Chapter.class, idChapter);
		}
		if (chapter.getIdStory() != idStory) {
			throw new NoSuchDataCouplingException(Chapter.class, Story.class, idChapter, idStory);
		}
		return chapter;
	}

	private void checkChapterPagesOnCoupling(int idChapter, List<Integer> startPages) {
		for (Page page : gameManagementDao.readPages(startPages)) {
			if (page.getIdChapter() != idChapter) {
				throw new NoSuchDataCouplingException(Page.class, Chapter.class, page.getIdPage(), idChapter);
			}
		}
	}

	private Page getPageIfExistsAndBelongsToCurrentChapter(int idChapter, int idPage) {
		Page result = gameManagementDao.readPage(idPage);
		if (result == null) {
			throw new NoSuchDataException(Page.class, idPage);
		} else if (result.getIdChapter() != idChapter) {
			throw new NoSuchDataCouplingException(Page.class, Chapter.class, idPage, idChapter);
		}
		return result;
	}

	private Screen getScreenIfExists(int idPage, int idScreen) {
		Screen result = gameManagementDao.readScreen(idPage, idScreen);
		if (result == null) {
			throw new NoSuchDataCouplingException(Screen.class, Page.class, idScreen, idPage);
		}
		return result;
	}

}
