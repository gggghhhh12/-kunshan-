package priv.zx.ecruit.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;

public class EPHandleModifyData extends HttpServlet {

	/**
	 *  ��ҵ�޸���Ϣ 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//ȡ�ø��ĺ���еĹ�˾������Ϣ
		String EPname=null ;
        String EPnature=null ;
        String EPcode=null ;
        String EPtrade=null ;
        String EPscale=null;
        String EPaddr=null ;
        String EPcontact=null;
        String EPemail=null ;
        String EPtel=null;
        String EPmobile=null ;
        String EPpostalcode=null ;
        String EPintroduction=null ;
        String fileName=null;
        HttpSession session = request.getSession();
        String EPusername = (String) session.getAttribute("EPUser");
        boolean isMultipart=ServletFileUpload.isMultipartContent(request);
        if(isMultipart) {
            //����һ��servletfileupload����
            FileItemFactory factory=new DiskFileItemFactory() ;          
            ServletFileUpload upload=new ServletFileUpload(factory);
            
            try {
                //items��List���ͣ����ǰ̨����������
                List<FileItem> items=upload.parseRequest(request);
                //������������������resultset
                Iterator<FileItem>iter=items.iterator();
                while(iter.hasNext()) {
                    FileItem item=iter.next();
                    //getFieldName�ǻ�ȡ��ͨ���ֶε�nameֵ
                    String itemName=item.getFieldName();
                 
                    //�ж�ǰ̨�������������Ƿ�Ϊ��ͨ���ֶλ������ļ�����
                    if (item.isFormField()){
                        if(itemName.equals("EPname")) {
                            EPname=item.getString("utf-8");
                        }
                        if(itemName.equals("EPnature")) {
                            EPnature=item.getString("utf-8");
                        }
                        if(itemName.equals("EPcode")) {
                            EPcode=item.getString("utf-8");
                        }
                        if(itemName.equals("EPtrade")) {
                            EPtrade=item.getString("utf-8");
                        }
                        if(itemName.equals("EPscale")) {
                            EPscale=item.getString("utf-8");
                        }
                        if(itemName.equals("EPaddr")) {
                            EPaddr=item.getString("utf-8");
                        }
                        if(itemName.equals("EPemail")) {
                            EPemail=item.getString("utf-8");
                        }
                        if(itemName.equals("EPcontact")) {
                            EPcontact=item.getString("utf-8");
                        }
                        if(itemName.equals("EPtel")) {
                            EPtel=item.getString("utf-8");
                        }
                        if(itemName.equals("EPmobile")) {
                            EPmobile=item.getString("utf-8");
                        }
                        if(itemName.equals("EPpostalcode")) {
                            EPpostalcode=item.getString("utf-8");
                        }
                        if(itemName.equals("EPintroduction")) {
                            EPintroduction=item.getString("utf-8");
                        }
                        
                    }
                    else
                    {   //EPlogo,�ļ��ϴ�
                        //getName��ȡ�ļ�����
                        fileName=item.getName();
                        String ext=fileName.substring(fileName.indexOf(".")+1);
                        if(!(ext.equals("jpg")||ext.equals("png")||ext.equals("jpeg"))) {
                            System.out.println("ͼƬ��ʽ����");
                            request.setAttribute("error", "ͼƬ��ʽ����");
                        }
                        else{
                            String path="E:\\picupload\\";
                            try {
                                EPData epd = new EPData();
                                EPDataDao epdd = new EPDataDao();
                                try {
                                    epd = epdd.getEPData(EPusername);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String old_fileName=epd.getEPlogo();
                                String filePath =path+old_fileName ;
                                filePath = filePath.toString();
                                System.out.println(filePath);
                                java.io.File myDelFile = new java.io.File(filePath);
                                myDelFile.delete();
                            } catch (Exception e) {
                                System.out.println("ɾ���ļ���������");
                                e.printStackTrace();
                            }
                            
                            System.out.println("ɾ�����ļ��ɹ���");
                            File file=new File(path,fileName);
                            upload.setSizeMax(40960);//40kb
                            try {
                                item.write(file);
                                System.out.println(fileName+"�ϴ��ɹ�");
                                request.setAttribute("error", "ͼƬ�ϴ��ɹ�");
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        
                    }
                    
                }
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
		//������˾���϶���
		EPData epd = new EPData();
		epd.setEPusername(EPusername);
		epd.setEPname(EPname);
		epd.setEPnature(EPnature);
		epd.setEPcode(EPcode);
		epd.setEPtrade(EPtrade);
		epd.setEPscale(EPscale);
		epd.setEPaddr(EPaddr);
		epd.setEPcontact(EPcontact);
		epd.setEPemail(EPemail);
		epd.setEPtel(EPtel);
		epd.setEPmobile(EPmobile);
		epd.setEPpostalcode(EPpostalcode);
		epd.setEPintroduction(EPintroduction);
		epd.setEPlogo(fileName);
		//��epd������ӽ����ݿ���
		try {
			EPDataDao epdd = new EPDataDao();
			epdd.updateEPData(epd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��EPHome.jspҳ��
		request.getRequestDispatcher("EPHomeServlet").forward(request, response);
	}

}
