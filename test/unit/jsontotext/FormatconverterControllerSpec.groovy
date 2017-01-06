package jsontotext

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(FormatconverterController)
class FormatconverterControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test correct id"() {
    	when:
    	params.id = 54999
        controller.convert()

        then:
        String answerExpected = 'class\tid\tmediaFormatType\tmediaFormatKind\tshortDescription\tpurchaseable\tprices-gbp\tprices-usd\tprices-eur\toprices-gbp\toprices-usd\toprices-eur\tsale\nReleaseFormat\t132610\tMP3\tDigital\tnull\tYES\t2.99\t3.99\t3.99\t2.99\t3.99\t3.99\tfalse\nReleaseFormat\t132611\tWAV\tDigital\tnull\tYES\t3.99\t5.49\t5.49\t3.99\t5.49\t5.49\tfalse\nReleaseFormat\t132612\tFLAC\tDigital\tnull\tYES\t3.99\t5.49\t5.49\t3.99\t5.49\t5.49\tfalse\nReleaseFormat\t133509\tCassette\tPhysical\tnull\tNO_STOCK_LEVEL\t4.99\t6.99\t5.49\t4.99\t6.99\t5.49\tfalse'
        
        response.text == answerExpected
        
    }
    void "test false id"() {
    	when:
    	params.id = 5
        controller.convert()

        then:
        response.text == 'Please enter a valid id or configure a valid URL. ERROR. Message: Not Found'
    }

    void "test response content type"() {
    	when:
    	params.id = 54999
        controller.convert()

        then:
        response.contentType == "text/plain;charset=utf-8"
    }
}
