package web.game_management;

import domain.Page;
import domain.Role;
import exceptions.PageAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.GameManagementService;
import service.SaveService;
import web.data_utils.ResponseObject;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by koloturka on 07.08.15.
 */

@RestController
@RequestMapping("/stories/{idStory}/chapters/{idChapter}/pages")
public class PageController {

    @Autowired
    private GameManagementService gameManagementService;

    @Autowired
    private SaveService saveService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseObject<Page> getPages(@PathVariable int idStory, @PathVariable int idChapter){
        return new ResponseObject(gameManagementService.readPages(idStory, idChapter));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseObject<Page> createPage(@PathVariable int idStory, @PathVariable int idChapter, @Valid @RequestBody Page page){
        page.setIdChapter(idChapter);
        return new ResponseObject(gameManagementService.insertPage(idStory, page));
    }

    @RequestMapping(value = "/{idPage}", method = RequestMethod.GET)
    public @ResponseBody ResponseObject<Page> getPage(@PathVariable int idStory, @PathVariable int idChapter, @PathVariable int idPage) throws PageAccessException {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        if (!user.getAuthorities().contains("ROLE_ADMIN") && !saveService.isViewablePage(idStory, user.getName(), idPage)) {
            throw new PageAccessException("Not eligible to enter this page.");
        }
        return new ResponseObject(gameManagementService.readPage(idStory, idChapter, idPage));
    }

    @RequestMapping(value = "/{idPage}", method = RequestMethod.PUT)
    public @ResponseBody ResponseObject<Page> upsertPage(@PathVariable int idStory, @PathVariable int idChapter,
                                                         @PathVariable int idPage ,@Valid @RequestBody Page page) {
        return new ResponseObject(gameManagementService.upsertPage(idStory, idChapter, idPage, page));
    }

    @RequestMapping(value = "/{idPage}/nextPage", method = RequestMethod.POST)
    public ModelAndView getNextPage(@PathVariable int idStory, @PathVariable int idPage,
                                    @RequestBody Map<String, Object> map){
        int idNextPage = (int) map.get("idNextPage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        saveService.upsertSave(idStory, username, idNextPage);
        int idChapter = gameManagementService.getChapterIdByPage(idNextPage);
        return new ModelAndView("redirect:/stories/" + idStory + "/chapters/" + idChapter + "/pages/" + idNextPage);
    }


    //    @RequestMapping(value = "/{idPage}", method = RequestMethod.PUT)
//    public @ResponseBody ResponseObject<Page> updatePage(@PathVariable int idStory, @PathVariable int idChapter,
//                                                         @PathVariable int idPage ,@RequestBody Map<Attribute, Object> updates) {
//        return new ResponseObject(gameManagementService.updatePage(idStory, idChapter, idPage, updates));
//    }

        /*  DELETE TEMPORARY UNAVAILABLE
    @RequestMapping(value = "/{idPage}", method = RequestMethod.DELETE)
    public @ResponseBody String deletePage(@PathVariable int idPage){
        return gameManagementService.deletePage(idPage);
    }
    */


}
