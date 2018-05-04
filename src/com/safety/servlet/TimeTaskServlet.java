package com.safety.servlet;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeTaskServlet extends HttpServlet  implements ServletContextListener {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 8196453254826723825L;

	/**
     * 定时任务
     */
    private static MyTask task = new MyTask();
    private static MyTaskReal taskReal = new MyTaskReal();

    /**
     * 定时器
     */
    private static Timer timer = null;

    /**
     * Web服务器启动后执行
     */
    public void contextInitialized(ServletContextEvent event) {
       System.out.println("定时任务已启动");
       timer = new Timer();
       //1000-表示启动后隔1秒才执行，10*1000表示每次执行的间隔为10秒.
       //timer.schedule(task, 10 * 1000, 24 * 60 * 60 * 1000); 
//       timer.schedule(taskReal, 20 * 1000, 60 * 60 * 1000); //可设置每小时执行一次
    }

    /**
     * Web服务器关闭后执行
     */
    public void contextDestroyed(ServletContextEvent event) {
       timer.cancel();
       System.out.println("定时任务已取消");
    }


    /**
     *
     * @param request
     * @param response
     * @param event
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
       processRequest(request, response);
    }

 
    /**
     *
     * @param request
     * @param response
     * @param event
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
       processRequest(request, response);

    }

 

    /**
     * 通过HTTP-Servlet控制定时器的开关.
     * @param request
     * @param response
     * @param event
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
       response.setContentType("text/html;charset=UTF-8");
       String action = request.getParameter("action");
       if ("shutdown".equals(action)) {
           timer.cancel();
           this.getServletContext().log("定时任务已取消");
       } else if ("start".equals(action)) {
           timer.cancel();
           timer = new Timer();
           this.getServletContext().log("定时任务已启动");
           timer.schedule(task, 1000, 10 * 1000);
       }
    }

   

}
