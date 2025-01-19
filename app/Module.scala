package app

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import play.api.{Configuration, Environment}
import org.mongodb.scala.MongoClient

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {
  override def configure(): Unit = {
    val mongoUri = configuration.get[String]("mongodb.uri")
    val dbName   = configuration.get[String]("mongodb.database")
    bind(classOf[MongoClient]).toInstance(MongoClient(mongoUri))
    bind(classOf[String]).annotatedWith(Names.named("dbName")).toInstance(dbName)
  }
}
