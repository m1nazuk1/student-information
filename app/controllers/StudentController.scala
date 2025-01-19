package controllers

import play.api.mvc._
import services.StudentService
import models.Student
import play.api.libs.json._
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class StudentController @Inject()(
  cc: ControllerComponents,
  studentService: StudentService,
  securedAction: SecuredAction
)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def listAll: Action[AnyContent] = securedAction.async {
    studentService.listAll.map(students => Ok(Json.toJson(students)))
  }

  def getOne(id: String): Action[AnyContent] = securedAction.async {
    studentService.getOne(id).map {
      case Some(student) => Ok(Json.toJson(student))
      case None => NotFound(Json.obj("error" -> "not_found"))
    }
  }

  def create: Action[JsValue] = securedAction.async(parse.json) {
    request =>
      request.body.validate[Student] match {
        case JsSuccess(studentData, _) =>
          studentService.create(studentData.copy(_id = None)).map { newId =>
            Created(Json.obj("id" -> newId))
          }
        case JsError(errors) =>
          Future.successful(BadRequest(Json.obj("error" -> "invalid_data")))
      }
  }

  def update(id: String): Action[JsValue] = securedAction.async(parse.json) {
    request =>
      request.body.validate[Student] match {
        case JsSuccess(studentData, _) =>
          studentService.update(id, studentData.copy(_id = Some(id))).map {
            case true => Ok(Json.obj("status" -> "updated"))
            case false => NotFound(Json.obj("error" -> "not_found"))
          }
        case JsError(_) =>
          Future.successful(BadRequest(Json.obj("error" -> "invalid_data")))
      }
  }

  def delete(id: String): Action[AnyContent] = securedAction.async {
    studentService.delete(id).map {
      case true => Ok(Json.obj("status" -> "deleted"))
      case false => NotFound(Json.obj("error" -> "not_found"))
    }
  }
}
