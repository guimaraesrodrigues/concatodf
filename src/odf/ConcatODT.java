package odf;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;
import org.jopendocument.dom.ODSingleXMLDocument;


public class ConcatODT {

    public static void main(String[] args) {
         
        String caminho = new String();           
        File doc = new File(System.getProperty("user.dir") + "/javasources/arquivo_vazio.odt");
        ODSingleXMLDocument odt_doc = null;
        
        try {
            odt_doc = ODSingleXMLDocument.createFromPackage(doc);
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ConcatODT.class.getName()).log(Level.SEVERE, "Erro ao abrir arquivo_vazio.odt!", ex);
        }

        for (int i = 2; i < args.length; i++){
            caminho = args[i];
            doc =  new File(caminho);
            try {                
                ODSingleXMLDocument xmldocument = ODSingleXMLDocument.createFromPackage(doc);
                odt_doc.add(xmldocument, false);
            } catch (JDOMException | IllegalArgumentException | IOException ex) {
                Logger.getLogger(ConcatODT.class.getName()).log(Level.SEVERE, "Erro ao abrir parte de ata: "+ doc.getName(), ex);
            }
        }

        try {         
            odt_doc.saveToPackageAs(new File(args[1]+args[0]));
        } catch (IOException ex) {
            Logger.getLogger(ConcatODT.class.getName()).log(Level.SEVERE, "Erro ao salvar ata montada: "+odt_doc.asString(), ex);
        }            
    }
}
