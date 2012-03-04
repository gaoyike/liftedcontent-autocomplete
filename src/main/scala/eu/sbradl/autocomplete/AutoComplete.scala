package eu.sbradl.autocomplete

import scala.xml.NodeSeq.seqToNodeSeq
import scala.xml.NodeSeq

import net.liftweb.http.js.JsCmds
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers.nextFuncName

object AutoComplete {

  /**
   * Creates a new auto-complete object.
   * 
   * @param choices The function used to get key-value pairs matching the given input.
   * @param minChars The minimum number of characters needed to trigger auto-completion.
   */
  def apply(choices: (String) => Seq[(String, String)], minChars: Int = 3) = new AutoComplete(choices, minChars)

}

/**
 * This class makes HTML5 autocompletion very easy to use. The fastest way to get
 * autocompletion to work is by using the methods [[AutoComplete.datalist]] and 
 * [[AutoComplete.input]]. If you need to use an already existing input element you 
 * can use [[AutoComplete.onChange]] to trigger the autocompletion.
 */
class AutoComplete(choices: (String) => Seq[(String, String)], minChars: Int) {

  /**
   * The ID used for the datalist element.
   */
  val datalistId = nextFuncName
  
  /**
   * The name used for the input element.
   */
  val inputName = nextFuncName

  /**
   * Returns an initial datalist element.
   */
  def datalist: NodeSeq = datalist("")

  /**
   * Returns a ready to use input element with auto-completion.
   */
  def input = <input type="text" name={ inputName } list={ datalistId } oninput={ onChange._2.toJsCmd }/>

  /**
   * Returns an ajax function to be used with custom input elements.
   */
  def onChange = SHtml.onEvent(updateDatalist _)

  private[autocomplete] def datalist(current: String): NodeSeq = <datalist id={ datalistId }>
                                                     { datalistContent(current) }
                                                   </datalist>

  private def updateDatalist(current: String) = JsCmds.SetHtml(datalistId, datalistContent(current))

  private def datalistContent(current: String) = if (current.length >= minChars) {

    choices(current) map {
      pair => <option value={ pair._1 }>{ pair._2 }</option>
    }

  } else NodeSeq.Empty
}