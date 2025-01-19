package models

import org.mongodb.scala.bson.ObjectId
import play.api.libs.json._

case class Student(
  _id: Option[String],
  lastName: String,
  firstName: String,
  middleName: String,
  group: String,
  gpa: Double
)

object Student {
  implicit val studentFormat: OFormat[Student] = Json.format[Student]
}
