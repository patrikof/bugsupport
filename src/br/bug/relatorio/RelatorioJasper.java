package br.bug.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.bug.jsf.FacesUtil;

public class RelatorioJasper {

	/*
	 * public static <T> void exibir(String PathJasper, Map<String, Object>
	 * parametros, List<T> lista) { try {
	 * 
	 * JasperPrint relatorio = pegaRelatorioJasper(PathJasper, parametros,
	 * lista);
	 * 
	 * if (relatorio != null) { JasperViewer.viewReport(relatorio, false);
	 * 
	 * FacesUtil .addInfo("Visualização do Relatório Executada com Sucesso!"); }
	 * else { FacesUtil
	 * .addError("Erro 1 - Falha na Visualização do Relatório!"); }
	 * 
	 * } catch (Exception e) { FacesUtil .addError(
	 * "Erro 2 - Ocorreu um erro ao tentar Visualizar o Relatório. Mensagem Técnica: "
	 * + e.getMessage() + " StackTrace:" + e.getStackTrace().toString() ); } }
	 * 
	 * public static <T> OutputStream gerarPdf(InputStream inputStream,
	 * Map<String, Object> parametros, List<T> lista, HttpServletResponse
	 * response) throws JRException, IOException {
	 */
	/*
	 * public static <T> void gerarPdf(String caminhoRelatorio, Map<String,
	 * Object> parametros, List<T> lista ) throws JRException, IOException {
	 * 
	 * 
	 * try {
	 * //http://davidbuzatto.com.br/2010/11/12/jasperreports-trabalhando-com
	 * -relatorios-em-java-parte-5-relatorios-na-web/
	 * 
	 * String CaminhoArqSalvar = pegarCaminhoArquivoSalvar("pdf");
	 * //response.setContentType("application/pdf"); //OutputStream out =
	 * response.getOutputStream();
	 * 
	 * JasperPrint jasperPrint = pegaRelatorioJasper(caminhoRelatorio,
	 * parametros, lista);
	 * 
	 * 
	 * // exportacao do relatorio para outro formato, no caso PDF JRExporter
	 * exporter = new JRPdfExporter();
	 * exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	 * //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
	 * 
	 * //gera o relatório exporter.exportReport();
	 * 
	 * //return out;
	 * 
	 * 
	 * } catch (Exception e) { FacesUtil .addError(
	 * "Erro 2 - Ocorreu um erro ao tentar gerar o PDF da pesquisa. Mensagem Técnica: "
	 * + e.getMessage()+ " StackTrace:" + e.getStackTrace().toString()); }
	 * //return null; }
	 * 
	 * 
	 * 
	 * 
	 * public static String pegarCaminhoArquivoSalvar(String extensao) {
	 * JFileChooser fileopen = new JFileChooser();
	 * javax.swing.filechooser.FileFilter filter = new
	 * FileNameExtensionFilter("Arquivos " + extensao, extensao);
	 * fileopen.addChoosableFileFilter(filter); fileopen.showDialog(null,
	 * "Salvar"); return fileopen.getSelectedFile().getPath() .replaceAll("." +
	 * extensao, "").concat("." + extensao);
	 * 
	 * 
	 * }
	 * 
	 * protected static <T> JasperPrint pegaRelatorioJasper(String
	 * caminhoRelatorio, Map<String, Object> parametros, List<T> lista) throws
	 * JRException {
	 * 
	 * JasperPrint print = JasperFillManager.fillReport(caminhoRelatorio,
	 * parametros, new JRBeanCollectionDataSource(lista)); return print; }
	 */

	public static <T> void imprimir(List<T> lista, String reportUrl,
			Map<String, Object> parametros) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		String separator = System.getProperty("file.separator"); // File.separator;

		String caminhoRelativo = separator + "resources" + separator
				+ "relatorios" + separator + reportUrl + ".jasper";

		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		OutputStream os = null;

		try {

			Boolean AchouArquivo = false;

			System.out.println("Método 1:");
			System.out.println("Arquivo passado:" + reportUrl);
			System.out.println("Url Relativa montada:" + caminhoRelativo);

			String reportUrlReal = request.getSession().getServletContext()
					.getRealPath(caminhoRelativo);

			System.out.println("Url Real Encontrada:" + reportUrlReal);

			if (reportUrlReal != null) {
				File arquivo = new File(reportUrlReal);
				AchouArquivo = arquivo.exists();
			} else {
				System.out.println("Url Real Encontrada foi NULL");
			}

			if (!AchouArquivo) {
				System.out.println("Método 2:");
				System.out.println("Arquivo passado:" + reportUrl);
				System.out.println("Url Relativa montada:" + caminhoRelativo);

				reportUrlReal = request.getSession().getServletContext()
						.getResource(caminhoRelativo).getPath();

				System.out.println("NOVA Url Real Encontrada:" + reportUrlReal);

				if (reportUrlReal != null) {
					File arquivo = new File(reportUrlReal);
					AchouArquivo = arquivo.exists();
				} else {
					System.out.println("Url Real Encontrada foi NULL");
				}
			}
			/*
			 * if (!AchouArquivo) {
			 * System.out.println("Url Real Encontrada mais não existe...");
			 * System.out.println("Método 3:");
			 * System.out.println("Arquivo passado:" + reportUrl);
			 * 
			 * caminhoRelativo = separator+ reportUrl + ".jasper";
			 * System.out.println("Url Relativa montada:" + caminhoRelativo);
			 * reportUrlReal =
			 * request.getSession().getServletContext().getResource
			 * (caminhoRelativo).getPath();
			 * System.out.println("NOVA Url Real Encontrada:" + reportUrlReal);
			 * 
			 * 
			 * if (reportUrlReal != null) { File arquivo = new
			 * File(reportUrlReal); AchouArquivo = arquivo.exists(); } else {
			 * System.out.println("Url Real Encontrada foi NULL"); } }
			 */
			if (!AchouArquivo) {
				System.out.println("Url Real Encontrada mais não existe...");
				System.out.println("Método 3(era o 4):");
				System.out.println("Arquivo passado:" + reportUrl);
				caminhoRelativo = "resources" + separator + "relatorios"
						+ separator + reportUrl + ".jasper";
				System.out.println("Url Relativa montada:" + caminhoRelativo);
				reportUrlReal = request.getSession().getServletContext()
						.getResource(caminhoRelativo).getPath();
				System.out.println("NOVA Url Real Encontrada:" + reportUrlReal);

				if (reportUrlReal != null) {
					File arquivo = new File(reportUrlReal);
					AchouArquivo = arquivo.exists();
				} else {
					System.out.println("Url Real Encontrada foi NULL");
				}
			}
			if (!AchouArquivo) {

				System.out.println("Url Real Encontrada mais não existe...");
				System.out.println("Método 5:");
				System.out.println("Arquivo passado:" + reportUrl);
				caminhoRelativo = "resources" + separator + "relatorios"
						+ separator + reportUrl + ".jasper";
				System.out.println("Url Relativa montada:" + caminhoRelativo);
				reportUrlReal = request.getSession().getServletContext()
						.getRealPath(caminhoRelativo);
				System.out.println("NOVA Url Real Encontrada:" + reportUrlReal);
			}

			if (reportUrlReal != null) {
				File arquivo = new File(reportUrlReal);
				AchouArquivo = arquivo.exists();

				if (AchouArquivo)
					System.out
							.println("Após todas as tentativas achou o arquivo!!!");
				else
					System.out
							.println("Após todas as tentativas não achou o arquivo");
			} else {
				System.out
						.println("Após todas as tentativas a URL Real ainda ficou NULL");
			}

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					lista);
			// compilacao do JRXML
			// JasperReport report =
			// JasperCompileManager.compileReport(reportUrlReal);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					reportUrlReal, parametros, ds);

			// JasperPrint jasperPrint = JasperFillManager.fillReport(report,
			// parametros, ds);

			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

			os = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(pdf.length);
			// response.setHeader("Content-disposition","attachment; filename=\""+
			// reportUrl.substring(0, reportUrl.length() - 7)+ ".pdf\"");
			response.setHeader("Content-disposition",
					"attachment; filename=\"Relatorio.pdf");
			os.write(pdf);
		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar gerar o PDF. Mensagem Técnica: "
							+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
				facesContext.responseComplete();
			} catch (IOException e) {
				FacesUtil
						.addError("Ocorreu um erro ao tentar gerar o PDF. Mensagem Técnica: "
								+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
