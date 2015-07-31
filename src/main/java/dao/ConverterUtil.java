package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dao.db_attributes.Attribute;
import entity.*;

import java.util.HashMap;

/**
 * Created by koloturka on 22.07.15.
 */
public interface ConverterUtil {
    DBObject convertStoryToDBObject(Story story);

    Story convertDBObjectToStory(DBObject dbObject);

    DBObject convertChapterToDBObject(Chapter chapter);

    Chapter convertDBObjectToChapter(DBObject dbObject);

    DBObject convertPageToDBObject(Page page);

    Page convertDBObjectToSPage(DBObject dbObject);

    DBObject convertScreenToDBObject(Screen screen);

    Screen convertDBObjectToScreen(DBObject dbObject);

    DBObject convertAnswerToDBObject(Answer answer);

    Answer convertDBObjectToAnswer(DBObject dbObject);

    void convertUpdatesToDBObject(BasicDBObject setQuery, HashMap<Attribute, String> updates);
}
