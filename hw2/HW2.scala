import de.dominicscheurer.fsautils._
import de.dominicscheurer.fsautils.RegularExpressions._
import de.dominicscheurer.fsautils.Conversions._

object HW2 extends FSA_DSL {
  
    def main(args: Array[String]) {
        def badWords = ('i & 'f) + ('t & 'h & 'e & 'n) + ('e & 'l & 's & 'e) + ('l & 'e & 't) + ('i & 'n) + ('t & 'r & 'u & 'e) + ('f & 'a & 'l & 's & 'e) : RE 
        def badDFA = ((badWords toNFA) toDFA) minimize : DFA
        println(badDFA)

        // '0 stands for all digits, '_ stands for all letters except those used in badWords
        def goodWords = ('_ + 'e + 's + 'n + 't + 'u + 'f + 'a + 'i + 'l + 'h + 'r) & (('_ + 'e + 's + 'n + 't + 'u + 'f + 'a + 'i + 'l + 'h + 'r + Symbol("0"))*) : RE
        def goodDFA = ((goodWords toNFA) toDFA) minimize : DFA
        println(goodDFA)

        def finalDFA = (goodDFA & (!(badDFA.extendAlphabet(Set('_, Symbol("0")))))) minimize : DFA
        println(finalDFA)
    }
    
}
