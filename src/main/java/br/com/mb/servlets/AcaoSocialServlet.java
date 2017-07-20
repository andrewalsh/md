package br.com.mb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.md.util.RelatorioUtil;




@WebServlet("/acaoSocialServlet")
public class AcaoSocialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AcaoSocialServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		String caminho = getServletContext().getRealPath("/WEB-INF/reports/Beneficios.jasper");
		
		byte[] bytes = RelatorioUtil.criarRelatorio(caminho, parametros);
		
		if(bytes != null && bytes.length > 0){
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		}else{
			String erro = RelatorioUtil.erroRelatorio();
			PrintWriter out = response.getWriter();

	        out.println("<html>");
	        out.println("<body>");
	        out.println("<h1>");
	        out.println("Erro ao gerar relatório: ERRO:[ "+erro+" ]");
	        out.println("</h1>");
	        out.println("</body>");
	        out.println("</html>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
