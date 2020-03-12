<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, com.oreilly.servlet.multipart.* ,com.oreilly.servlet.*"%>
 <%@ page import="java.io.*" %>
<%
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception Ex){}

	String path="c:\\upload";
	int maxSize=100*1024*1024;		//파일크기
	String enctype="UTF-8"; 		//한글파일
	
	MultipartRequest mr=new MultipartRequest(request,path,maxSize,enctype,new DefaultFileRenamePolicy());  //renamepolicy (1),(2) 중복 파일 이름 처리 
	
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	
	String filename=mr.getOriginalFileName("upload");
	String fn=mr.getFilesystemName("upload");
	
	//중복 이름에 대한 처리가 필요함 originalFileName 업로드시 올리는 이름)
	//같은 파일 이름을 처리하는 FileSystemName
	
	FileBoardVO vo = new FileBoardVO();
	
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	if(filename==null){
		vo.setFilename("");
		vo.setFilesize(0);
	}else{
		File file=new File(path+"\\"+fn);
		vo.setFilename(fn);
		vo.setFilesize((int)file.length());
	}
	
	FileBoardDAO dao = new FileBoardDAO();
	//insert
	dao.boardInsert(vo);
	//이동
	response.sendRedirect("list.jsp");
	
%>