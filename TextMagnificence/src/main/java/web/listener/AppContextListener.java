package web.listener;

import com.mongodb.MongoClient;
import dao.ConverterUtil;
import dao.GameManagementDao;
import dao.UserDao;
import dao.mongo.MongoConverterUtil;
import dao.mongo.MongoGameManagementDaoImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.UnknownHostException;

public class AppContextListener implements ServletContextListener {

    public static final Logger LOGGER = Logger
            .getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        initLog4J(servletContext);

        try {
            MongoClient mongoClient = new MongoClient(
                    servletContext.getInitParameter("MONGO_HOST"),
                    Integer.parseInt(servletContext.getInitParameter("MONGODB_PORT")));

            servletContext.setAttribute("mongoClient", mongoClient);

            //BEAN CONVERTER
            ConverterUtil mongoConverterUtil = new MongoConverterUtil();

            //DAO
            GameManagementDao gameManagementDao = new MongoGameManagementDaoImpl(mongoClient, mongoConverterUtil);
            servletContext.setAttribute("gameManagementDao", gameManagementDao);

            System.out.println("MongoClient initialized successfully");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        LOGGER.info("App context initialized");
    }


    public void contextDestroyed(ServletContextEvent arg0) {}

    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext
                    .getRealPath("WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            LOGGER.error("Cannot configure Log4j", ex);
        }
        log("Log4J initialization finished");
        LOGGER.debug("Log4j has been initialized");
    }

    private void log(String msg) {
        System.out.println("[AppContextListener] " + msg);
    }

}