package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.AttractionVO;

public class GetDataController implements Controller{

   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String pageNo = request.getParameter("pageNo");   
      if(pageNo == null) pageNo="1";
      String tag = request.getParameter("search");
      ArrayList<AttractionVO> alist = null;
      boolean emptyFlag = false;
      boolean flag = false;

      String path = "relatedreview.do";
      
      alist = TourDao.getInstance().getData(tag);
      System.out.println(alist+"�̰� �����");
      if(!alist.isEmpty()) 
         emptyFlag = true;
      
      else {
         /*boolean tagExist = TourDao.getInstance().tagExist(tag);
         if(tagExist) {
            String check = TourDao.getInstance().checkTag(tag);
            if(check.equals("location"))
               path = "index.jsp"; // ���߿� location(v1) �������� �̵�
               
            else if(check.equals("city")) 
               path = "index.jsp"; // ���߿� city(v2) �������� �̵�
         }
         else {
            alist = TourDao.getInstance().checkSpot(tag);
            flag = true;
         }*/
         
         String check = TourDao.getInstance().checkTag(tag);
         System.out.println(check+"check Ȯ��");
         if(check.equals("location")) {            //�˻�� location �̸��̸�
            path = "locationpage.do?location=path-"+tag; // ���߿� location(v1) �������� �̵�
            return new ModelAndView(path);
         }
            
         else if(check.equals("city")) {            //�˻�� city �̸��̸�
            ArrayList<String> locationList = TourDao.getInstance().checkLocationByCity(tag);
            if(locationList.size()!=1) {
               request.setAttribute("locationList", locationList);
               request.setAttribute("city", tag);
               path = "searchLocationByCity.jsp"; // ���߿� city(v2) �������� �̵�
               return new ModelAndView(path);
            } else {
               path = "getAttraction.do?city="+tag+"&&location="+locationList.get(0);
               return new ModelAndView(path);
            }
            
         }
         
         else {                              //�˻�� �Ѵ� �ƴϸ�
            boolean tagExist = TourDao.getInstance().tagExist(tag);
            System.out.println("tagExist : "+tagExist);
            if(!tagExist) {                     //�˻�� ����tag�� ����
               alist = TourDao.getInstance().checkSpot(tag);
               flag = true;
               System.out.println(alist+" tagExist �ȿ� alist");
               if(alist.isEmpty()) {            //�˻�� ���������� ����
                  if(tag.indexOf(" ") == -1) {   //���� ����
                     path ="noResult.jsp"; 
                     System.out.println("������������");
                  }
                  
                  else {                     //���� �־�
                     String[] arr = tag.trim().split(" ");
                     String strL = arr[arr.length-2];         //�� �տ���
                     String strC = arr[arr.length-1];         //���ϵڿ���
                     String checkC = TourDao.getInstance().checkTag(strC);
                     boolean cflag = false;
                     System.out.println(checkC+"   checkC�� Ȯ���غ���    strL : "+strL+"     strC"+strC);
                     
                     if(checkC.equals("city")) {
                        ArrayList<String> locationList = TourDao.getInstance().checkLocationByCity(strC);
                        if(locationList.size()!=1) {
                           for(int i = 0; i < locationList.size(); i++) {
                              if(locationList.get(i) == strL) {
                                 path = "getAttraction.do?location="+strL+"&&city="+strC;
                                 cflag = true;
                              }
                           }
                           if(!cflag)
                              path ="noResult.jsp";
                              
                        }
                        path = "getAttraction.do?location="+strL+"&&city="+strC;
                        
                        return new ModelAndView(path);
                        
                     }//if
                     else {
                        alist = TourDao.getInstance().checkSpot(strC);
                     }
                  }//else
               }//if alist.empty
               
               else {                        //�˻�� �������� �־�
                  System.out.println(alist+"!@#");
               }//else !alist.empty
            }//tagExist
         }
      }
      
      request.setAttribute("alist", alist);
      request.setAttribute("emptyFlag", emptyFlag);
      
      return new ModelAndView(path + "?pageNo="+pageNo+"&&tag="+tag+"&&flag="+flag);
   }
}
