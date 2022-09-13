package au.gov.act.health.aether.mitaf.bridges.workshop.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.freemarker.FreemarkerConstants;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.DefaultModelClassFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.parser.PipeParser;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.Version;
import net.fhirfactory.pegacorn.core.constants.petasos.PetasosPropertyConstants;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelTypeDescriptor;
import net.fhirfactory.pegacorn.core.model.generalid.FDNToken;
import net.fhirfactory.pegacorn.core.model.petasos.uow.UoW;
import net.fhirfactory.pegacorn.core.model.petasos.uow.UoWPayload;

/**
 * Tests for transformations for capital pathology incoming ORU messages.
 * 
 * @author Brendan Douglas
 *
 */
public class LavertyORUTTransformTest extends CamelTestSupport {	
	
	@EndpointInject("mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce("direct:start")
	protected ProducerTemplate template;
	
	/**
	 * Tests the ORU transformation on port 16601.  The transformation only applied to this port.
	 */
	@Test
	public void testORUTransformationPort16601() {
		try (HapiContext context = new DefaultHapiContext();) {
			String inputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_16601_in.hl7"));
			String outputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_16601_out.hl7"));
		
			inputHL7 = inputHL7.replaceAll("\n", "\r");
			outputHL7 = outputHL7.replaceAll("\n", "\r");
	
			PipeParser parser = context.getPipeParser();
			parser.getParserConfiguration().setValidating(false);
	
			ModelClassFactory cmf = new DefaultModelClassFactory();
			context.setModelClassFactory(cmf);
			
			Message inputMessage = parser.parse(inputHL7);
			Message outputMessage = parser.parse(outputHL7);
					
            doTransform(inputMessage, getUnitOfWork("SourcePort", "16601"));
            
			String transformedMessage = (String)resultEndpoint.getExchanges().get(0).getMessage().getBody();
                                   
            assertEquals(outputMessage.toString(), parser.parse(transformedMessage).toString());
		} catch (HL7Exception e) {
			fail("Unable to process HL7 message", e);
		} catch (IOException e) {
			fail("Unable to read HL7 message", e);
		}				
	}
	
	
	/**
	 * Tests the ORU transformation on port 16601 when no pati.  The transformation only applied to this port.
	 */
	@Test
	public void testORUTransformationNoIdTypePort16601() {
		try (HapiContext context = new DefaultHapiContext();) {
			String inputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_no_id_type_16601_in.hl7"));
			String outputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_no_id_type_16601_out.hl7"));
		
			inputHL7 = inputHL7.replaceAll("\n", "\r");
			outputHL7 = outputHL7.replaceAll("\n", "\r");
	
			PipeParser parser = context.getPipeParser();
			parser.getParserConfiguration().setValidating(false);
	
			ModelClassFactory cmf = new DefaultModelClassFactory();
			context.setModelClassFactory(cmf);
			
			Message inputMessage = parser.parse(inputHL7);
			Message outputMessage = parser.parse(outputHL7);
					
            doTransform(inputMessage, getUnitOfWork("SourcePort", "16601"));
            
			String transformedMessage = (String)resultEndpoint.getExchanges().get(0).getMessage().getBody();
                                   
            assertEquals(outputMessage.toString(), parser.parse(transformedMessage).toString());
		} catch (HL7Exception e) {
			fail("Unable to process HL7 message", e);
		} catch (IOException e) {
			fail("Unable to read HL7 message", e);
		}				
	}
	
	
	/**
	 * Tests the ORU transformation on port 16602.  The transformation only applied to this port.
	 */
	@Test
	public void testORUTransformationPort16602() {
		try (HapiContext context = new DefaultHapiContext();) {
			String inputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_16602_in.hl7"));
			String outputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_16602_out.hl7"));
		
			inputHL7 = inputHL7.replaceAll("\n", "\r");
			outputHL7 = outputHL7.replaceAll("\n", "\r");
	
			PipeParser parser = context.getPipeParser();
			parser.getParserConfiguration().setValidating(false);
	
			ModelClassFactory cmf = new DefaultModelClassFactory();
			context.setModelClassFactory(cmf);
			
			Message inputMessage = parser.parse(inputHL7);
			Message outputMessage = parser.parse(outputHL7);
					
            doTransform(inputMessage, getUnitOfWork("SourcePort.Name", "oru-nc-in"));
            
			String transformedMessage = (String)resultEndpoint.getExchanges().get(0).getMessage().getBody();
                                   
            assertEquals(outputMessage.toString(), parser.parse(transformedMessage).toString());
		} catch (HL7Exception e) {
			fail("Unable to process HL7 message", e);
		} catch (IOException e) {
			fail("Unable to read HL7 message", e);
		}				
	}
	
	
	/**
	 * Tests the ORU transformation on another port.  Not 16601.  The transformation only applied to port 15651
	 */
	@Test
	public void testORUTransformationOtherPort() {
		try (HapiContext context = new DefaultHapiContext();) {
			String inputHL7 = Files.readString(Paths.get("src/test/resources/hl7/laverty_ORU_16601_in.hl7"));
		
			inputHL7 = inputHL7.replaceAll("\n", "\r");
			
			PipeParser parser = context.getPipeParser();
			parser.getParserConfiguration().setValidating(false);
	
			ModelClassFactory cmf = new DefaultModelClassFactory();
			context.setModelClassFactory(cmf);
			
			Message inputMessage = parser.parse(inputHL7);
			
            doTransform(inputMessage, getUnitOfWork("SourcePort", "16652"));
            
			String transformedMessage = (String)resultEndpoint.getExchanges().get(0).getMessage().getBody();
                                   
            assertEquals(inputMessage.toString(), parser.parse(transformedMessage).toString());
		} catch (HL7Exception e) {
			fail("Unable to process HL7 message", e);
		} catch (IOException e) {
			fail("Unable to read HL7 message", e);
		}				
	}
	
	
	
	  protected void doTransform(Message message, UoW uoW) {	
			
			template.send(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					UoW uow = new UoW();

					String fdnTokenString0 = "<0:TestType0>TestValue0</0:TestType0";
					FDNToken token = new FDNToken(fdnTokenString0);
					uow.setTypeID(token);

					UoWPayload ingresPayload = new UoWPayload();
					ingresPayload.setPayload(message.toString());

					DataParcelManifest manifest = new DataParcelManifest();
					ingresPayload.setPayloadManifest(manifest);
					uow.setIngresContent(ingresPayload);

					exchange.setProperty(PetasosPropertyConstants.WUP_CURRENT_UOW_EXCHANGE_PROPERTY_NAME, uow);
					exchange.getMessage().setBody(message);

					Map<String, Object> variableMap = new HashMap<>();
					variableMap.put("message", message);
					variableMap.put("exchange", exchange);
					variableMap.put("uoW", uoW);
					exchange.getIn().setHeader(FreemarkerConstants.FREEMARKER_DATA_MODEL, variableMap);
					BeansWrapper wrapper = new BeansWrapper(new Version(2, 3, 27));
					TemplateModel statics = wrapper.getStaticModels();

					variableMap.put("statics", statics);
				}
			});
		}
	  
	    private UoW getUnitOfWork(String discriminatorType, String discriminatorValue) {
	        UoW uoW = new UoW();
	        FDNToken fDNToken = new FDNToken();
	        fDNToken.setContent("<0:test>HL7</0:test><1:token>Message</1:token>");
	        uoW.setTypeID(fDNToken);
	        UoWPayload uoWPayload = new UoWPayload();
	        DataParcelManifest dataParcelManifest = new DataParcelManifest();
	        DataParcelTypeDescriptor dataParcelTypeDescriptor = new DataParcelTypeDescriptor();
	        dataParcelTypeDescriptor.setDataParcelDiscriminatorType(discriminatorType);
	        dataParcelTypeDescriptor.setDataParcelDiscriminatorValue(discriminatorValue);
	        dataParcelManifest.setContentDescriptor(dataParcelTypeDescriptor);
	        uoWPayload.setPayloadManifest(dataParcelManifest);
	        uoW.setIngresContent(uoWPayload);

	        return uoW;
	    }
	    
	    
	    @Override
	    protected RouteBuilder createRouteBuilder() throws Exception {
	        return new RouteBuilder() {
	            @Override
	            public void configure() throws Exception {
	                from("direct:start")
	                .to("freemarker:file:../aether-host-files/subsystem/aether-mitaf-pathologyreferencelab-laverty/aether-mitaf-pathologyreferencelab-laverty-ingres-transformation-config.ftl?contentCache=false&allowTemplateFromHeader=true&allowContextMapAll=true")
	                .to("mock:result");
	            }
	        };
	    }
}
