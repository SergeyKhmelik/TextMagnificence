package web.servlet;

import web.servlet.commands.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by koloturka on 24.07.15.
 */
public class StoryServlet extends HttpServlet {

    private Map<String, Action> actionMap = new HashMap<String, Action>();

    @Override
    public void init() throws ServletException {
        actionMap.put("createStory", new CreateStoryAction());
        actionMap.put("readStories", new ReadStoriesAction());
        actionMap.put("updateStory", new UpdateStoryAction());
        actionMap.put("deleteStory", new DeleteStoryAction());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String actionKey = req.getParameter("action");
            Action action = actionMap.get(actionKey);
            String view = action.execute(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
