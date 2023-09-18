package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@RestController
public class Return_Xml_data {

    @PostMapping(value = "/getXml5", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> myMethod(@RequestBody byte[] binaryData) throws ParserConfigurationException, TransformerException {
        // Create an XML document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Create the root element
        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);

        // Add elements and text nodes
        rootElement.appendChild(createElementWithText(doc, "photoUrls", "https://germanshepard/burno.jpg"));
        rootElement.appendChild(createElementWithText(doc, "name", "Bruno"));
        rootElement.appendChild(createElementWithText(doc, "id", "1"));

        Element tagsElement = doc.createElement("tags");
        rootElement.appendChild(tagsElement);
        tagsElement.appendChild(createElementWithText(doc, "name", "Tommy"));
        tagsElement.appendChild(createElementWithText(doc, "id", "1"));

        rootElement.appendChild(createElementWithText(doc, "status", "available"));

        // Convert the XML document to a string
        String xmlContent = convertDocumentToString(doc);

        // Return the XML content as a ResponseEntity
        return ResponseEntity.ok(xmlContent);
    }
    @PostMapping(value = "/getXml", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> yourMethod(@RequestBody byte[] binaryData) throws ParserConfigurationException, TransformerException {
        // Create an XML document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Create the root element
        Element rootElement = doc.createElement("response");
        doc.appendChild(rootElement);

        // Create a sub-element for the "200" key
        Element status200 = doc.createElement("status200");
        rootElement.appendChild(status200);

        // Add elements and text nodes under "status200" for the JSON data
        status200.appendChild(createElementWithText(doc, "summary", "positive"));

        Element value = doc.createElement("value");
        status200.appendChild(value);

        value.appendChild(createElementWithText(doc, "id", "1234"));

        Element category = doc.createElement("category");
        value.appendChild(category);

        category.appendChild(createElementWithText(doc, "id", "1"));
        category.appendChild(createElementWithText(doc, "name", "BullDog"));

        value.appendChild(createElementWithText(doc, "name", "Bruno"));

        Element photoUrls = doc.createElement("photoUrls");
        value.appendChild(photoUrls);

        // Add multiple "photoUrl" elements under "photoUrls"
        for (String url : new String[]{"https://germanshepard/burno.jpg"}) {
            photoUrls.appendChild(createElementWithText(doc, "photoUrl", url));
        }

        Element tags = doc.createElement("tags");
        value.appendChild(tags);

        Element tag = doc.createElement("tag");
        tags.appendChild(tag);

        tag.appendChild(createElementWithText(doc, "id", "1"));
        tag.appendChild(createElementWithText(doc, "name", "Tommy"));

        value.appendChild(createElementWithText(doc, "status", "available"));

        // Convert the XML document to a string
        String xmlContent = convertDocumentToString(doc);

        // Return the XML content as a ResponseEntity
        return ResponseEntity.ok(xmlContent);
    }

    private Element createElementWithText(Document doc, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        return element;
    }

    private String convertDocumentToString(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.toString();
    }

    @PostMapping(value = "/getXml2", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> yourMethod1(@RequestBody byte[] binaryData) throws ParserConfigurationException, TransformerException {
        // Create an XML document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Create the root element
        Element rootElement = doc.createElement("response");
        doc.appendChild(rootElement);

        // Create a sub-element for the "message" and "data" fields
        Element messageElement = doc.createElement("message");
        rootElement.appendChild(messageElement);
        messageElement.appendChild(doc.createTextNode("Complex JSON response example"));

        Element dataElement = doc.createElement("data");
        rootElement.appendChild(dataElement);

        // Add "id", "name", and "tags" elements under "data"
        Element idElement = doc.createElement("id");
        dataElement.appendChild(idElement);
        idElement.appendChild(doc.createTextNode("123"));

        Element nameElement = doc.createElement("name");
        dataElement.appendChild(nameElement);
        nameElement.appendChild(doc.createTextNode("Sample Item"));

        Element tagsElement = doc.createElement("tags");
        dataElement.appendChild(tagsElement);

        // Add "tag" elements under "tags"
        Element tag1Element = doc.createElement("tag");
        tagsElement.appendChild(tag1Element);
        tag1Element.appendChild(doc.createTextNode("tag1"));

        Element tag2Element = doc.createElement("tag");
        tagsElement.appendChild(tag2Element);
        tag2Element.appendChild(doc.createTextNode("tag2"));

        // Convert the XML document to a string
        String xmlContent = convertDocumentToString(doc);

        // Return the XML content as a ResponseEntity with proper XML content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
    }


    @PostMapping(value = "/getXml3", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> yourMethod1() throws ParserConfigurationException, TransformerException {
        // Create an XML document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Create the root element
        Element libraryElement = doc.createElement("library");
        doc.appendChild(libraryElement);

        // Create book elements
        Element book1Element = createBookElement(doc, "The Great Gatsby", "F. Scott Fitzgerald", "1925");
        libraryElement.appendChild(book1Element);

        Element book2Element = createBookElement(doc, "To Kill a Mockingbird", "Harper Lee", "1960");
        libraryElement.appendChild(book2Element);

        // Convert the XML document to a string
        String xmlContent = convertDocumentToString(doc);

        // Return the XML content as a ResponseEntity with proper XML content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
    }

    private Element createBookElement(Document doc, String title, String author, String publicationYear) {
        Element bookElement = doc.createElement("book");

        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode(title));
        bookElement.appendChild(titleElement);

        Element authorElement = doc.createElement("author");
        authorElement.appendChild(doc.createTextNode(author));
        bookElement.appendChild(authorElement);

        Element publicationYearElement = doc.createElement("publication_year");
        publicationYearElement.appendChild(doc.createTextNode(publicationYear));
        bookElement.appendChild(publicationYearElement);

        return bookElement;
    }

}
