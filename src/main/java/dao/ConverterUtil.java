package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dao.mongo.enum_attributes.Attribute;
import domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by koloturka on 22.07.15.
 */
public interface ConverterUtil {

    DBObject convertStoryToDBObject(Story story);

    Story convertDBObjectToStory(DBObject dbObject);

    DBObject convertChapterToDBObject(Chapter chapter);

    Chapter convertDBObjectToChapter(DBObject dbObject);

    DBObject convertPageToDBObject(Page page);

    Page convertDBObjectToPage(DBObject dbObject);

    DBObject convertScreenToDBObject(Screen screen);

    Screen convertDBObjectToScreen(DBObject dbObject);

    DBObject convertAnswerToDBObject(Answer answer);

    Answer convertDBObjectToAnswer(DBObject dbObject);

    void convertUpdatesToDBObject(BasicDBObject setQuery, Map<? extends Attribute, Object> updates);

    DBObject convertUserToDBObject(User user);

    User convertDBObjectToUser(DBObject object);

    DBObject convertSaveToDBObject(Save save);

    Save convertDBObjectToSave(DBObject resultDBObject);

}
