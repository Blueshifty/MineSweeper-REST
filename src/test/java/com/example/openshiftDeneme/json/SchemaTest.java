/*package com.example.openshiftDeneme.json;

import org.junit.Test;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaTest {

    @Test
    public void givenInvalidInput() throws ValidationException {
        JSONObject jsonSchema = new JSONObject(
            new JSONTokener(SchemaTest.class.getResourceAsStream("/MoveSchema.json")));
          JSONObject jsonSubject = new JSONObject(
            new JSONTokener(SchemaTest.class.getResourceAsStream("/test1.json")));
       
          Schema schema = SchemaLoader.load(jsonSchema);
          schema.validate(jsonSubject);
    }
    
    @Test
    public void givenValidInput() throws ValidationException {
      JSONObject jsonSchema = new JSONObject(
        new JSONTokener(SchemaTest.class.getResourceAsStream("/MoveSchema.json")));
        JSONObject jsonSubject =  new JSONObject(
          new JSONTokener(SchemaTest.class.getResourceAsStream("/test2.json")));

          Schema schema = SchemaLoader.load(jsonSchema);
          schema.validate(jsonSubject);

    }
}*/
