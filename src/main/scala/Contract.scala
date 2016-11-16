/**
  * Created by default on 16/11/16.
  */

case class Contract(signed: List[(Person, java.time.Instant)], executed: Option[java.time.Instant])