import java.time.Instant

import org.scalatest.FunSuite

/**
  * Created by default on 16/11/16.
  */
class ContractTest extends FunSuite {

  test("can not sign a contract without any person") {
    val contract = Contract(List.empty[(Person, Instant)], None)
    assert(Contract(List.empty[(Person, Instant)], None).execute.failed.get.getMessage == "needs at least one person to execute")
  }
}
