import java.time.Instant

import scala.util.{Failure, Success, Try}

/**
  * Created by default on 16/11/16.
  */

case class Contract(signed: List[(Person, java.time.Instant)], executed: Option[java.time.Instant]) {

  def execute(): Try[Contract] = {
    if(signed.isEmpty) {
      Failure(new Exception("needs at least one person to execute"))
    } else {
      Success(this.copy(executed = Some(Instant.now)))
    }
  }
}