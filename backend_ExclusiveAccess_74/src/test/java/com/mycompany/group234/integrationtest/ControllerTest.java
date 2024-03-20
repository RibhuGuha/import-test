package com.mycompany.group234.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.group234.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/ExclusiveAccess/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/ExclusiveAccess/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("ExclusiveAccess", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreatePackageManagementInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PackageManagementInstance.json"))
        .when()
        .post("/ExclusiveAccess/PackageManagements")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPackageManagement() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PackageManagementInstance.json"))
        .when()
        .post("/ExclusiveAccess/PackageManagements")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/PackageManagements?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Id", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/PackageManagements/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateMenu_NavInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("Menu_NavInstance.json"))
        .when()
        .post("/ExclusiveAccess/Menu_Navs")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsMenu_Nav() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("Menu_NavInstance.json"))
        .when()
        .post("/ExclusiveAccess/Menu_Navs")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/Menu_Navs?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).MenuId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/Menu_Navs/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateArchitectureInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ArchitectureInstance.json"))
        .when()
        .post("/ExclusiveAccess/Architectures")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsArchitecture() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ArchitectureInstance.json"))
        .when()
        .post("/ExclusiveAccess/Architectures")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/Architectures?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Id", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/Architectures/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateServerInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ServerInstance.json"))
        .when()
        .post("/ExclusiveAccess/Servers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsServer() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ServerInstance.json"))
        .when()
        .post("/ExclusiveAccess/Servers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/Servers?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Id", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/Servers/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateUIFieldInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("UIFieldInstance.json"))
        .when()
        .post("/ExclusiveAccess/UIFields")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsUIField() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("UIFieldInstance.json"))
        .when()
        .post("/ExclusiveAccess/UIFields")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/UIFields?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).FieldId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/UIFields/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFrontendAppMetaTagsInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FrontendAppMetaTagsInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendAppMetaTags")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFrontendAppMetaTags() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FrontendAppMetaTagsInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendAppMetaTags")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/FrontendAppMetaTags?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Id", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/FrontendAppMetaTags/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateMenuItemInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("MenuItemInstance.json"))
        .when()
        .post("/ExclusiveAccess/MenuItems")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsMenuItem() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("MenuItemInstance.json"))
        .when()
        .post("/ExclusiveAccess/MenuItems")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/MenuItems?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).MenuItemId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/MenuItems/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDocumentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/ExclusiveAccess/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDocument() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/ExclusiveAccess/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/Documents?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DocId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/Documents/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateUICompInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("UICompInstance.json"))
        .when()
        .post("/ExclusiveAccess/UIComps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsUIComp() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("UICompInstance.json"))
        .when()
        .post("/ExclusiveAccess/UIComps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/UIComps?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CompId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/UIComps/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateUITemplateInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("UITemplateInstance.json"))
        .when()
        .post("/ExclusiveAccess/UITemplates")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsUITemplate() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("UITemplateInstance.json"))
        .when()
        .post("/ExclusiveAccess/UITemplates")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/UITemplates?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).TemplateId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/UITemplates/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFrontendScreenInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FrontendScreenInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendScreens")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFrontendScreen() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FrontendScreenInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendScreens")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/FrontendScreens?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).FeScreenId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/FrontendScreens/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBuildToolInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BuildToolInstance.json"))
        .when()
        .post("/ExclusiveAccess/BuildTools")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBuildTool() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BuildToolInstance.json"))
        .when()
        .post("/ExclusiveAccess/BuildTools")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/BuildTools?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Id", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/BuildTools/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateAppConfigurationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("AppConfigurationInstance.json"))
        .when()
        .post("/ExclusiveAccess/AppConfigurations")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsAppConfiguration() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("AppConfigurationInstance.json"))
        .when()
        .post("/ExclusiveAccess/AppConfigurations")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/AppConfigurations?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CAppId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/AppConfigurations/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFrontendAppSelectedScreenIdsInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FrontendAppSelectedScreenIdsInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendAppSelectedScreenIds")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFrontendAppSelectedScreenIds() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FrontendAppSelectedScreenIdsInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendAppSelectedScreenIds")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/FrontendAppSelectedScreenIds?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Id", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/FrontendAppSelectedScreenIds/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFrontendAppInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FrontendAppInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendApps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFrontendApp() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FrontendAppInstance.json"))
        .when()
        .post("/ExclusiveAccess/FrontendApps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/FrontendApps?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).AppId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/FrontendApps/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateHeaderBarInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("HeaderBarInstance.json"))
        .when()
        .post("/ExclusiveAccess/HeaderBars")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsHeaderBar() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("HeaderBarInstance.json"))
        .when()
        .post("/ExclusiveAccess/HeaderBars")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/HeaderBars?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).HeaderId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/HeaderBars/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateTemplateScreenInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("TemplateScreenInstance.json"))
        .when()
        .post("/ExclusiveAccess/TemplateScreens")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsTemplateScreen() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("TemplateScreenInstance.json"))
        .when()
        .post("/ExclusiveAccess/TemplateScreens")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/ExclusiveAccess/TemplateScreens?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).TeScreenId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/ExclusiveAccess/TemplateScreens/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.PackageManagement");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.Menu_Nav");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.Architecture");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.Server");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.UIField");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendAppMetaTags");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.MenuItem");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.Document");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.UIComp");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.UITemplate");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendScreen");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.BuildTool");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.AppConfiguration");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendAppSelectedScreenIds");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendApp");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.HeaderBar");
    jdbcTemplate.execute("DELETE FROM ExclusiveAccess.TemplateScreen");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.UICompFields");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.Menu_NavItems");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendAppSelectedScreens");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendScreenUIComponents");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.UITemplateAllScreens");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendAppMetaTags");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.FrontendAppSelectedScreenIds");
     jdbcTemplate.execute("DELETE FROM ExclusiveAccess.HeaderBarFields");

    RestAssuredMockMvc.reset();
  }
}
