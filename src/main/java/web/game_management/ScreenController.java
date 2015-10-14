package web.game_management;

import domain.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.GameManagementService;
import web.data_utils.ResponseObject;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by koloturka on 14.08.2015.
 */

@RestController
@RequestMapping("/stories/{idStory}/chapters/{idChapter}/pages/{idPage}/screens")
public class ScreenController {

    private GameManagementService gameManagementService;

    @Autowired
    public void setGameManagementService(GameManagementService gameManagementService) {
        this.gameManagementService = gameManagementService;
    }

    /**
     * Read all screens for current page.
     * @param idPage
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseObject<List<Screen>> readScreens(@PathVariable int idStory, @PathVariable int idChapter,
                                                                  @PathVariable int idPage){
        return new ResponseObject(gameManagementService.readScreens(idStory, idChapter, idPage));
    }

    /**
     * Insert a screen into current page
     * @param idPage
     * @param screen
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseObject<Screen> createScreen(@PathVariable int idStory, @PathVariable int idChapter,
                                                             @PathVariable int idPage, @RequestBody @Valid Screen screen){
        return new ResponseObject(gameManagementService.insertScreen(idStory, idChapter, idPage, screen));
    }

    @RequestMapping(value = "/{idScreen}", method = RequestMethod.GET)
    public @ResponseBody ResponseObject<Screen> readScreen(@PathVariable int idStory, @PathVariable int idChapter,
                                                           @PathVariable int idPage, @PathVariable int idScreen){
        return new ResponseObject(gameManagementService.readScreen(idStory, idChapter, idPage, idScreen));
    }

//    @RequestMapping(value = "/{idScreen}", method = RequestMethod.PUT)
//    public @ResponseBody ResponseObject<Screen> updateScreen(@PathVariable int idPage, @PathVariable int idScreen,
//                                             @RequestBody Map<Attribute, Object> updates){
//        return new ResponseObject(gameManagementService.updateScreen(idPage, idScreen, updates));
//    }

    @RequestMapping(value = "/{idScreen}", method = RequestMethod.PUT)
    public @ResponseBody ResponseObject<Screen> upsertScreen(@PathVariable int idStory, @PathVariable int idChapter,
                                                             @PathVariable int idPage, @PathVariable int idScreen,
                                                             @Valid @RequestBody Screen screen){
        System.out.println("CONTROLLER SCREEN " + screen);
        return new ResponseObject(gameManagementService.upsertScreen(idStory, idChapter, idPage, idScreen, screen));
    }

    @RequestMapping(value = "/{idScreen}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseObject<String> deleteScreen(@PathVariable int idStory, @PathVariable int idChapter,
                                                             @PathVariable int idPage, @PathVariable int idScreen) {
        return new ResponseObject(gameManagementService.deleteScreen(idStory, idChapter, idPage, idScreen));
    }

}