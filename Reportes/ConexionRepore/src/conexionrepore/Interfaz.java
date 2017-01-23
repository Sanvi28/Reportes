/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionrepore;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Interfaz extends JFrame{
    
    JTextArea area;
    
    public Interfaz(){
        iniciarGUI();
    }
    
    public void iniciarGUI(){
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        
        area = new JTextArea();
        
        File fichero = new File("reporteSesion1HTML.html");
        
    }
    
}
