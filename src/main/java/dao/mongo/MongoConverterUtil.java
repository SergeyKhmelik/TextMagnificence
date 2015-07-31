package dao.mongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import dao.ConverterUtil;
import dao.db_attributes.*;
import entity.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by koloturka on 20.07.15.
 */

@Component
public class MongoConverterUtil implements ConverterUtil {

    public DBObject convertStoryToDBObject(Story story) {
        BasicDBList genreList = new BasicDBList();
        for (String genre : story.getGenre()) {
            genreList.add(new BasicDBObject(StoryAttr.GENRE.getName(), genre));
        }

        BasicDBList chapterList = new BasicDBList();
        for (Chapter chapter : story.getChapters()) {
            chapterList.add(convertChapterToDBObject(chapter));
        }

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append(StoryAttr.ID_STORY.getName(), story.getId())
                .append(StoryAttr.NAME.getName(), story.getName())
                .append(StoryAttr.GENRE.getName(), genreList)
                .append(StoryAttr.ANNOTATION.getName(), story.getAnnotation())
                .append(StoryAttr.IMAGE.getName(), story.getImage())
                .append(StoryAttr.CHAPTERS.getName(), chapterList);

        return builder.get();
    }

    public Story convertDBObjectToStory(DBObject dbObject) {
        Story story = new Story();
        story.setId((Integer) dbObject.get(StoryAttr.ID_STORY.getName()));
        story.setName((String) dbObject.get(StoryAttr.NAME.getName()));
        story.setAnnotation((String) dbObject.get(StoryAttr.ANNOTATION.getName()));
        story.setImage((String) dbObject.get(StoryAttr.IMAGE.getName()));

        BasicDBList genreList = (BasicDBList) dbObject.get(StoryAttr.GENRE.getName());
        ArrayList<String> genres = new ArrayList<String>();
        for (Object object : genreList) {
            genres.add((String) object);
        }
        story.setGenre(genres);

        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        BasicDBList chapterList = (BasicDBList) dbObject.get(StoryAttr.CHAPTERS.getName());
        for (Object object : chapterList) {
            chapters.add(convertDBObjectToChapter((DBObject) object));
        }
        story.setChapters(chapters);
        return story;
    }

    public DBObject convertChapterToDBObject(Chapter chapter) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append(ChapterAttr.ID_CHAPTER.getName(), chapter.getId())
                .append(ChapterAttr.NAME.getName(), chapter.getName())
                .append(ChapterAttr.DESCRIPTION.getName(), chapter.getDescription())
                .append(ChapterAttr.PAGES.getName(), chapter.getPages());
        return builder.get();
    }

    public Chapter convertDBObjectToChapter(DBObject dbObject) {
        Chapter chapter = new Chapter();
        chapter.setId((Integer) dbObject.get(ChapterAttr.ID_CHAPTER.getName()));
        chapter.setName((String) dbObject.get(ChapterAttr.NAME.getName()));
        chapter.setDescription((String) dbObject.get(ChapterAttr.DESCRIPTION.getName()));

        BasicDBList pagesDBList = (BasicDBList) dbObject.get(ChapterAttr.PAGES.getName());
        ArrayList<Integer> pages = new ArrayList<Integer>();
        if(pagesDBList != null){
            for (Object object : pagesDBList) {
                pages.add((Integer) object);
            }
        }
        chapter.setPages(pages);
        return chapter;
    }

    public DBObject convertPageToDBObject(Page page) {
        BasicDBList screenList = new BasicDBList();
        for (Screen screen : page.getScreens()) {
            screenList.add(convertScreenToDBObject(screen));
        }

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append(PageAttr.ID_PAGE.getName(), page.getId())
                .append(PageAttr.IS_END_CHAPTER.getName(), page.isEndChapter())
                .append(PageAttr.NEXT_CHAPTER.getName(), page.getNextChapter())
                .append(PageAttr.SCREENS.getName(), screenList);
        return builder.get();
    }

    public Page convertDBObjectToSPage(DBObject dbObject) {
        Page page = new Page();
        page.setId((Integer) dbObject.get(PageAttr.ID_PAGE.getName()));
        page.setEndChapter((Boolean) dbObject.get(PageAttr.IS_END_CHAPTER.getName()));
        page.setNextChapter((Integer) dbObject.get(PageAttr.NEXT_CHAPTER.getName()));

        BasicDBList screenList = (BasicDBList) dbObject.get(PageAttr.SCREENS.getName());
        ArrayList<Screen> screens = new ArrayList<Screen>();
        for (Object object : screenList) {
            screens.add(convertDBObjectToScreen((DBObject) object));
        }
        page.setScreens(screens);
        return page;
    }

    public DBObject convertScreenToDBObject(Screen screen) {
        BasicDBList answers = new BasicDBList();
        for (Answer answer : screen.getAnswers()) {
            answers.add(convertAnswerToDBObject(answer));
        }

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append(ScreenAttr.ID_SCREEN.getName(), screen.getId())
                .append(ScreenAttr.TEXT.getName(), screen.getText())
                .append(ScreenAttr.ANSWERS.getName(), answers);
        return builder.get();
    }

    public Screen convertDBObjectToScreen(DBObject dbObject) {
        Screen screen = new Screen();
        screen.setId((Integer) dbObject.get(ScreenAttr.ID_SCREEN.getName()));
        screen.setText((String) dbObject.get(ScreenAttr.TEXT.getName()));

        BasicDBList answerList = (BasicDBList) dbObject.get(ScreenAttr.ANSWERS.getName());
        ArrayList<Answer> answers = new ArrayList<Answer>();
        for (Object object : answerList) {
            answers.add(convertDBObjectToAnswer((DBObject) object));
        }
        screen.setAnswers(answers);
        return screen;
    }

    public DBObject convertAnswerToDBObject(Answer answer) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append(AnswerAttr.TEXT.getName(), answer.getText())
                .append(AnswerAttr.NEXT_ITEM.getName(), answer.getItemType())
                .append(AnswerAttr.NEXT_ITEM_ID.getName(), answer.getNextItemId());
        return builder.get();
    }

    public Answer convertDBObjectToAnswer(DBObject dbObject) {
        Answer answer = new Answer();
        answer.setText((String) dbObject.get(AnswerAttr.TEXT.getName()));
        answer.setItemType(ItemType.valueOf((String) dbObject.get(AnswerAttr.NEXT_ITEM.getName())));
        answer.setNextItemId((Integer) dbObject.get(AnswerAttr.NEXT_ITEM_ID.getName()));
        return answer;
    }

    public void convertUpdatesToDBObject(BasicDBObject setQuery, HashMap<Attribute, String> updates) {
        Iterator iterator = updates.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Attribute, String> pair = (Map.Entry<Attribute, String>) iterator.next();
            setQuery.append(pair.getKey().getName(), pair.getValue());
        }
    }

}
