import scala.collection.mutable.HashSet

case class Property(uuid: Int, name: String, maximumOccupancy: Int, occupants: List[Person], contract: Option[Contract])

class Properties {

  val records: HashSet[Property] = HashSet.empty[Property]()

  def save(property: Property): Unit = {
    records.add(property)
  }

  def view(uuid: Int): Property) = {

  }
}