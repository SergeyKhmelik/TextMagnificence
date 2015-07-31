package web.controller;

import dao.GameManagementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by koloturka on 30.07.15.
 */

@Controller
@RequestMapping("/test")
public class MainController {

    private GameManagementDao gameManagementDao;

    @Autowired
    public void setGameManagementDao(GameManagementDao gameManagementDao) {
        this.gameManagementDao = gameManagementDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(Map<String, Object> model){
        System.out.println("XYU XYU XYU");
        model.put("stories", gameManagementDao.readStories());

        return "admin/story_create";
    }



}
