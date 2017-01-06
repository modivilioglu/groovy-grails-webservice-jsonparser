package jsontotext
import groovyx.net.http.RESTClient
import grails.converters.*
import groovyx.net.http.HttpResponseException

class FormatconverterController {

	// Configured in UrlMappings.groovy, and mapped with ONLY GET HTTP Method!
    def convert() { 
    	try {

    		// Get The Request
    		// URL is already configured in Config.groovy as a property    		
	    	def jsonRequest = new RESTClient(grailsApplication.config.bleepURL)

	    	// The default is parameter in params is already named as id
	    	def queryString = params.id
	    	def response = jsonRequest.get(path: queryString)

	    	// "response" should be converted to Groovy Object from JSON format
	    	def responseObject  =  JSON.parse(response.data.toString())

	    	// Titles of the colums
	    	def titles = ["class", 
		    	"id", 
		    	"mediaFormatType", 
		    	"mediaFormatKind",
				"shortDescription",
				"purchaseable",
				"prices-gbp",
				"prices-usd",
				"prices-eur",
				"oprices-gbp",
				"oprices-usd",
				"oprices-eur",
				"sale"]

			// Adding tab delimeter between titles (The first row)
			def returnString = titles.join("\t")

			// For each item in releaseFormats list, 1) we iterate, 2) create a row with its values, 
			// 3) seperate each value in the row by tab delimeter, 4) seperate each row with LineFeed delimeter
			responseObject.item.releaseFormats.each {
				def row = [	it.class,
							it.id,
							it.mediaFormatType,
							it.mediaFormatKind,
							it.shortDescription,
							it.purchaseable,
							it.prices.gbp,
							it.prices.usd,
							it.prices.eur,
							it.oprices.gbp,
							it.oprices.usd,
							it.oprices.eur,
							it.sale]
				returnString += "\n" + row.join("\t");				

			}
			// Return the response in text/plain content
	 		render(contentType: "text/plain", encoding:"utf-8", text: returnString)
	 	}
	 	catch (HttpResponseException cpe) {
	 		render "Please enter a valid id or configure a valid URL. ERROR. Message: " + cpe.message
	 	}
	 	catch (Exception e) {
	 		render "ERROR. Message: " + e.message
	 	}
    }
}
