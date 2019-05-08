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
	 *  企业修改信息 
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
		//取得更改后表单中的公司资料信息
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
            //创建一个servletfileupload对象
            FileItemFactory factory=new DiskFileItemFactory() ;          
            ServletFileUpload upload=new ServletFileUpload(factory);
            
            try {
                //items是List类型，存放前台传来的数据
                List<FileItem> items=upload.parseRequest(request);
                //迭代器来遍历，类似resultset
                Iterator<FileItem>iter=items.iterator();
                while(iter.hasNext()) {
                    FileItem item=iter.next();
                    //getFieldName是获取普通表单字段的name值
                    String itemName=item.getFieldName();
                 
                    //判断前台传过来的数据是否为普通表单字段或者是文件类型
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
                    {   //EPlogo,文件上传
                        //getName获取文件名字
                        fileName=item.getName();
                        String ext=fileName.substring(fileName.indexOf(".")+1);
                        if(!(ext.equals("jpg")||ext.equals("png")||ext.equals("jpeg"))) {
                            System.out.println("图片格式有误");
                            request.setAttribute("error", "图片格式错误");
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
                                System.out.println("删除文件操作出错");
                                e.printStackTrace();
                            }
                            
                            System.out.println("删除旧文件成功！");
                            File file=new File(path,fileName);
                            upload.setSizeMax(40960);//40kb
                            try {
                                item.write(file);
                                System.out.println(fileName+"上传成功");
                                request.setAttribute("error", "图片上传成功");
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
		
		//创建公司资料对象
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
		//将epd对象添加进数据库中
		try {
			EPDataDao epdd = new EPDataDao();
			epdd.updateEPData(epd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转向EPHome.jsp页面
		request.getRequestDispatcher("EPHomeServlet").forward(request, response);
	}

}
