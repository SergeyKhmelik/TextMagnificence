package web.game_management;

import domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.GameManagementService;
import web.data_utils.ResponseObject;

import javax.validation.Valid;

/**
 * Created by koloturka on 14.08.2015.
 */

@RestController
@RequestMapping("/stories/{idStory}/chapters/{idChapter}/pages/{idPage}/screens/{idScreen}/answers")
public class AnswerController {

	private GameManagementService gameManagementService;

	@Autowired
	public void setGameManagementService(GameManagementService gameManagementService) {
		this.gameManagementService = gameManagementService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseObject<Answer> readAnswers(@PathVariable int idPage, @PathVariable int idScreen){
			return new ResponseObject(gameManagementService.readAnswers(idPage, idScreen));
	}


	@RequestMapping(value ="/{answerText}", method = RequestMethod.GET)
	public @ResponseBody ResponseObject<Answer> readAnswer(@PathVariable int idPage, @PathVariable int idScreen,
	                                                       @PathVariable String answerText){
		return new ResponseObject(gameManagementService.readAnswer(idPage, idScreen, answerText));
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseObject<Answer> insertAnswer(@PathVariable int idPage, @PathVariable int idScreen ,
	                                                         @Valid @RequestBody Answer answer){
		return new ResponseObject(gameManagementService.insertAnswer(idPage, idScreen, answer));
	}

	@RequestMapping(value = "/{answerText}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseObject<String> deleteAnswer(@PathVariable int idPage, @PathVariable int idScreen,
	                                                         @PathVariable String answerText){
		return new ResponseObject(gameManagementService.deleteAnswer(idPage, idScreen, answerText));
	}

}
