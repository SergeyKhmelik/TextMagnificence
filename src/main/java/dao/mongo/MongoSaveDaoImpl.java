package dao.mongo;

import com.mongodb.*;
import dao.ConverterUtil;
import dao.SaveDao;
import dao.mongo.enum_attributes.SaveAttr;
import domain.Save;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koloturka on 28.09.2015.
 */
@Component
public class MongoSaveDaoImpl implements SaveDao {

    public static final String DB_NAME = "TextMagnificence";
    public static final String SAVES_COLLECTION = "saves";
    private ConverterUtil converterUtil;
    private DB textGameDB;
    private DBCollection saves;

    @Autowired
    public void setConverterUtil(ConverterUtil converterUtil) {
        this.converterUtil = converterUtil;
    }

    @Autowired
    public void setMongoClient(Mongo mongoClient) {
        this.textGameDB = mongoClient.getDB(DB_NAME);
        this.saves = textGameDB.getCollection(SAVES_COLLECTION);
    }

    @Override
    public void createSave(int idStory, String username, int startPage) {
        Save save = new Save();
        save.setIdStory(idStory);
        save.setUsername(username);
        List<Integer> pages = new ArrayList<Integer>();
        pages.add(startPage);
        save.setPages(pages);
        saves.insert(converterUtil.convertSaveToDBObject(save));
    }

    @Override
    public Save readSave(int idStory, String username) {
        Save result = null;
        DBObject query = new BasicDBObject()
                .append(SaveAttr.ID_STORY.getName(), idStory)
                .append(SaveAttr.USERNAME.getName(), username);
        DBCursor cursor = saves.find(query);
        if(cursor.hasNext()) {
            DBObject resultDBObject = cursor.next();
            result = converterUtil.convertDBObjectToSave(resultDBObject);
        }
        return result;
    }

    @Override
    public void updateSave(int idStory, String username, int idPage) {
        DBObject findQuery = new BasicDBObject()
                .append(SaveAttr.ID_STORY.getName(), idStory)
                .append(SaveAttr.USERNAME.getName(), username);
        DBObject updateQuery = new BasicDBObject()
                .append("$push", new BasicDBObject(SaveAttr.PAGES.getName(), idPage));
        saves.update(findQuery, updateQuery);
    }

    @Override
    public void deleteSave(int idStory, String username) {
        DBObject findQuery = new BasicDBObject()
                .append(SaveAttr.ID_STORY.getName(), idStory)
                .append(SaveAttr.USERNAME.getName(), username);
        saves.remove(findQuery);
    }

}
