package com.programmingfree.fileupload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class UploadFile
 */

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UploadFile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	        if (isMultipart) {
	        	// Create a factory for disk-based file items
	        	FileItemFactory factory = new DiskFileItemFactory();

	        	// Create a new file upload handler
	        	ServletFileUpload upload = new ServletFileUpload(factory);
	 
	            try {
	            	String filter = "grayscale", root = "";
	            	// Parse the request
					List /* FileItem */ items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
	                    if(item.isFormField()) {
	                    	filter = item.getString();
	                    	System.out.println(item.getString());
	                    }
	                    if (!item.isFormField()) {
	                        String fileName = item.getName();
	                        root = getServletContext().getRealPath("/");
	                        File path = new File(root + "/uploads");
	                        if (!path.exists()) {
								boolean status = path.mkdirs();
	                        }
	                        String newFileName = "input.jpg";
	                        File uploadedFile = new File(path + "/" + newFileName);
	                        String fileLocation = uploadedFile.getAbsolutePath();
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        //System.out.println(root);
	                        item.write(uploadedFile);
	                        //System.out.println("Verificare1");
	                        
	                    }
	                }
	                System.out.println(filter);
	                switch(filter) {
		                case "grayscale": {
		                	Grayscale.RGB2Grayscale(root);
		                	break;
		                }
		                case "sepia": {
		                	Sepia.RGB2Sepia(root);
		                	break;
		                }
		                case "green":{
		                	GreenImage.RGB2Green(root);
		                	break;
		                }
		                case "negative":{
		                	NegativeImage.RGB2Negative(root);
		                	break;
		                }
	                }
	            }
	             catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            
	            PrintWriter out = response.getWriter();
	            out.println("<img src='C:\\Users\\Delia Oana\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\AjaxFileUpload\uploads\Output.jpg' >");
	        }
	        
	}

}
