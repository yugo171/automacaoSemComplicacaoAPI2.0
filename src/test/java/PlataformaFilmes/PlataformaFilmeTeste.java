package PlataformaFilmes;


import Maps.LoginMap;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Restutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.*;
public class PlataformaFilmeTeste {


    @Test
    public void ValidarLogin(){

        Restutils.setBaseURI("http://localhost:8080");

        String json = "{\"email\":\"aluno@email.com\",\"senha\":\"123456\"}";

        /*json = json.replace("123456", "789");
        * FAZ A ALTERÇÃO DA SENHA
        * */

        Response response = Restutils.post(json, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        LoginMap.token = response.jsonPath().get("token");
    }

    @BeforeAll
    public static void validarLoginMap(){
        Restutils.setBaseURI("http://localhost:8080");
        LoginMap.initLogin();

        /*map.put("senha", "789");*/
        /*map.remove("senha");*/

        Response response = Restutils.post(LoginMap.getLogin(), ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        LoginMap.token = response.jsonPath().get("token");
        System.out.println(response.jsonPath().get().toString());


    }

    @Test
    public void validarContultoriasCategorias(){
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+LoginMap.token);

        Response response = Restutils.get(header, "categorias");
        assertEquals(200, response.statusCode());


        System.out.println(response.jsonPath().get().toString());

        assertEquals("Terror",response.jsonPath().get("tipo[2]"));

        List<String> listTipo = response.jsonPath().get("tipo");
        assertTrue(listTipo.contains("Terror"),"Não foi encontrado a categoria Terror na lista de categorias");



    }
}
