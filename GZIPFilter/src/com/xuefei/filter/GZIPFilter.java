package com.xuefei.filter;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZIPFilter implements Filter {

	@Override
	public void destroy() {
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//����������Ϣ
		MyHttpResponse myresponse=new MyHttpResponse((HttpServletResponse)arg1);
		arg2.doFilter(arg0, myresponse);
		//������Ӧ����
		
		char[] charArray = MyHttpResponse.getCharArray();
		MyHttpResponse.CleanCharArray();//��ջ��������
		
		ByteArrayOutputStream bao=new ByteArrayOutputStream();
		GZIPOutputStream zip=new GZIPOutputStream(bao);
		zip.write(new String(charArray).getBytes());
		zip.finish();
		
		((HttpServletResponse)arg1).setHeader("content-encoding", "gzip");
		arg1.getOutputStream().write(bao.toByteArray());
	}


}

class MyHttpResponse extends HttpServletResponseWrapper{

	private static CharArrayWriter caw=new CharArrayWriter();
	public MyHttpResponse(HttpServletResponse response) {
		super(response);
	}
	//���ػ�����д�������
	public static char[] getCharArray(){
		return caw.toCharArray();		
	}
	public static void CleanCharArray(){
		caw=new CharArrayWriter();
	}
	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(caw);
	}
	
}