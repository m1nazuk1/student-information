package repository

import javax.inject.Inject
import javax.inject.Named
import models.Student
import org.mongodb.scala.MongoClient
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.ReplaceOptions
import org.mongodb.scala.bson.ObjectId
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class StudentRepository @Inject()(mongoClient: MongoClient, @Named("dbName") dbName: String)(implicit ec: ExecutionContext) {
  private val database: MongoDatabase = mongoClient.getDatabase(dbName)
  private val collection: MongoCollection[org.mongodb.scala.bson.Document] = database.getCollection("students")

  def findAll: Future[Seq[Student]] =
    collection.find().toFuture().map(_.map { doc =>
      Student(
        Option(doc.getObjectId("_id").toHexString),
        doc.getString("lastName"),
        doc.getString("firstName"),
        doc.getString("middleName"),
        doc.getString("group"),
        doc.getDouble("gpa")
      )
    })

  def findById(id: String): Future[Option[Student]] =
    collection.find(equal("_id", new ObjectId(id))).first().toFuture().map { doc =>
      if (doc == null) None
      else Some(Student(
        Option(doc.getObjectId("_id").toHexString),
        doc.getString("lastName"),
        doc.getString("firstName"),
        doc.getString("middleName"),
        doc.getString("group"),
        doc.getDouble("gpa")
      ))
    }

  def create(student: Student): Future[String] = {
    val newId = new ObjectId()
    val doc = org.mongodb.scala.bson.Document(
      "_id" -> newId,
      "lastName" -> student.lastName,
      "firstName" -> student.firstName,
      "middleName" -> student.middleName,
      "group" -> student.group,
      "gpa" -> student.gpa
    )
    collection.insertOne(doc).toFuture().map(_ => newId.toHexString)
  }

  def update(id: String, student: Student): Future[Boolean] = {
    val filter = equal("_id", new ObjectId(id))
    val doc = org.mongodb.scala.bson.Document(
      "_id" -> new ObjectId(id),
      "lastName" -> student.lastName,
      "firstName" -> student.firstName,
      "middleName" -> student.middleName,
      "group" -> student.group,
      "gpa" -> student.gpa
    )
    collection.replaceOne(filter, doc).toFuture().map(_.wasAcknowledged())
  }

  def delete(id: String): Future[Boolean] = {
    val filter = equal("_id", new ObjectId(id))
    collection.deleteOne(filter).toFuture().map(_.wasAcknowledged())
  }
}
