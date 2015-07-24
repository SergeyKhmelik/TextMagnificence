package dao.mongo;

import com.mongodb.*;
import dao.ConverterUtil;
import dao.GameManagementDao;
import dao.db_attributes.*;
import entity.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by koloturka on 20.07.15.
 */
public class MongoGameManagementDaoImpl implements GameManagementDao {

    private ConverterUtil converterUtil;
    private DB textGameDB;
    private DBCollection stories;
    private DBCollection pages;

    public MongoGameManagementDaoImpl(MongoClient mongoClient, ConverterUtil mongoConverterUtil) {
        converterUtil = mongoConverterUtil;
        textGameDB = mongoClient.getDB("local");
        stories = textGameDB.getCollection("stories");
        pages = textGameDB.getCollection("pages");
    }

    public int createStory(Story story) {
        DBObject storyToInsert = converterUtil.convertStoryToDBObject(story);
        stories.insert(storyToInsert);
        return story.getId();
    }

    public int updateStory(int idStory, HashMap<Attribute, String> updates) {
        DBObject findQuery = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory);
        BasicDBObject setQuery = new BasicDBObject();
        converterUtil.convertUpdatesToDBObject(setQuery, updates);
        DBObject updateQuery = new BasicDBObject("$set", setQuery);

        stories.update(findQuery, updateQuery, true, false);
        return idStory;
    }

    public int insertChapter(int idStory, Chapter chapter) {
        DBObject findQuery = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory);
        DBObject listItem = new BasicDBObject(StoryAttr.CHAPTERS.getName(), converterUtil.convertChapterToDBObject(chapter));
        DBObject insertQuery = new BasicDBObject("$push", listItem);

        stories.update(findQuery, insertQuery);
        return chapter.getId();
    }

    public int updateChapter(int idStory, int idChapter, HashMap<Attribute, String> updates) {
        DBObject findQuery = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory).append(
                StoryAttr.CHAPTERS.getName() + "." + ChapterAttr.ID_CHAPTER.getName(), idChapter);
        BasicDBObject setQuery = new BasicDBObject();
        converterUtil.convertUpdatesToDBObject(setQuery, updates);
        DBObject updateQuery = new BasicDBObject("$set", setQuery);

        stories.update(findQuery, updateQuery);
        return idChapter;
    }

    public int deleteChapter(int idStory, int idChapter) {
        DBObject findQuery = new BasicDBObject(StoryAttr.ID_STORY.getName(), idStory);
        DBObject updateQuery = new BasicDBObject("$pop", new BasicDBObject(
                StoryAttr.CHAPTERS.getName() + "." + ChapterAttr.ID_CHAPTER.getName(), idChapter));
        stories.update(findQuery, updateQuery);
        return idChapter;
    }

    public int insertPage(Page page) {
        pages.insert(converterUtil.convertPageToDBObject(page));
        return page.getId();
    }

    public int updatePage(int idPage, HashMap<Attribute, String> updates) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
        BasicDBObject setQuery = new BasicDBObject();
        converterUtil.convertUpdatesToDBObject(setQuery, updates);
        DBObject updateQuery = new BasicDBObject("$set", setQuery);

        pages.update(findQuery, updateQuery);
        return idPage;
    }

    public int deletePage(int idPage) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);

        pages.remove(findQuery);
        return idPage;
    }

    public int insertScreen(int idPage, Screen screen) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
        DBObject listItem = new BasicDBObject(PageAttr.SCREENS.getName(), converterUtil.convertScreenToDBObject(screen));
        DBObject insertQuery = new BasicDBObject("$push", listItem);

        pages.update(findQuery, insertQuery);
        return screen.getId();
    }

    public int updateScreen(int idPage, int idScreen, HashMap<Attribute, String> updates) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
                .append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
        BasicDBObject setQuery = new BasicDBObject();
        converterUtil.convertUpdatesToDBObject(setQuery, updates);
        DBObject updateQuery = new BasicDBObject("$set", setQuery);

        pages.update(findQuery, updateQuery);
        return idScreen;

    }

    public int deleteScreen(int idPage, int idScreen) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage);
        DBObject updateQuery = new BasicDBObject("$pop", new BasicDBObject(
                PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen));
        pages.update(findQuery, updateQuery);
        return idScreen;
    }

    public int insertAnswer(int idPage, int idScreen, Answer answer) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
                .append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
        DBObject listItem = new BasicDBObject(
                PageAttr.SCREENS.getName() + ".$." + ScreenAttr.ANSWERS.getName(),
                converterUtil.convertAnswerToDBObject(answer));
        DBObject insertQuery = new BasicDBObject("$push", listItem);

        pages.update(findQuery, insertQuery);
        return idScreen;
    }

    public int deleteAnswer(int idPage, int idScreen, String text) {
        DBObject findQuery = new BasicDBObject(PageAttr.ID_PAGE.getName(), idPage)
                .append(PageAttr.SCREENS.getName() + "." + ScreenAttr.ID_SCREEN.getName(), idScreen);
        DBObject listItem = new BasicDBObject(
                PageAttr.SCREENS.getName() + ".$." + ScreenAttr.ANSWERS.getName(),
                new BasicDBObject(AnswerAttr.TEXT.getName(), text));
        DBObject insertQuery = new BasicDBObject("$pop", listItem);

        pages.update(findQuery, insertQuery);
        return idScreen;
    }

    public ArrayList<Story> readStories() {
        ArrayList<Story> stories = new ArrayList<Story>();
        DBCursor cursor = this.stories.find();
        while(cursor.hasNext()){
            DBObject currentObject = cursor.next();
            Story currentStory = converterUtil.convertDBObjectToStory(currentObject);
            stories.add(currentStory);
        }
        return stories;
    }

    //TODO DUDU
    public ArrayList<Chapter> readChapters(int idStory) {
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        DBCursor cursor = stories.find(new BasicDBObject(StoryAttr.CHAPTERS.getName() + ".$", ""));
        return null;
    }

    public ArrayList<Page> readPages(int idStory) {
        ArrayList<Page> result = new ArrayList<Page>();
        DBCursor cursor = pages.find();
        while(cursor.hasNext()){
            DBObject currentObject = cursor.next();
            Page currentPage = converterUtil.convertDBObjectToSPage(currentObject);
            result.add(currentPage);
        }
        return result;
    }

    //TODO AWEWQ
    public ArrayList<Screen> readScreens(int idPage) {
        return null;
    }
}
