package example

import java.io.{File, PrintWriter}

object Helper {
  var externalState = 0
  
  def writeToFile(path: String): Unit = {
    externalState = externalState + 10
    val w = new PrintWriter(new File(path))
    w.write(externalState.toString)
    w.close()
  }
}
