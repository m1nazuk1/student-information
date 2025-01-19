// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:2
package controllers {

  // @LINE:3
  class ReverseStudentController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def create: Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "api/students")
    }
  
    // @LINE:3
    def listAll: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/students")
    }
  
    // @LINE:5
    def getOne(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/students/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:7
    def delete(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "api/students/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:6
    def update(id:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "api/students/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
  }

  // @LINE:2
  class ReverseAuthController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def token: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "oauth/token")
    }
  
  }


}
