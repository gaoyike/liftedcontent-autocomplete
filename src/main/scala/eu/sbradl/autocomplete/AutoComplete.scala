package eu.sbradl.autocomplete

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds
import scala.xml.NodeSeq

object AutoComplete {

  def apply(choices: (String) => Seq[(String, String)], minChars: Int = 3) = new AutoComplete(choices, minChars)

}

class AutoComplete(choices: (String) => Seq[(String, String)], minChars: Int) {

  val datalistId = nextFuncName
  val inputName = nextFuncName

  def datalist: NodeSeq = datalist("")

  def input = <input type="text" name={ inputName } list={ datalistId } onchange={ onChange._2.toJsCmd }/>

  def onChange = SHtml.onEvent(updateDatalist _)
  
  private def datalist(current: String): NodeSeq = <datalist id={ datalistId }>
                                             { datalistContent(current) }
                                           </datalist>

  private def updateDatalist(current: String) = JsCmds.SetHtml(datalistId, datalistContent(current))

  private def datalistContent(current: String) = if (current.length >= minChars) {
      {
        choices(current) map {
          pair => <option value={ pair._1 }>{ pair._2 }</option>
        }
      }

  } else NodeSeq.Empty
}