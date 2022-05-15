package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.CustomerModel;

@Path("/Service")
public class service {
CustomerModel cusobj = new CustomerModel();
	
	@POST
	 @Path("/insert") 
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String insertCusService( @FormParam("Name") String Name, 
	  @FormParam("Address") String Address, @FormParam("Issue") String Issue, 
	  @FormParam("TelNo") String TelNo, @FormParam("Status") String Status) 
	 { 
	  String output = cusobj.insertCusService(Name, Address, Issue, TelNo, Status); 
	 return output; 
	 }
	
	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCusService() 
	 { 
	 return cusobj.readCusService(); 
	 } 
 	 
	@PUT
	@Path("/cusupdate") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCusService(String cusData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject cusObject = new JsonParser().parse(cusData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String Id = cusObject.get("Id").getAsString(); 
	 String Name = cusObject.get("Name").getAsString(); 
	 String Address = cusObject.get("Address").getAsString();
	 String Issue = cusObject.get("Issue").getAsString();
	 String TelNo = cusObject.get("TelNo").getAsString();
	 String Status = cusObject.get("Status").getAsString(); 
	 
	 String output = cusobj.updateCusService(Id,Name,Address,Issue,TelNo,Status); 
	return output; 
	}
	


	
	
	
	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCusService(String cusData) {
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(cusData, "", Parser.xmlParser());
		
		//Read the Values from the element<Name>
		String Name = doc.select("Id").text();
		String output = cusobj.deleteCusService(Name);
		return output;
	}


}
