package web.game_management;

import domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.GameManagementService;
import web.data_utils.ResponseObject;

import javax.validation.Valid;

/**
 * Created by koloturka on 07.08.15.
 */
@RestController
@RequestMapping("/stories/{idStory}/chapters")
public class ChapterController {

    private GameManagementService gameManagementService;

    @Autowired
    public void setGameManagementService(GameManagementService gameManagementService) {
        this.gameManagementService = gameManagementService;
    }

    /**
     * Reads all chapters, that belong to story {@code idStory}
     * @param idStory URL path variable, that defines story domain.
     * @return List of chapters of {@code idStory} story.
     */

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseObject<Chapter> getChapters(@PathVariable int idStory){
        return new ResponseObject(gameManagementService.readChapters(idStory));
    }


    @RequestMapping(value = "/{idChapter}", method = RequestMethod.GET)
    public @ResponseBody ResponseObject<Chapter> getChapter(@PathVariable int idStory, @PathVariable int idChapter){
        return new ResponseObject(gameManagementService.readChapter(idStory, idChapter));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseObject<Chapter> createChapter(@PathVariable int idStory, @Valid @RequestBody Chapter chapter){
        chapter.setIdStory(idStory);
        return new ResponseObject(gameManagementService.insertChapter(chapter));
    }

//    //TODO not workin' ( attribute map, how to? :( )
//    @RequestMapping(value = "/{idChapter}", method = RequestMethod.PUT)
//    public @ResponseBody ResponseObject<Chapter> updateChapter(@PathVariable int idStory, @PathVariable int idChapter,
//                                                               @RequestBody Map<Attribute, Object> updates){
//        return new ResponseObject(gameManagementService.updateChapter(idStory, idChapter, updates));
//    }

    @RequestMapping(value = "/{idChapter}", method = RequestMethod.PUT)
    public @ResponseBody ResponseObject<Chapter> upsertChapter(@PathVariable int idStory, @PathVariable int idChapter,
                                                               @Valid @RequestBody Chapter chapter){
        return new ResponseObject(gameManagementService.upsertChapter(idStory, idChapter, chapter));
    }

    /*
    @RequestMapping(value = "/{idChapter}", method = RequestMethod.DELETE)
    public String deleteChapter(@PathVariable int idStory, @PathVariable int idChapter){
        return gameManagementService.deleteChapter(idStory, idChapter);
    }
    */

}
