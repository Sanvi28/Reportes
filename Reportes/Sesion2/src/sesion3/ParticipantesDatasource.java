/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author dam2
 */
public class ParticipantesDatasource implements JRDataSource {

    private List<Participante> listaParticipantes = new ArrayList<>();
    private int contadorParticipante = -1;

    public void addParticipante(Participante participante) {
        this.listaParticipantes.add(participante);
    }

    @Override
    public boolean next() throws JRException {
        return ++contadorParticipante < listaParticipantes.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("nombre".equals(jrf.getName())) {
            valor = listaParticipantes.get(contadorParticipante).getNombre();
        }else if("id".equals(jrf.getName())){
            valor = listaParticipantes.get(contadorParticipante).getId();
        } else if ("userName".equals(jrf.getName())) {
            valor = listaParticipantes.get(contadorParticipante).getUserName();
        } else if ("password".equals(jrf.getName())) {
            valor = listaParticipantes.get(contadorParticipante).getPassword();
        } else if ("comentarios".equals(jrf.getName())) {
            valor = listaParticipantes.get(contadorParticipante).getComentarios();
        } else if ("puntos".equals(jrf.getName())) {
            valor = listaParticipantes.get(contadorParticipante).getPuntos();
        }
        return valor;
    }

    public static void main(String[] args) {
        ParticipantesDatasource datasource = new ParticipantesDatasource();

        for (int i = 1; i <= 13; i++) {
            Participante p = new Participante(i, "Particpante " + i, "Usuario " + i, "Pass " + i, i ,"Comentarios para " + i);
            
            p.setPuntos(i);
            
            datasource.addParticipante(p);
        }

        try {
            //Creamos un Map con los parametros así no le pediremos nada al usuario
            //Clave Valor
            Map<String, Object> parametrosMap = new HashMap<String, Object>();
            parametrosMap.put("autor", "Sandra Villena");
            parametrosMap.put("titulo", "Reporte Participantes Sesion 3");

            
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reporteSesion3.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametrosMap, datasource);
            
            Exporter exporterPDF = new JRPdfExporter();

            exporterPDF.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporterPDF.setExporterOutput(new SimpleOutputStreamExporterOutput(new java.io.File("reporteSesion3PuntossPDF.pdf")));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporterPDF.setConfiguration(configuration);

            exporterPDF.exportReport();
        } catch (JRException ex) {

            Logger.getLogger(ParticipantesDatasource.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
