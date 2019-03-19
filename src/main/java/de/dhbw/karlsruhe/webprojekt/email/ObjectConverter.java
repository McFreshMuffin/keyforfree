package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.xalan.processor.TransformerFactoryImpl;

public class ObjectConverter {

    private final String template = "src//main//resources//template.xsl";

    public void convert(Games game) throws JAXBException, FileNotFoundException, IOException, TransformerException, FOPException {
        File xsltfile = new File(template);
        StringBuilder xml = new StringBuilder();

        xml.append("<header-section>");
        xml.append("<data-type id=\"019\">User Bill Data</data-type>");
        xml.append("<process-date>Thursday December 9 2016 00:04:29</process-date>");
        xml.append("</header-section>");

        JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(game, sw);
        xml.append(xml.toString());

        StreamSource source = new StreamSource(new StringReader(xml.toString()));
        System.out.println("XML --->" + xml.toString());

        
        StreamSource transformSource = new StreamSource(xsltfile);
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Transformer xslfoTransformer;
        try {
            xslfoTransformer = getTransformer(transformSource);
            Fop fop;
            try {
                fop = fopFactory.newFop(MimeConstants.MIME_PDF, outStream);

                Result res = new SAXResult(fop.getDefaultHandler());

                try {
                    xslfoTransformer.transform(source, res);
                    File pdffile = new File("Result.pdf");
                    OutputStream out = new FileOutputStream(pdffile);  // Error is throwing on this line
                    out = new java.io.BufferedOutputStream(out);
                    FileOutputStream str = new FileOutputStream(pdffile);
                    str.write(outStream.toByteArray());
                    str.close();
                    out.close();

                } catch (TransformerException e) {
                    throw e;
                }
            } catch (FOPException e) {
                throw e;
            }
        } catch (TransformerConfigurationException e) {
            throw e;
        } catch (TransformerFactoryConfigurationError e) {
            throw e;
        }
    }

    private static Transformer getTransformer(StreamSource streamSource) {
        TransformerFactoryImpl impl
                = new TransformerFactoryImpl();

        try {
            return impl.newTransformer(streamSource);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    
    private final String RESOURCES_DIR = "src//main//resources//";
    private final String OUTPUT_DIR = "src//main//resources//output//";

    public void convertToXml(Games game) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Games.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(game, sw);
            String xmlContent = sw.toString();
            System.out.println(xmlContent);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void convertXmlToPdf() throws FileNotFoundException, FOPException, TransformerConfigurationException, TransformerException, IOException {
        File xsltFile = new File(RESOURCES_DIR + "//template.xsl");
        StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//xml.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out = new FileOutputStream(OUTPUT_DIR + "//output.pdf");

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}*/
}