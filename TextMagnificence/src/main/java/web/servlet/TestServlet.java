package web.servlet;

import dao.GameManagementDao;
import entity.Story;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by koloturka on 23.07.15.
 */
public class TestServlet extends HttpServlet {

    GameManagementDao gameManagementDao;

    @Override
    public void init() throws ServletException {
        gameManagementDao = (GameManagementDao) getServletContext().getAttribute("gameManagementDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8;");
        resp.setCharacterEncoding("UTF-8"); // кодировка ответа
        req.setCharacterEncoding("UTF-8");
        System.out.println("TEXT SERBLET, СУКА БЛЯТЬ");
        List<Story> stories = gameManagementDao.readStories();
        System.out.println(stories);
        req.setAttribute("stories", stories);
        req.getRequestDispatcher("testjsp").forward(req, resp);

    }
}
