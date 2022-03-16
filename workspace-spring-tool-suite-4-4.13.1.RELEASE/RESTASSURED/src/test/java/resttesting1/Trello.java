package resttesting1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Trello {
	public static String baseurl = "https://api.trello.com";
	public static String id;
	
	@Test(priority = 0)
	
	public void createBoard()
	{
		RestAssured.baseURI = baseurl;
		Response response = given().queryParam("name", "Sahilmane board")
		.queryParam("key", "1eed83918185adfcfa45037743b0b17a")
		.queryParam("token", "b026081fc83014b37aa9a103fe738160535af8bb7dd789c1b992eb9c334cc9c6")
		.header("Content-Type", "application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		JsonPath js = new JsonPath(jsonresponse);
		id = js.get("id");
}

	//if i make any @Test method enabled =false/ that method will not execute
	
		@Test(priority = 1)
		public void getBoard()
		{
			RestAssured.baseURI = baseurl;
			
			
		Response response =	given()
			.queryParam("key", "1eed83918185adfcfa45037743b0b17a")
			.queryParam("token", "b026081fc83014b37aa9a103fe738160535af8bb7dd789c1b992eb9c334cc9c6")
			.header("Content-Type","application/json")
			
			.when()
			.get("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
			String jsonresponse = response.asString();
			
			System.out.println(jsonresponse);
		}
		
	@Test(priority = 2)
		public void DeleteBoard()
		{
			RestAssured.baseURI = baseurl;
			
			
		Response response =	given()
			.queryParam("key", "1eed83918185adfcfa45037743b0b17a")
			.queryParam("token", "b026081fc83014b37aa9a103fe738160535af8bb7dd789c1b992eb9c334cc9c6")
			.header("Content-Type","application/json")
			
			.when()
			.delete("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
			String jsonresponse = response.asString();
			
			System.out.println(jsonresponse);
	}
	}


