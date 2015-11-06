package web.game_management;

import com.mongodb.MongoTimeoutException;
import com.wordnik.swagger.annotations.Api;
import domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.GameManagementService;
import service.SaveService;
import web.response_data_utils.ResponseObject;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/stories")
@Api(value = "/stories", description = "Operations to manipulate story domain data.")
public class StoryController {

	@Autowired
	private SaveService saveService;

	@Autowired
	private GameManagementService gameManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseObject<Story> getStories() throws MongoTimeoutException{
		return new ResponseObject(gameManagementService.readStories());
	}

	@RequestMapping(value = "/{idStory}", method = RequestMethod.GET)
	public @ResponseBody ResponseObject<Story> getStory(@PathVariable int idStory) {
		return new ResponseObject(gameManagementService.readStory(idStory));
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseObject<Story> createStory(@Valid @RequestBody Story story) {
		return new ResponseObject(gameManagementService.createStory(story));
	}

	@RequestMapping(value = "/{idStory}", method = RequestMethod.PUT)
	public @ResponseBody ResponseObject<Story> updateStory(@PathVariable int idStory, @Valid @RequestBody Story story) {
		return new ResponseObject(gameManagementService.upsertStory(idStory, story));
	}

	@RequestMapping(value = "/{idStory}/lastSave", method = RequestMethod.GET)
	public ModelAndView getLastSave(@PathVariable int idStory){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		int idPage = saveService.getLastSave(idStory, username);
		int idChapter = gameManagementService.getChapterIdByPage(idPage);
		return new ModelAndView("redirect:/stories/" + idStory + "/chapters/" + idChapter + "/pages/" + idPage);
	}

	@RequestMapping(value = "/{idStory}/resetSave", method = RequestMethod.GET)
	public ModelAndView resetSave(@PathVariable int idStory){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		saveService.deleteSave(idStory, username);
		return new ModelAndView("redirect:/stories");
	}

	/*
    @RequestMapping(value = "/{idStory}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteStory(@PathVariable int idStory) {
        return gameManagementService.deleteStory(idStory);
    }
    */

}
