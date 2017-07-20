package br.com.md.util;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioUtil {
	private static String erro = null;
	public static byte[] criarRelatorio(String arquivoJasper, Map<String, Object> parametros){
		byte[] bytes = null;
		try {
			Connection conn = HibernateUtil.getConnection();
			JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile(arquivoJasper);
			bytes = JasperRunManager.runReportToPdf(relatorio, parametros, conn);
		} catch (JRException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar o arquivo para impressão! ERRO: [ "+e.getMessage()+" ]");
			erro = e.getMessage();
			
		}
		return bytes;
	}
	
	public static String erroRelatorio(){
		return erro;
	}

}
