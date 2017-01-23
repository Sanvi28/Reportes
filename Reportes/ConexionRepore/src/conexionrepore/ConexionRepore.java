/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionrepore;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dam2
 */
public class ConexionRepore {
    public static void main(String[] args) {
       
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/informesdi","root", "campus");
            
            JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile("reporteSesion1.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,conexion);

            JasperViewer viewer = new JasperViewer(jasperPrint);
            
            Exporter exporterPdf = new JRPdfExporter();
            
            exporterPdf.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(new File("reporteSesion1PDF.pdf")));
            SimplePdfExporterConfiguration configurationPdf = new SimplePdfExporterConfiguration();
            exporterPdf.setConfiguration(configurationPdf);
            exporterPdf.exportReport();
            
            
            Exporter exporterHtml = new HtmlExporter();
            exporterHtml.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporterHtml.setExporterOutput(new SimpleHtmlExporterOutput(new File("reporteSesion1HTML.html")));
            SimpleHtmlExporterConfiguration configurationHtml = new SimpleHtmlExporterConfiguration();
            exporterHtml.setConfiguration(configurationHtml);
            exporterHtml.exportReport();

            
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionRepore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionRepore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
                Logger.getLogger(ConexionRepore.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
