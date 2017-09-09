package io.youi.example

import io.youi.app.ServerApplication
import io.youi.http._


/**
  * The server application implementation. Notice that it requires two mix-ins:
  *   - ServerApplication: required for "servery things"
  *   - ExampleApplication: defines this application as providing an implementation of ExampleApplication shared trait.
  *
  * The only necessity to actually be done in this code here is to define handlers to set up the content. A main method
  * is already defined to allow the server to start.
  */
object ServerExampleApplication extends ServerApplication with ExampleApplication {
  

  /**
   * Change default address to 0.0.0.0
   * 
   * Alternative: ./config.json with
   *  {
   *    "listeners": {
   *       "http": {
   *       "host": "0.0.0.0"
   *       }
   *    }
   *  }
   */
  protected override def run(): Unit = {
    config.clearListeners().addHttpListener("0.0.0.0", 8080)

    super.run()
  }
  
  /**
    * Defines "/" and "/index.html" as "pages" that loads a basic HTML page along with the Scala.js resource loaded.
    */
  handler.matcher(
    combined.any(
      path.exact("/"),
      path.exact("/index.html"),
      path.exact("/chat.html")
    )
  ).page()

  override def main(args: Array[String]): Unit = start(args)

  
}