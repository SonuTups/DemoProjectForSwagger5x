package com.example.demo;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/schema")
public class refForSchemaTestWithoutOctet {
    @GetMapping(value = "/upload1")
    public ResponseEntity<String> assertExample() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/schemaBodyInOtherFile")
    public ResponseEntity<String> schemaBodyInOtherFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/directSchema")
    public ResponseEntity<String> assertExample1() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }
    @GetMapping(value = "/schemaInCompo")
    public ResponseEntity<String> schemaInCompo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }
    @GetMapping(value = "/schemaInCompo2")
    public ResponseEntity<String> schemaInCompo2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    public static String giveJson(){

        Gson gson = new Gson();

        JsonObject category = new JsonObject();
        category.addProperty("id", 1);
        category.addProperty("name", "BullDog");

        JsonObject tag = new JsonObject();
        tag.addProperty("id", 1);
        tag.addProperty("name", "Tommy");

        JsonObject value = new JsonObject();
        value.addProperty("id", 1234);
        value.add("category", category); // Corrected "catrgory" to "category"
        value.addProperty("name", "Bruno");

        // Create a JSON array for "photoUrls" directly
        JsonArray photoUrls = new JsonArray();
        photoUrls.add("https://germanshepard/burno.jpg");
        value.add("photoUrls", photoUrls);

        // Create a JSON array for "tags" directly
        JsonArray tagsArray = new JsonArray();
        tagsArray.add(tag);
        value.add("tags", tagsArray);

        value.addProperty("status", "available");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("summary", "positive");
        jsonObject.add("value", value);

        JsonObject finalJson = new JsonObject();
        finalJson.add("200",jsonObject);
        // Print the JSON object
        String json = gson.toJson(finalJson);
        System.out.println(json);
        return json;

    }

    @GetMapping(value = "/fluffySchema")
    public ResponseEntity<String> assertSchema() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJsonForSchema(), headers, HttpStatus.OK);
    }
    public static String giveJsonForSchema() {
        Gson gson = new Gson();

        // Create the "value" JSON object
        JsonObject value = new JsonObject();
        value.addProperty("id", 1);
        value.addProperty("name", "Fluffy");
        value.addProperty("status", "available");

        // Print the JSON object
        String json = gson.toJson(value);
        System.out.println(json);
        return json;
    }

    @GetMapping(value = "/getXmlData")
    public ResponseEntity<String> xmlOne() throws ParserConfigurationException, TransformerException {
        return ResponseEntity.ok("<root>\n" +
                "    <id>1</id>\n" +
                "    <name>Bruno</name>\n" +
                "    <photoUrls>\n" +
                "        <photoUrl>https://germanshepard/burno.jpg</photoUrl>\n" +
                "    </photoUrls>\n" +
                "    <tags>\n" +
                "        <tag>\n" +
                "            <id>1</id>\n" +
                "            <name>Tommy</name>\n" +
                "        </tag>\n" +
                "    </tags>\n" +
                "    <status>available</status>\n" +
                "</root>\n");
    }

    @GetMapping(value = "/getXml")
    public ResponseEntity<String> yourMethod() throws ParserConfigurationException, TransformerException {
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

}
