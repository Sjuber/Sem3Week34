package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;


class Country {
    String name;
    String borders;

    public Country(String name, String borders) {
        this.name = name;
        this.borders = borders;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }
    
    
    
}

@Path("demo")
public class DemoClass {

    @Context
    private UriInfo context;
    
   private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   
    public DemoClass() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "[]";
    }
    
    @Path("numbers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        return "[123,321]";
    }

        @Path("objectdemo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        Country c = new Country("Denmark","Germany");
        String jsonString = GSON.toJson(c);
    return jsonString;
}
    
}
