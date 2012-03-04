package eu.sbradl.autocomplete

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import scala.xml.NodeSeq
import net.liftweb.http.LiftSession
import net.liftweb.util.StringHelpers
import net.liftweb.common.Empty
import net.liftweb.http.S
import org.specs2.execute.Result
import scala.xml.Node

@RunWith(classOf[JUnitRunner])
class AutoCompleteSpecs extends SpecificationWithJUnit {

  def choices(input: String) = for (i <- 1 to 3) yield (i.toString, input + i)

  val autocomplete = AutoComplete(choices)

  "datalist" should {

    "initially be empty" in {
      (autocomplete.datalist \ "datalist") must be equalTo NodeSeq.Empty
    }

    "have correct id" in {
      (autocomplete.datalist \\ "@id").text must be equalTo autocomplete.datalistId
    }

    "contain 3 elements" in {
      (autocomplete.datalist("test") \ "option").size must be equalTo 3
    }
    
    "contain the correct elements" in {
      val options = (autocomplete.datalist("test") \ "option").toList
      
      options(0) must be equalTo <option value="1">test1</option>
      options(1) must be equalTo <option value="2">test2</option>
      options(2) must be equalTo <option value="3">test3</option>
    }
  }

  "input" should {

    val session = new LiftSession("", StringHelpers.randomString(20), Empty)

    "use the correct name" in {
      S.initIfUninitted(session) {
        (autocomplete.input \\ "@name").text must be equalTo autocomplete.inputName
      }
    }

    "use the correct datalist ID" in {
      S.initIfUninitted(session) {
        (autocomplete.input \\ "@list").text must be equalTo autocomplete.datalistId
      }
    }

  }
}