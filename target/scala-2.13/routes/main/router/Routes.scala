// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  AuthController_1: controllers.AuthController,
  // @LINE:3
  StudentController_0: controllers.StudentController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    AuthController_1: controllers.AuthController,
    // @LINE:3
    StudentController_0: controllers.StudentController
  ) = this(errorHandler, AuthController_1, StudentController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, AuthController_1, StudentController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """oauth/token""", """controllers.AuthController.token"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/students""", """controllers.StudentController.listAll"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/students""", """controllers.StudentController.create"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/students/""" + "$" + """id<[^/]+>""", """controllers.StudentController.getOne(id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/students/""" + "$" + """id<[^/]+>""", """controllers.StudentController.update(id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/students/""" + "$" + """id<[^/]+>""", """controllers.StudentController.delete(id:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_AuthController_token0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("oauth/token")))
  )
  private[this] lazy val controllers_AuthController_token0_invoker = createInvoker(
    AuthController_1.token,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthController",
      "token",
      Nil,
      "POST",
      this.prefix + """oauth/token""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:3
  private[this] lazy val controllers_StudentController_listAll1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/students")))
  )
  private[this] lazy val controllers_StudentController_listAll1_invoker = createInvoker(
    StudentController_0.listAll,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StudentController",
      "listAll",
      Nil,
      "GET",
      this.prefix + """api/students""",
      """""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_StudentController_create2_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/students")))
  )
  private[this] lazy val controllers_StudentController_create2_invoker = createInvoker(
    StudentController_0.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StudentController",
      "create",
      Nil,
      "PUT",
      this.prefix + """api/students""",
      """""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_StudentController_getOne3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/students/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_StudentController_getOne3_invoker = createInvoker(
    StudentController_0.getOne(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StudentController",
      "getOne",
      Seq(classOf[String]),
      "GET",
      this.prefix + """api/students/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val controllers_StudentController_update4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/students/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_StudentController_update4_invoker = createInvoker(
    StudentController_0.update(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StudentController",
      "update",
      Seq(classOf[String]),
      "POST",
      this.prefix + """api/students/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_StudentController_delete5_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/students/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_StudentController_delete5_invoker = createInvoker(
    StudentController_0.delete(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StudentController",
      "delete",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """api/students/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_AuthController_token0_route(params@_) =>
      call { 
        controllers_AuthController_token0_invoker.call(AuthController_1.token)
      }
  
    // @LINE:3
    case controllers_StudentController_listAll1_route(params@_) =>
      call { 
        controllers_StudentController_listAll1_invoker.call(StudentController_0.listAll)
      }
  
    // @LINE:4
    case controllers_StudentController_create2_route(params@_) =>
      call { 
        controllers_StudentController_create2_invoker.call(StudentController_0.create)
      }
  
    // @LINE:5
    case controllers_StudentController_getOne3_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_StudentController_getOne3_invoker.call(StudentController_0.getOne(id))
      }
  
    // @LINE:6
    case controllers_StudentController_update4_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_StudentController_update4_invoker.call(StudentController_0.update(id))
      }
  
    // @LINE:7
    case controllers_StudentController_delete5_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_StudentController_delete5_invoker.call(StudentController_0.delete(id))
      }
  }
}
