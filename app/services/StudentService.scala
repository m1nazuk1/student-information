package services

import javax.inject.Inject
import repository.StudentRepository
import models.Student
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class StudentService @Inject()(studentRepository: StudentRepository)(implicit ec: ExecutionContext) {
  def listAll: Future[Seq[Student]] = studentRepository.findAll
  def getOne(id: String): Future[Option[Student]] = studentRepository.findById(id)
  def create(student: Student): Future[String] = studentRepository.create(student)
  def update(id: String, student: Student): Future[Boolean] = studentRepository.update(id, student)
  def delete(id: String): Future[Boolean] = studentRepository.delete(id)
}
