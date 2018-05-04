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
     * ��ʱ����
     */
    private static MyTask task = new MyTask();
    private static MyTaskReal taskReal = new MyTaskReal();

    /**
     * ��ʱ��
     */
    private static Timer timer = null;

    /**
     * Web������������ִ��
     */
    public void contextInitialized(ServletContextEvent event) {
       System.out.println("��ʱ����������");
       timer = new Timer();
       //1000-��ʾ�������1���ִ�У�10*1000��ʾÿ��ִ�еļ��Ϊ10��.
       //timer.schedule(task, 10 * 1000, 24 * 60 * 60 * 1000); 
//       timer.schedule(taskReal, 20 * 1000, 60 * 60 * 1000); //������ÿСʱִ��һ��
    }

    /**
     * Web�������رպ�ִ��
     */
    public void contextDestroyed(ServletContextEvent event) {
       timer.cancel();
       System.out.println("��ʱ������ȡ��");
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
     * ͨ��HTTP-Servlet���ƶ�ʱ���Ŀ���.
     * @param request
     * @param response
     * @param event
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
       response.setContentType("text/html;charset=UTF-8");
       String action = request.getParameter("action");
       if ("shutdown".equals(action)) {
           timer.cancel();
           this.getServletContext().log("��ʱ������ȡ��");
       } else if ("start".equals(action)) {
           timer.cancel();
           timer = new Timer();
           this.getServletContext().log("��ʱ����������");
           timer.schedule(task, 1000, 10 * 1000);
       }
    }

   

}
