package models

import org.anormcypher._
import java.math.BigInteger

case class Movie(tmdbid: Long, title: Option[String])

class MovieDto {
  val allQuery = Cypher("MATCH (m:Movie) return m.tmdbid as tmdbid, m.title as title")
  val saveQuery = Cypher("""CREATE (m:Movie {tmdbid: {tmdbid}, title: {title}})""")
  val findQuery = Cypher("""MATCH (m:Movie {tmdbid: {tmdbid}}) return m.tmdbid as tmdbid, m.title as title""")
  def findAll() = allQuery().map(rowMapper)

  def saveMovie(m: Movie) = saveQuery.on("tmdbid" -> m.tmdbid, "title" -> m.title.getOrElse("")).execute()
  def findMovieByTmdbId(id: Long) = findQuery.on("tmdbid" -> id)().map(rowMapper).headOption

  def rowMapper(row: CypherRow) = Movie(row[Long]("tmdbid"), Option(row[String]("title")))

}
