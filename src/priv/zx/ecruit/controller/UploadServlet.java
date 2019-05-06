/*package priv.zx.ecruit.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.EPSelectResult;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.User;
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// �ж��ϴ����Ƿ�Ϊmultipart/form-data����
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user"); // �ڵ�¼ʱ�� User ��������� �Ự
							 // ��
 
	if (ServletFileUpload.isMultipartContent(request)) {
 
	    try {
		// 1. ����DiskFileItemFactory�������û�������С����ʱ�ļ�Ŀ¼
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// System.out.println(System.getProperty("java.io.tmpdir"));//Ĭ����ʱ�ļ���
 
		// 2. ����ServletFileUpload���󣬲������ϴ��ļ��Ĵ�С���ơ�
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setSizeMax(10 * 1024 * 1024);// ��byteΪ��λ ���ܳ���10M 1024byte =
						 // 1kb 1024kb=1M 1024M = 1G
		sfu.setHeaderEncoding("utf-8");
 
		// 3.
		// ����ServletFileUpload.parseRequest��������request���󣬵õ�һ�������������ϴ����ݵ�List����
		@SuppressWarnings("unchecked")
		List<FileItem> fileItemList = sfu.parseRequest(request);
		Iterator<FileItem> fileItems = fileItemList.iterator();
 
		// 4. ����list��ÿ����һ��FileItem���󣬵�����isFormField�����ж��Ƿ����ϴ��ļ�
		while (fileItems.hasNext()) {
		    FileItem fileItem = fileItems.next();
		    // ��ͨ��Ԫ��
		    if (fileItem.isFormField()) {
			String name = fileItem.getFieldName();// name����ֵ
			String value = fileItem.getString("utf-8");// name��Ӧ��valueֵ
 
			System.out.println(name + " = " + value);
		    }
		    // <input type="file">���ϴ��ļ���Ԫ��
		    else {
			String fileName = fileItem.getName();// �ļ�����
			System.out.println("ԭ�ļ�����" + fileName);// Koala.jpg
 
			String suffix = fileName.substring(fileName.lastIndexOf('.'));
			System.out.println("��չ����" + suffix);// .jpg
 
			// ���ļ�����Ψһ��
			String newFileName = new Date().getTime() + suffix;
			System.out.println("���ļ�����" + newFileName);// image\1478509873038.jpg
 
			// 5. ����FileItem��write()������д���ļ�
			File file = new File("D:/lindaProjects/mySpace/wendao/WebContent/touxiang/" + newFileName);
			System.out.println(file.getAbsolutePath());
			fileItem.write(file);
 
			// 6. ����FileItem��delete()������ɾ����ʱ�ļ�
			fileItem.delete();
 
			/*
			 * �洢�����ݿ�ʱע�� 1.����Դ�ļ����� Koala.jpg 2.�������·��
			 * image/1478509873038.jpg
			 * 
			 */
			/*if (user != null) {
			    int myid = user.getId();
			    String SQL = "INSERT INTO t_touxiang(image_path,user_id,old_name)VALUES(?,?,?)";
			    int rows = JdbcHelper.insert(SQL, false, "touxiang/" + newFileName, myid, fileName);
			    if (rows > 0) {
				session.setAttribute("image_name", fileName);
				session.setAttribute("image_path", "touxiang/" + newFileName);
				response.sendRedirect(request.getContextPath() + "/upImage.html");
			    } else {
 
			    }
 
			} else {
			    session.setAttribute("loginFail", "���¼");
			    response.sendRedirect(request.getContextPath() + "/login.html");
			}
 
		    }
		}
 
	    } catch (FileUploadException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
 
	}
    }
}*/
