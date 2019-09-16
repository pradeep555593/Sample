package com.TestPackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ObjectToJson {
    public static void main( String[] args ) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Define map which will be converted to JSON

        ArrayList<String> purl = new ArrayList<>();
        ArrayList<Tag> t = new ArrayList<>();
        t.add(new Tag(34, "harvey"));
        purl.add("url1");
        purl.add("url2");
        Input ip = new Input();
        ip.setid(Long.valueOf(0));
        ip.setName("pitbull");
        ip.setCategory(new Category(0,"cat"));
        ip.setPhotoUrls(purl);
        ip.setTags(t);

        /*Tag[] t = Stream.of(
                new Tag(34, "harvey"))
                .toArray(Tag[]::new);*/
        ip.setStatus("available");


        //1. Convert Array to JSON
        //String arrayToJson = objectMapper.writeValueAsString(t);
        String Json = objectMapper.writeValueAsString(ip);
        System.out.println("1. Convert Array to JSON :");
        System.out.println(Json);


    }


}
