class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/formatconverter/$id"(controller:"formatconverter") {
       		action = [GET:"convert"/*, PUT:"update", DELETE:"delete", POST:"save"*/]
       	}
	}
}
