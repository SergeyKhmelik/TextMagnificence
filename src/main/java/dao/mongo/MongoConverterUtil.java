package dao.mongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import dao.ConverterUtil;
import dao.mongo.enum_attributes.*;
import domain.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by koloturka on 20.07.15.
 */

@Component
public class MongoConverterUtil implements ConverterUtil {

	//TODO два цикла проверить на необходимость (сравнить с чаптерами)
	public DBObject convertStoryToDBObject(Story story) {
		BasicDBList genreList = new BasicDBList();
		for (String genre : story.getGenre()) {
			genreList.add(genre);
		}

		BasicDBList authorList = new BasicDBList();
		for (String author : story.getAuthor()) {
			authorList.add(author);
		}

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(StoryAttr.ID_STORY.getName(), story.getIdStory())
				.append(StoryAttr.NAME.getName(), story.getName())
				.append(StoryAttr.IMAGE.getName(), story.getImage())
				.append(StoryAttr.START_PAGE.getName(), story.getStartPage())
				.append(StoryAttr.AUTHOR.getName(), authorList)
				.append(StoryAttr.DATE.getName(), story.getDate())
				.append(StoryAttr.GENRE.getName(), genreList)
				.append(StoryAttr.ANNOTATION.getName(), story.getAnnotation());

		return builder.get();
	}

	public Story convertDBObjectToStory(DBObject dbObject) {
		Story story = new Story();
		story.setIdStory((Integer) dbObject.get(StoryAttr.ID_STORY.getName()));
		story.setName((String) dbObject.get(StoryAttr.NAME.getName()));
		story.setAnnotation((String) dbObject.get(StoryAttr.ANNOTATION.getName()));
		story.setImage((String) dbObject.get(StoryAttr.IMAGE.getName()));
		story.setDate((Date) dbObject.get(StoryAttr.DATE.getName()));
		story.setStartPage((Integer) dbObject.get(StoryAttr.START_PAGE.getName()));

		Set<String> genres = new HashSet<String>();
		BasicDBList genreList = (BasicDBList) dbObject.get(StoryAttr.GENRE.getName());
		for (Object object : genreList) {
			genres.add(object.toString());
		}
		story.setGenre(genres);

		List<String> authors = new ArrayList<String>();
		BasicDBList authorList = (BasicDBList) dbObject.get(StoryAttr.AUTHOR.getName());
		for (Object object : authorList) {
			authors.add(object.toString());
		}
		story.setAuthor(authors);
		return story;
	}

	public DBObject convertChapterToDBObject(Chapter chapter) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(ChapterAttr.ID_CHAPTER.getName(), chapter.getIdChapter())
				.append(ChapterAttr.ID_STORY.getName(), chapter.getIdStory())
				.append(ChapterAttr.NAME.getName(), chapter.getName())
				.append(ChapterAttr.DESCRIPTION.getName(), chapter.getDescription());
		if (chapter.getStartPages() == null) {
			builder.append(ChapterAttr.START_PAGES.getName(), new ArrayList<Integer>());
		} else {
			builder.append(ChapterAttr.START_PAGES.getName(), chapter.getStartPages());
		}
		return builder.get();
	}

	public Chapter convertDBObjectToChapter(DBObject dbObject) {
		Chapter chapter = new Chapter();
		chapter.setIdChapter((Integer) dbObject.get(ChapterAttr.ID_CHAPTER.getName()));
		chapter.setIdStory((Integer) dbObject.get(ChapterAttr.ID_STORY.getName()));
		chapter.setName((String) dbObject.get(ChapterAttr.NAME.getName()));
		chapter.setDescription((String) dbObject.get(ChapterAttr.DESCRIPTION.getName()));

		BasicDBList startPagesDBList = (BasicDBList) dbObject.get(ChapterAttr.START_PAGES.getName());
		List<Integer> pages = new ArrayList<Integer>();
		if (startPagesDBList != null) {
			for (Object object : startPagesDBList) {
				pages.add((Integer) object);
			}
		}
		chapter.setStartPages(pages);
		return chapter;
	}

	public DBObject convertPageToDBObject(Page page) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(PageAttr.ID_PAGE.getName(), page.getIdPage())
				.append(PageAttr.ID_CHAPTER.getName(), page.getIdChapter())
				.append(PageAttr.IS_END_CHAPTER.getName(), page.isEndChapter());
		if (page.getScreens() != null) {
			BasicDBList screens = new BasicDBList();
			for (Screen screen : page.getScreens()) {
				screens.add(convertScreenToDBObject(screen));
			}
			builder.add(PageAttr.SCREENS.getName(), screens);
		} else {
			builder.append(PageAttr.SCREENS.getName(), new ArrayList<Screen>());
		}
		if (page.isEndChapter()) {
			builder.append(PageAttr.NEXT_CHAPTER.getName(), page.getNextChapter());
		}
		return builder.get();
	}

	public Page convertDBObjectToPage(DBObject dbObject) {
		Page page = new Page();
		page.setIdPage((Integer) dbObject.get(PageAttr.ID_PAGE.getName()));
		page.setIdChapter((Integer) dbObject.get(PageAttr.ID_CHAPTER.getName()));
		page.setEndChapter((Boolean) dbObject.get(PageAttr.IS_END_CHAPTER.getName()));

		if (page.isEndChapter()) {
			page.setNextChapter((Integer) dbObject.get(PageAttr.NEXT_CHAPTER.getName()));
		}

		BasicDBList screenList = (BasicDBList) dbObject.get(PageAttr.SCREENS.getName());
		ArrayList<Screen> screens = new ArrayList<Screen>();
		if (screenList != null) {
			for (Object object : screenList) {
				screens.add(convertDBObjectToScreen((DBObject) object));
			}
		}

		page.setScreens(screens);
		return page;
	}

	public DBObject convertScreenToDBObject(Screen screen) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(ScreenAttr.ID_SCREEN.getName(), screen.getIdScreen())
				.append(ScreenAttr.TEXT.getName(), screen.getText());
		if (screen.getAnswers() != null) {
			BasicDBList answers = new BasicDBList();
			for (Answer answer : screen.getAnswers()) {
				answers.add(convertAnswerToDBObject(answer));
			}
			builder.add(ScreenAttr.ANSWERS.getName(), answers);
		} else {
			builder.append(ScreenAttr.ANSWERS.getName(), new ArrayList<Answer>());
		}
		return builder.get();
	}

	public Screen convertDBObjectToScreen(DBObject dbObject) {
		Screen screen = new Screen();
		screen.setIdScreen((Integer) dbObject.get(ScreenAttr.ID_SCREEN.getName()));
		screen.setText((String) dbObject.get(ScreenAttr.TEXT.getName()));

		BasicDBList answerList = (BasicDBList) dbObject.get(ScreenAttr.ANSWERS.getName());
		ArrayList<Answer> answers = new ArrayList<Answer>();
		if (answerList != null) {
			for (Object object : answerList) {
				answers.add(convertDBObjectToAnswer((DBObject) object));
			}
		}
		screen.setAnswers(answers);
		return screen;
	}

	public DBObject convertAnswerToDBObject(Answer answer) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(AnswerAttr.TEXT.getName(), answer.getText())
				.append(AnswerAttr.NEXT_ITEM.getName(), answer.getNextItem().name().toLowerCase())
				.append(AnswerAttr.NEXT_ITEM_ID.getName(), answer.getNextItemId());
		return builder.get();
	}

	public Answer convertDBObjectToAnswer(DBObject dbObject) {
		Answer answer = new Answer();
		answer.setText((String) dbObject.get(AnswerAttr.TEXT.getName()));
		answer.setNextItem(ItemType.valueOf(((String) dbObject.get(AnswerAttr.NEXT_ITEM.getName())).toUpperCase()));
		answer.setNextItemId((Integer) dbObject.get(AnswerAttr.NEXT_ITEM_ID.getName()));
		return answer;
	}

	public void convertUpdatesToDBObject(BasicDBObject setQuery, Map<? extends Attribute, Object> updates) {
		Iterator iterator = updates.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Attribute, Object> pair = (Map.Entry<Attribute, Object>) iterator.next();
			setQuery.append(pair.getKey().getName(), pair.getValue());
		}
	}

	@Override
	public DBObject convertUserToDBObject(User user) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(UserAttr.NAME.getName(), user.getName())
				.append(UserAttr.PASSWORD.getName(), user.getPassword())
				.append(UserAttr.AGE.getName(), user.getAge())
				.append(UserAttr.ROLE.getName(), user.getRole().toValue().toLowerCase())
				.append(UserAttr.EMAIL.getName(), user.getEmail())
				.append(UserAttr.ENABLED.getName(), user.isEnabled())
				.append(UserAttr.LOCKED.getName(), user.isLocked());
		return builder.get();
	}

	@Override
	public User convertDBObjectToUser(DBObject object) {
		User user = new User();
		user.setName((String) object.get(UserAttr.NAME.getName()));
		user.setPassword((String) object.get(UserAttr.PASSWORD.getName()));
		user.setAge((Integer) object.get(UserAttr.AGE.getName()));
		user.setEmail((String) object.get(UserAttr.EMAIL.getName()));
		user.setRole(Role.valueOf(((String) object.get(UserAttr.ROLE.getName())).toUpperCase()));
		user.setEnabled((Boolean) object.get(UserAttr.ENABLED.getName()));
		user.setLocked((Boolean) object.get(UserAttr.LOCKED.getName()));
		return user;
	}

	@Override
	public DBObject convertSaveToDBObject(Save save) {
		BasicDBList pages = new BasicDBList();
		pages.addAll(save.getPages());
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append(SaveAttr.ID_STORY.getName(), save.getIdStory())
				.append(SaveAttr.USERNAME.getName(), save.getUsername())
				.append(SaveAttr.PAGES.getName(), pages);
		return builder.get();
	}

	@Override
	public Save convertDBObjectToSave(DBObject dbObject) {
		Save save = new Save();
		save.setIdStory((Integer) dbObject.get(SaveAttr.ID_STORY.getName()));
		save.setUsername((String) dbObject.get(SaveAttr.USERNAME.getName()));
		BasicDBList pagesDBList = (BasicDBList) dbObject.get(SaveAttr.PAGES.getName());
		List<Integer> pages = new ArrayList<Integer>();
		if (pagesDBList != null) {
			for (Object object : pagesDBList) {
				pages.add((Integer) object);
			}
		}
		save.setPages(pages);
		return save;
	}

}
