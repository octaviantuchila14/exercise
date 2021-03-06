import scala.collection.mutable
import scala.util.{Failure, Success, Try}

case class Property(uuid: Int, name: String, maximumOccupancy: Int, occupants: List[Person], contract: Option[Contract]) {
    def sign(newContract: Option[Contract]): Try[Property] = {
      if(contract.nonEmpty) {
         Failure(new Exception("Contract already signed"))
      } else if(newContract.nonEmpty && newContract.get.signed.map(_._1).forall(occupants contains _)) {
        Failure(new Exception("Not everyone signed the contract"))
      } else {
        Success(this.copy(contract=newContract))
      }
    }

    def changeMaximumOccupancy(newMax: Int): Try[Property] = {
      if(contract.nonEmpty) {
        Failure(new Exception("Contract already signed"))
      }else if(maximumOccupancy > 0) {
        Success(this.copy(maximumOccupancy=newMax))
      } else {
        Failure(new Exception("Maximum number of occupants must be positive"))
      }
    }

    def addPerson(person: Person): Try[Property] = {
      if(contract.nonEmpty) {
        Failure(new Exception("Contract already signed"))
      } else if(occupants.length + 1 > maximumOccupancy) {
        Failure(new Exception("Too many people"))
      } else if(occupants.map(_.name) contains person.name) {
        Failure(new Exception("Name must be unique in a property"))
      } else {
        Success(this.copy(occupants = occupants ++ List(person)))
      }
    }

    def removePerson(person: Person): Try[Property] = {
      if(contract.nonEmpty) {
        Failure(new Exception("Contract already signed"))
      }else if(occupants.contains(person)) {
        Success(this.copy(occupants = occupants.filter(_ != person)))
      } else {
        Failure(new Exception("Person doesn't exist"))
      }
    }
}

class PropertyListing {

  val records: mutable.Map[Int, Property] = new mutable.HashMap[Int, Property]()

  def save(property: Property): Unit = {
    records += property.uuid -> property
  }

  def view(uuid: Int): Try[Property] = {
    records.get(uuid) match {
      case Some(r) => Success(r)
      case None => Failure(new Exception("Property not found"))
    }
  }
}

object PropertyListing {
    def apply() = new PropertyListing
}