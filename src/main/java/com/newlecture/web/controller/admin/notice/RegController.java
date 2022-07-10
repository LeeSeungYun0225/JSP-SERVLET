package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;



@MultipartConfig(
		fileSizeThreshold=1024*1024, // 바이트 단위(1024Byte = 1kilobytes, 1024*1024 = 1mb) 전송하는 데이터가 이 기준을 넘어서면 디스크를 사용함 
		maxFileSize=1024*1024*50,//파일 사이즈 제한 - 서비스가 마비되는것을 방지함, 한개의 파일 사이즈를 제한하는것  = 5MB
		maxRequestSize=1024*1024*50*5 // 전체 요청 파일의 크기는 25MB를 넘어설 수 없음 
		)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
	
	
	 try {
			request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()	;
		}

	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Part filePart = request.getPart("file");//
		String fileName = filePart.getSubmittedFileName();
		InputStream fis = filePart.getInputStream();
		// 상대 경로 주소를 사용할 수 없음 - 절대경로로..
		// /upload에 저장하고자 할때
		String realPath = request.getServletContext().getRealPath("/upload");//상대주소를 물리주소로 반환해줌 
		
		//int b = fis.read();// 바이트 단위로 데이터를 읽어온다. 데이터를 더 읽을 것이 없을때 정수형으로 -1 반환 
		//int b;
		String filePath = realPath + File.separator + fileName;
		// File.separator는 절대경로 ..../upload 뒤에 /파일명으로 저장을 해야되는데 /(역슬래시)를 쓰는건 윈도우즈 국한 방법
		// 운영체제에 따라 방법이 다르므로 자바에서 제공하는 File.separator를 +"/"+ 대신 쓰는게 맞다.
		
		
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buffer = new byte[1024];//1kbyte
		
		int size = 0;
		//while((b = fis.read())!=-1) // 바이트 하나만 읽어와서 오래걸린다
		while((size=fis.read(buffer))!=-1) // fis.read(byte[])형식은 읽어온 개수를 반환한다. 
		{
			fos.write(buffer,0,size);// 버퍼에 0인덱스부터 size개수만큼 가져와서 write
		}
		
		
		fos.close();
		fis.close();
		
		boolean pub = false;

		if(isOpen != null)
		{
			pub = true;
		}
		
		Notice notice = new Notice();
		notice.setContent(content);
		notice.setTitle(title);
		notice.setPub(pub);
		notice.setWriter_id("Tester");
		NoticeService service = new NoticeService();
		
		
		service.insertNotice(notice);
		

		response.sendRedirect("/admin/board/notice/list");
	}

	


}
