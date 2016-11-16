import scala.collection.mutable

case class Property(uuid: Int, name: String, maximumOccupancy: Int, occupants: List[Person], contract: Option[Contract]) {
    def changeMaximumOccupancy(newMax: Int): Property = this.copy(maximumOccupancy=newMax)

    def addPerson(person: Person): Property = this.copy(occupants = occupants ++ List(person))

    def removePerson(person: Person): Property = this.copy(occupants = occupants.filter(_ != person))
}

class PropertyListing {

  val records: mutable.Map[Int, Property] = new mutable.HashMap[Int, Property]()

  def save(property: Property): Unit = {
    records += property.uuid -> property
  }

  def view(uuid: Int): Property = {
    records(uuid)
  }
}

object PropertyListing {
  def apply() = new PropertyListing
}