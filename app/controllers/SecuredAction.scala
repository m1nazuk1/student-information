package controllers
import play.api.mvc.Results.Unauthorized
import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import pdi.jwt.{JwtJson, JwtOptions, JwtAlgorithm}
import play.api.libs.json._
import java.time.Instant
import play.api.Configuration
import scala.util.{Success, Failure}

class SecuredAction @Inject()(parser: BodyParsers.Default, cc: ControllerComponents, config: Configuration)(implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser) {

  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
    val maybeAuthHeader = request.headers.get("Authorization")
    maybeAuthHeader match {
      case Some(headerValue) if headerValue.startsWith("Bearer ") =>
        val token = headerValue.replace("Bearer ", "")
        val secretKey = config.get[String]("jwt.secret")
        JwtJson.decodeJson(token, secretKey, Seq(JwtAlgorithm.HS256), JwtOptions(signature = true, expiration = true)) match {
          case Success(claim) =>
            val exp = (claim \ "exp").as[Long]
            if (exp < Instant.now().getEpochSecond)
              Future.successful(Unauthorized(Json.obj("error" -> "token_expired")))
            else block(request)
          case Failure(_) =>
            Future.successful(Unauthorized(Json.obj("error" -> "invalid_token")))
        }
      case _ =>
        Future.successful(Unauthorized(Json.obj("error" -> "no_token_provided")))
    }
  }
}
