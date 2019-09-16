
import com.TestPackage.Category;
import com.TestPackage.Input;
import com.TestPackage.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;



public class TestAPITest {

    ObjectMapper objectMapper = new ObjectMapper();
    public Input json;

    @BeforeMethod
    public void setUp() throws JsonProcessingException {
        RestAssured.baseURI = Constants.baseURL;
        //RequestSpecification httpRequest = RestAssured.given();
        //Response tknResp = httpRequest.auth().oauth2(baseURI+"oauth/authorize","test")
                //request(Method.POST,"/oauth/authorize");
    }

    @AfterMethod
    public void tearDown() {

    }

    @Test
    public void PetStore(){

        //Create PET
        /*String PostPet = "{ "+
                "\"id\": "+0+","+
                "\"category\": { \"id\": "+0+", \"name\" : \"dog\" },"+
                "\"name\" : \"ScoobyDoo\","+
                "\"photoUrls\" : [ \"url1\" ],"+
                "\"tags\" : [{ \"id\":"+123+", \"name\": \"Indian breed\"}],"+
                "\"status\": \"available\""+
                "}";*/
        json = new Input();
        json = getObject(json);
        String PostPet = "";
        try {
            PostPet = objectMapper.writeValueAsString(json);
            Response respPostPet = given().
                    basePath("pet").
                    contentType("application/json").
                    body(PostPet).
                    post();
            System.out.println("Response after Postpet: "+respPostPet.body().asString());
            Assert.assertEquals(respPostPet.getStatusCode(), 200);
            Constants.iPet = respPostPet.path("id");
            Constants.strName = respPostPet.path("name").toString();
            System.out.println(Constants.iPet+" "+ Constants.strName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //Find By Status
        findbyStatus(Constants.iPet,Constants.strName,"available");
        //PUT
        putPET(Constants.iPet,"Bull dog");
        //POST pet
        if(postPET(Constants.iPet,"update")){

            System.out.println("Pet Updated");
        }
        //Find By Status
        findbyStatus(Constants.iPet,"Bull Dog","available");
        //Delete Pet
        deletPET(Constants.iPet);
        if(postPET(Constants.iPet,"delete")){
            System.out.println("Pet Deleted");
        }


    }
    //Find By Status
    public void findbyStatus(Long petID,String petName,String petStatus){
        Response respGetByStatus = given().
                basePath("pet/findByStatus").
                queryParam("status",petStatus).
                get();
        String resp = respGetByStatus.body().asString();
        System.out.println("Response after findbystatus: "+resp);
        Assert.assertEquals(respGetByStatus.getStatusCode(), 200);
        JsonPath jp = respGetByStatus.jsonPath();
        List<Long> ids = jp.getList("id");
        //System.out.println(ids.size());
        if(ids.contains(Long.valueOf(Constants.iPet))){
                System.out.println("Pet found");
            }
    }

    //PUT
    public void putPET(Long petid,String petNewName) {

        /*String PostPet = "{ " +
                "\"id\": " + petid + "," +
                "\"category\": { \"id\": " + 0 + ", \"name\" : \"dog\" }," +
                "\"name\" : \""+petNewName+"\","+
                "\"photoUrls\" : [ \"https://www.google.com/dogimage.jpeg\" ]," +
                "\"tags\" : [{ \"id\":" + 123 + ", \"name\": \"Indian breed\"}]," +
                "\"status\": \"available\"" +
                "}";*/
        Input json = new Input();
        json = getObjectUpdated(json,petid,petNewName);
        String PostPet = "";
        try {
            PostPet = objectMapper.writeValueAsString(json);
            Response respPostPet = given().
                    basePath("pet").
                    contentType("application/json").
                    body(PostPet).
                    put();
            System.out.println("Response after PUTpet: " + respPostPet.body().asString());
            Assert.assertEquals(respPostPet.getStatusCode(), 200);
            Constants.iPet = respPostPet.path("id");
            Constants.strName = respPostPet.path("name").toString();
            System.out.println(Constants.iPet + " " + Constants.strName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    //POST pet
    public boolean postPET(Long  petid, String Type){
        boolean res = false;
        Response respDelPet = given().
                basePath("pet/"+petid).
                post();
        if (Type.equalsIgnoreCase("update")) {
            Assert.assertEquals(respDelPet.getStatusCode(), 200);
            res = true;
        }else{
            Assert.assertEquals(respDelPet.getStatusCode(), 404);
            res = true;
        }
        return  res;
    }
    //Delete Pet
    public void deletPET(Long  petid){
        Response respDelPet = given().
                basePath("pet/"+petid).
                delete();
        Assert.assertEquals(respDelPet.getStatusCode(), 200);

    }


    public Input getObject(Input ip){

        ArrayList<String> purl = new ArrayList<>();
        ArrayList<Tag> t = new ArrayList<>();
        t.add(new Tag(34, "pdf"));
        purl.add("url1");
        purl.add("url2");
        ip.setid(Long.valueOf(0));
        ip.setName("tom");
        ip.setCategory(new Category(0,"cat"));
        ip.setPhotoUrls(purl);
        ip.setTags(t);
        ip.setStatus("available");

        return  ip;

    }

    public Input getObjectUpdated(Input ip,Long petId, String petName){

        ArrayList<String> purl = new ArrayList<>();
        ArrayList<Tag> t = new ArrayList<>();
        t.add(new Tag(34, "pdf"));
        purl.add("url3");
        purl.add("url4");
        ip.setid(petId);
        ip.setName(petName);
        ip.setCategory(new Category(0,"cat"));
        ip.setPhotoUrls(purl);
        ip.setTags(t);
        ip.setStatus("available");

        return  ip;

    }
}