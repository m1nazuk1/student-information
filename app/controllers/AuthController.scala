package controllers

import play.api.mvc._
import play.api.libs.json._
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import pdi.jwt._
import pdi.jwt.JwtAlgorithm
import pdi.jwt.JwtJson
import java.time.Instant
import java.time.temporal.ChronoUnit
import play.api.Configuration

@Singleton
class AuthController @Inject()(cc: ControllerComponents, config: Configuration)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  def token: Action[AnyContent] = Action.async { request =>
    val maybeClientId = request.body.asFormUrlEncoded.flatMap(_.get("client_id").flatMap(_.headOption))
    val maybeClientSecret = request.body.asFormUrlEncoded.flatMap(_.get("client_secret").flatMap(_.headOption))
    val maybeUsername = request.body.asFormUrlEncoded.flatMap(_.get("username").flatMap(_.headOption))
    val maybePassword = request.body.asFormUrlEncoded.flatMap(_.get("password").flatMap(_.headOption))
    if (maybeClientId.contains("testclient") && maybeClientSecret.contains("testsecret") && maybeUsername.contains("user") && maybePassword.contains("pass")) {
      val secretKey = config.get[String]("jwt.secret")
      val now = Instant.now()
      val expires = now.plus(1, ChronoUnit.HOURS)
      val claim = Json.obj(
        "sub" -> maybeUsername.get,
        "iat" -> now.getEpochSecond,
        "exp" -> expires.getEpochSecond
      )
      val token = JwtJson.encode(claim, secretKey, JwtAlgorithm.HS256)
      Future.successful(Ok(Json.obj("access_token" -> token, "token_type" -> "Bearer")))
    } else Future.successful(Unauthorized(Json.obj("error" -> "invalid_credentials")))
  }
}
