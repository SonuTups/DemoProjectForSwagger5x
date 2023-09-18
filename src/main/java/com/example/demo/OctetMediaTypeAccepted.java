package com.example.demo;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OctetMediaTypeAccepted {

    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE,
    produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> uploadBinaryFile(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/upload1", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> assertExample(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        System.out.println("Received binary file with size: " + dataSize + " bytes");
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/schemaBodyInOtherFile", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> schemaBodyInOtherFile(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        System.out.println("Received binary file with size: " + dataSize + " bytes");
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }
    @PostMapping(value = "/schemaInCompo", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> schemaInCompo(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        System.out.println("Received binary file with size: " + dataSize + " bytes");
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }
    @PostMapping(value = "/schemaInCompo2", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> schemaInCompo2(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        System.out.println("Received binary file with size: " + dataSize + " bytes");
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }
    @PostMapping(value = "/directSchema", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> assertExample1(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        System.out.println("Received binary file with size: " + dataSize + " bytes");
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/schemaAssert", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> assertSchema(@RequestBody byte[] binaryData) {
        int dataSize = binaryData.length;
        System.out.println("Received binary file with size: " + dataSize + " bytes");
        String jsonData = "{\"message\": \"Binary file uploaded successfully!\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJsonForSchema(), headers, HttpStatus.OK);
    }
    @PostMapping(value = "/textdata", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String getTextData(@RequestBody byte[] binaryData) {
        System.out.println("Received binary file with size: " + binaryData.length + " bytes");
        return "\"I am plain text and I got your octet parcel\"";
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


    @GetMapping(value = "/IamRefSchemaFromCompo1")
    public ResponseEntity<String> IamRefSchemaFromCompo1() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/IamRefSchemaFromCompo2")
    public ResponseEntity<String> IamRefSchemaFromCompo2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJson(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/IamDirectSchema1")
    public ResponseEntity<String> IamDirectSchema() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJsonForSchema(), headers, HttpStatus.OK);
    }
    @GetMapping(value = "/IamDirectSchema2")
    public ResponseEntity<String> IamDirectSchema2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(giveJsonForSchema(), headers, HttpStatus.OK);
    }
    @GetMapping(value = "/IamRefSchemaFromCompoXML")
    public ResponseEntity<String> IamRefSchemaFromCompoXML() {

        return new ResponseEntity<>("We do not support xml schema assertion", HttpStatus.OK);
    }


}
