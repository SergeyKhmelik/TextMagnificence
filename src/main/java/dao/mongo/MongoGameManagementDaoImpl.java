package dao.mongo;

import com.mongodb.*;
import dao.ConverterUtil;
import dao.GameManagementDao;
import dao.mongo.enum_attributes.*;
import domain.*;
import exceptions.NoSuchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by koloturka on 20.07.15.
 */

@Component
public class MongoGameManagementDaoImpl implements GameManagementDao {

	public static final String DB_NAME = "TextMagnificence";
	public static final String STORIES_COLLECTION = "stories";
	public static final String CHAPTERS_COLLECTION = "chapters";
	public static final String PAGES_COLLECTION = "pages";

	private ConverterUtil converterUtil;
	private DB textGameDB;
	private DBCollection stories;
	private DBCollection chapters;
	private DBCollection pages;

	@Autowired
	public void setConverterUtil(ConverterUtil converterUtil) {
		this.converterUtil = converterUtil;
	}

	@Autowired
	public void setMongoClient(Mongo mongoClient) {
		this.textGameDB = mongoClient.getDB(DB_NAME);
		this.stories = textGameDB.getCollection(STORIES_COLLECTION);
		this.chapters = textGameDB.getCollection(CHAPTERS_COLLECTION);
		this.pages = textGameDB.getCollection(PAGES_COLLECTION);
	}


	/*
	 *	STORIES
	 */

	@Override
	public int createStory(Story story) {
		DBObject storyToInsert = converterUtil.convertStoryToDBObject(story);
		stories.insert(storyToInsert);
		return story.getIdStory();
	}

	@Override
	public Story readStory(int idStory){
		Story result = null;
		BasicDBObject query = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory);
		DBCursor cursor = stories.find(query);
		if (cursor.hasNext()) {
			DBObject resultDBObject = cursor.next();
			result = converterUtil.convertDBObjectToStory(resultDBObject);
		}
		return result;
	}

	@Override
	public List<Story> readStories() {
		List<Story> stories = new ArrayList<Story>();
		DBCursor cursor = this.stories.find();
		while (cursor.hasNext()) {
			Story currentStory = converterUtil.convertDBObjectToStory(cursor.next());
			stories.add(currentStory);
		}
		return stories;
	}

	@Override
	public int updateStory(int idStory, Map<? extends Attribute, Object> updates) {
		DBObject findQuery = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory);
		BasicDBObject setQuery = new BasicDBObject();
		converterUtil.convertUpdatesToDBObject(setQuery, updates);
		DBObject updateQuery = new BasicDBObject("$set", setQuery);
		if (!stories.update(findQuery, updateQuery, true, false).isUpdateOfExisting()) {
			throw new NoSuchDataException(Story.class, idStory);
		}
		return idStory;
	}

	@Override
	public int upsertStory(int idStory, Story story) {
		DBObject findQuery = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory);
		if (!stories.update(findQuery, converterUtil.convertStoryToDBObject(story), true, false).isUpdateOfExisting()){
			throw new NoSuchDataException(Story.class, idStory);
		}
		return  idStory;
	}


	/*
	 * CHAPTERS
	 */

	@Override
	public int insertChapter(Chapter chapter) {
		chapters.insert(converterUtil.convertChapterToDBObject(chapter));
		return chapter.getIdChapter();
	}

	@Override
	public Chapter readChapter(int idChapter) throws NoSuchDataException {
		Chapter result = null;
		DBObject findQuery = new BasicDBObject(ChapterAttr.ID_CHAPTER.getName(), idChapter);
		DBCursor cursor = chapters.find(findQuery);
		if (cursor.hasNext()) {
			DBObject currentObject = cursor.next();
			result = converterUtil.convertDBObjectToChapter(currentObject);
		}
		return result;
	}

	@Override
	public List<Chapter> readChapters(int idStory) {
		List<Chapter> result = new ArrayList<Chapter>();
		DBObject findQuery = new BasicDBObject(ChapterAttr.ID_STORY.getName(), idStory);
		DBCursor cursor = chapters.find(findQuery);
		while (cursor.hasNext()) {
			Chapter currentChapter = converterUtil.convertDBObjectToChapter(cursor.next());
			result.add(currentChapter);
		}
		return result;
	}

	@Override
	public int updateChapter(int idChapter, Map<Attribute, Object> updates) {
		DBObject findQuery = new BasicDBObject(ChapterAttr.ID_CHAPTER.getName(), idChapter);
		BasicDBObject setQuery = new BasicDBObject();
		converterUtil.convertUpdatesToDBObject(setQuery, updates);
		DBObject updateQuery = new BasicDBObject("$set", setQuery);
		if (!chapters.update(findQuery, updateQuery, true, false).isUpdateOfExisting()) {
			throw new NoSuchDataException(Chapter.class, idChapter);
		}
		return idChapter;
	}

	@Override
	public int upsertChapter(int idChapter, Chapter chapter) {
		DBObject findQuery = new BasicDBObject(ChapterAttr.ID_CHAPTER.getName(), idChapter);
		if (!chapters.update(findQuery, converterUtil.convertChapterToDBObject(chapter), true, false).isUpdateOfExisting()) {
			throw new NoSuchDataException(Chapter.class, idChapter);
		}
		return idChapter;
	}

	@Override
	public int deleteChapter(int idChapter) {
		DBObject findQuery = new BasicDBObject(ChapterAttr.ID_CHAPTER.getName(), idChapter);
		chapters.remove(findQuery);
		return idChapter;
	}

	@Override
	public int getChapterIdByPage(int idPage) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		DBObject projectQuery = new BasicDBObject(PageAttr.ID_CHAPTER.getName(), 1);
		DBCursor cursor = pages.find(findQuery, projectQuery);
		if(!cursor.hasNext()){
			throw new NoSuchDataException(Page.class, idPage);
		}
		DBObject result = cursor.next();
		return (Integer) result.get(PageAttr.ID_CHAPTER.getName());
	}


	/*
	 * PAGES
	 */

	@Override
	public int insertPage(Page page) {
		pages.insert(converterUtil.convertPageToDBObject(page));
		return page.getIdPage();
	}

	@Override
	public Page readPage(int idPage) throws NoSuchDataException {
		Page result;
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		DBCursor cursor = pages.find(findQuery);
		if (cursor.hasNext()) {
			DBObject currentObject = cursor.next();
			result = converterUtil.convertDBObjectToPage(currentObject);
		} else {
			throw new NoSuchDataException(Page.class, idPage);
		}
		return result;
	}

	@Override
	public List<Page> readPages(List<Integer> pagesList) {
		List<Page> result = new ArrayList<Page>();
		DBObject inQuery = new BasicDBObject("$in", pagesList);
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), inQuery);
		DBCursor cursor = pages.find(findQuery);
		while (cursor.hasNext()) {
			Page currentPage = converterUtil.convertDBObjectToPage(cursor.next());
			result.add(currentPage);
		}
		return result;
	}

	@Override
	public List<Page> readPages(int idChapter) {
		List<Page> result = new ArrayList<Page>();
		DBObject findQuery = new BasicDBObject(PageAttr.ID_CHAPTER.getName(), idChapter);
		DBCursor cursor = pages.find(findQuery);
		while (cursor.hasNext()) {
			Page currentPage = converterUtil.convertDBObjectToPage(cursor.next());
			result.add(currentPage);
		}
		return result;
	}

	@Override
	public int updatePage(int idPage, Map<Attribute, Object> updates) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		BasicDBObject setQuery = new BasicDBObject();
		converterUtil.convertUpdatesToDBObject(setQuery, updates);
		DBObject updateQuery = new BasicDBObject("$set", setQuery);

		if (!pages.update(findQuery, updateQuery).isUpdateOfExisting()) {
			throw new NoSuchDataException(Page.class, idPage);
		}
		return idPage;
	}

	@Override
	public int upsertPage(int idPage, Page page) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		if(!pages.update(findQuery, converterUtil.convertPageToDBObject(page), true, false).isUpdateOfExisting()){
			throw new NoSuchDataException(Page.class, idPage);
		}
		return idPage;
	}

	@Override
	public int deletePage(int idPage) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		pages.remove(findQuery);
		return idPage;
	}


	/*
	 * SCREENS
	 */

	@Override
	public int insertScreen(int idPage, Screen screen) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		DBObject listItem = new BasicDBObject(PageAttr.SCREENS.getName(), converterUtil.convertScreenToDBObject(screen));
		DBObject insertQuery = new BasicDBObject("$push", listItem);
		if(!pages.update(findQuery, insertQuery).isUpdateOfExisting()){
			throw new NoSuchDataException(Page.class, idPage);
		}
		return screen.getIdScreen();
	}

	@Override
	public Screen readScreen(int idPage, int idScreen) throws NoSuchDataException {
		Screen screen;

		List<DBObject> pipeline = new ArrayList<DBObject>();
		BasicDBObject pageMatch = new BasicDBObject("$match", new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage));
		BasicDBObject unwind = new BasicDBObject("$unwind", '$' + PageAttr.SCREENS.getName());
		BasicDBObject screenMatch = new BasicDBObject("$match",
				new BasicDBObject(PageAttr.SCREENS.getName() + '.' + ScreenAttr.ID_SCREEN.getName(), idScreen));
		BasicDBObject project = new BasicDBObject("$project",
				new BasicDBObject(PageAttr.SCREENS.getName(), 1).append("_id", 0));

		pipeline.add(pageMatch);
		pipeline.add(unwind);
		pipeline.add(screenMatch);
		pipeline.add(project);

		AggregationOutput output = pages.aggregate(pipeline);
		Iterator<DBObject> aggregationIterator = output.results().iterator();
		if (aggregationIterator.hasNext()) {
			DBObject screenDBObject = (DBObject) aggregationIterator.next().get(PageAttr.SCREENS.getName());
			screen = converterUtil.convertDBObjectToScreen(screenDBObject);
		} else {
			throw new NoSuchDataException(Screen.class, idScreen);
		}
		return screen;
	}

	@Override
	public List<Screen> readScreens(int idPage) {
		List<Screen> screens = new ArrayList<Screen>();

		List<DBObject> pipeline = new ArrayList<DBObject>();
		BasicDBObject match = new BasicDBObject("$match", new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage));
		BasicDBObject unwind = new BasicDBObject("$unwind", '$' + PageAttr.SCREENS.getName());
		BasicDBObject project = new BasicDBObject("$project",
				new BasicDBObject(PageAttr.SCREENS.getName(), 1).append("_id", 0));
		pipeline.add(match);
		pipeline.add(unwind);
		pipeline.add(project);

		AggregationOutput output = pages.aggregate(pipeline);

		for (final DBObject result : output.results()) {
			screens.add(converterUtil.convertDBObjectToScreen((DBObject) result.get(PageAttr.SCREENS.getName())));
		}

		return screens;
	}

	@Override
	public int updateScreen(int idPage, int idScreen, Map<Attribute, Object> updates) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
				.append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
		BasicDBObject setQuery = new BasicDBObject();
		converterUtil.convertUpdatesToDBObject(setQuery, updates);
		DBObject updateQuery = new BasicDBObject("$set", setQuery);
		if(!pages.update(findQuery, updateQuery).isUpdateOfExisting()){
			throw new NoSuchDataException(Screen.class, idScreen);
		}
		return idScreen;
	}

	@Override
	public int upsertScreen(int idPage, int idScreen, Screen screen) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
				.append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
		DBObject updateQuery = new BasicDBObject("$set",
				new BasicDBObject(PageAttr.SCREENS.getName() + ".$", converterUtil.convertScreenToDBObject(screen)));
		if(!pages.update(findQuery, updateQuery).isUpdateOfExisting()){
			throw new NoSuchDataException(Screen.class, idScreen);
		}
		return idScreen;
	}

	@Override
	public int deleteScreen(int idPage, int idScreen) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
		DBObject updateQuery = new BasicDBObject("$pull", new BasicDBObject(PageAttr.SCREENS.getName(),
				new BasicDBObject(ScreenAttr.ID_SCREEN.getName(), idScreen)));
		if(!pages.update(findQuery, updateQuery).isUpdateOfExisting()){
			throw new NoSuchDataException(Screen.class, idScreen);
		}
		return idScreen;
	}


	/*
	 * ANSWERS
	 */

	@Override
	public int insertAnswer(int idPage, int idScreen, Answer answer) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
				.append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
		DBObject listItem = new BasicDBObject(
				PageAttr.SCREENS.getName() + ".$." + ScreenAttr.ANSWERS.getName(),
				converterUtil.convertAnswerToDBObject(answer));
		DBObject insertQuery = new BasicDBObject("$push", listItem);

		if(!pages.update(findQuery, insertQuery).isUpdateOfExisting()){
			throw new NoSuchDataException(Screen.class, idScreen);
		}
		return idScreen;
	}

	@Override
	public Answer readAnswer(int idPage, int idScreen, String text) {
		Answer answer = new Answer();

		List<DBObject> pipeline = new ArrayList<DBObject>();

		BasicDBObject pageMatch = new BasicDBObject("$match", new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage));
		BasicDBObject screensUnwind = new BasicDBObject("$unwind", '$' + PageAttr.SCREENS.getName());
		BasicDBObject screenMatch = new BasicDBObject("$match",
				new BasicDBObject(PageAttr.SCREENS.getName() + '.' + ScreenAttr.ID_SCREEN.getName(), idScreen));
		BasicDBObject answersUnwind =
				new BasicDBObject("$unwind", '$' + PageAttr.SCREENS.getName() + '.' + ScreenAttr.ANSWERS.getName());
		BasicDBObject answerMatch = new BasicDBObject("$match", new BasicDBObject(
				PageAttr.SCREENS.getName() + '.' + ScreenAttr.ANSWERS.getName() + '.' + AnswerAttr.TEXT.getName(), text));
		BasicDBObject project = new BasicDBObject("$project",
				new BasicDBObject(PageAttr.SCREENS.getName() + '.' + ScreenAttr.ANSWERS.getName(), 1).append("_id", 0));

		pipeline.add(pageMatch);
		pipeline.add(screensUnwind);
		pipeline.add(screenMatch);
		pipeline.add(answersUnwind);
		pipeline.add(answerMatch);
		pipeline.add(project);

		AggregationOutput output = pages.aggregate(pipeline);
		if (output.results().iterator().hasNext()) {
			DBObject currentResultObject = (DBObject) output.results().iterator().next().get(PageAttr.SCREENS.getName());
			DBObject currentAnswerObject = (DBObject) currentResultObject.get(ScreenAttr.ANSWERS.getName());
			answer = converterUtil.convertDBObjectToAnswer(currentAnswerObject);
		}
		return answer;
	}

	@Override
	public List<Answer> readAnswers(int idPage, int idScreen) {
		List<Answer> answers = new ArrayList<Answer>();
		List<DBObject> pipeline = new ArrayList<DBObject>();

		BasicDBObject pageMatch = new BasicDBObject("$match", new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage));
		BasicDBObject screensUnwind = new BasicDBObject("$unwind", '$' + PageAttr.SCREENS.getName());
		BasicDBObject screenMatch = new BasicDBObject("$match",
				new BasicDBObject(PageAttr.SCREENS.getName() + '.' + ScreenAttr.ID_SCREEN.getName(), idScreen));
		BasicDBObject answersUnwind =
				new BasicDBObject("$unwind", '$' + PageAttr.SCREENS.getName() + '.' + ScreenAttr.ANSWERS.getName());
		BasicDBObject project = new BasicDBObject("$project",
				new BasicDBObject(PageAttr.SCREENS.getName() + '.' + ScreenAttr.ANSWERS.getName(), 1).append("_id", 0));
		pipeline.add(pageMatch);
		pipeline.add(screensUnwind);
		pipeline.add(screenMatch);
		pipeline.add(answersUnwind);
		pipeline.add(project);

		AggregationOutput output = pages.aggregate(pipeline);

		for (final DBObject result : output.results()) {
			DBObject currentResultObject = (DBObject) result.get(PageAttr.SCREENS.getName());
			DBObject currentAnswerObject = (DBObject) currentResultObject.get(ScreenAttr.ANSWERS.getName());
			Answer currentAnswer = converterUtil.convertDBObjectToAnswer(currentAnswerObject);
			answers.add(currentAnswer);
		}

		return answers;
	}

	@Override
	public int deleteAnswer(int idPage, int idScreen, String text) {
		DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
				.append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
		DBObject listItem = new BasicDBObject(PageAttr.SCREENS.getName() + ".$." + ScreenAttr.ANSWERS.getName(),
				new BasicDBObject(AnswerAttr.TEXT.getName(), text));
		DBObject deleteQuery = new BasicDBObject("$pull", listItem);

		pages.update(findQuery, deleteQuery);
		return idScreen;
	}

}
