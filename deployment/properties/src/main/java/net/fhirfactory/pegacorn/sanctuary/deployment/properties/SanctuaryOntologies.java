/*
 * Copyright (c) 2022 Mark A. Hunter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.fhirfactory.pegacorn.sanctuary.deployment.properties;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SanctuaryOntologies {

    //
    // FMIS / Supply Chain Ontologies
    //
    public String getSupplyChainFMISFileTypeSystem(){return("https://ontology.hd.act.gov.au/aether/supply-chain-v1/fmisapi");}
    public String getSupplyChainHRIMSFileTypeSystem(){return("https://ontology.hd.act.gov.au/aether/supply-chain-v1/hrimsapi");}
    public String getSupplyChainConvergaFileTypeSystem(){return("https://ontology.hd.act.gov.au/aether/supply-chain-v1/convergaapi");}
    public String getSupplyChainCpfFileTypeSystem(){return("https://ontology.hd.act.gov.au/aether/supply-chain-v1/cpfapi");}
    public String getSupplyChainPbrcFileTypeSystem(){return("https://ontology.hd.act.gov.au/aether/supply-chain-v1/pbrcapi");}
    
    //
    // ACTPAS Ontologies
    //  
    public String getACTPASIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers");}
    public String getACTPASPatientMRIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/patient_identifiers/MR");}
    public String getACTPASPatientRefNoIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/patient_identifiers/ReferenceNo");}
    public String getACTPASPractitionerIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/practitioner_identifiers");}
    public String getACTPASPractitionerRoleIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/practitionerRole_identifiers");}
    public String getACTPASEncounterIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/encounter_identifiers");}
    public String getACTPASProcedureRefNoIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/procedure_identifiers/ReferenceNo");}
    public String getACTPASProcedureCodeSystem() {return("https://ontology.health.act.gov.au/aether/actpas/procedure_codes");}
    public String getACTPASProcedureImmediacySystem() {return("https://ontology.health.act.gov.au/aether/actpas/procedure_immediacies");}
    public String getACTPASSpecialtySystem() {return("https://ontology.health.act.gov.au/aether/actpas/specialties");}
    public String getACTPASAdministrativeCategoryServiceTypeSystem() {return("https://ontology.health.act.gov.au/aether/actpas/administrative_categories");}
    public String getACTPASPrioritySystem() {return("https://ontology.health.act.gov.au/aether/actpas/priorities");}
    public String getACTPASLocationIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/location_identifiers");}
    public String getACTPASLanguageIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/language_identifiers");}
    public String getACTPASMaritalStatusIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/actpas/identifiers/marital_status_identifiers");}
    
    //
    // AETHER Ontologies
    //  
    public String getAETHERIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/identifiers");}
    public String getAETHERBundleIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/identifiers/bundle_identifiers");}
    public String getAETHERCommunicationIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/identifiers/communication_identifiers");}
    public String getAETHERProcedureIdentifierSystem() {return("https://ontology.health.act.gov.au/aether/identifiers/procedure_identifiers");}
    public String getAETHERPractitionerRolesCodeOrdersSystem() {return("https://ontology.health.act.gov.au/aether/identifiers/practitionerRole_code_order");}

    //
    // AETHER Extensions
    //
    public String getPatientA19QueryExtensionKey() {return ("https://ontology.health.act.gov.au/aether/patient/extensions/a19_query");}
    public String getPatientA19ResponseExtensionKey() {return ("https://ontology.health.act.gov.au/aether/patient/extensions/a19_response");}

    //
    // SNOMED Ontologies
    //  
    public String getSNOMEDIdentifierSystem() {return("http://snomed.info/sct");}
    public String getSNOMEDCodingVersion() {return("International Edition 2020-07-31 Release");}    
    
    //
    // FHIR Ontologies
    //  
    public String getHL7CommunicationCategorySystem() {return("http://terminology.hl7.org/CodeSystem/communication-category");}
    public String getHL7MaritalStatusSystem() {return("http://hl7.org/fhir/ValueSet/marital-status");}
    public String getHL7ParticipationModeSystem() {return("http://terminology.hl7.org/CodeSystem/v3-ParticipationMode");}
    public String getHL7ActCodeSystem() {return("http://terminology.hl7.org/CodeSystem/v3-ActCode");}
    public String getHL7BundleTypeSystem() {return("http://hl7.org/fhir/ValueSet/bundle-type");}
    public String getHL7ParticipantTypeSystem() {return("http://terminology.hl7.org/CodeSystem/v3-ParticipationMode");}
    public String getHL7ServiceDeliveryLocationRoleTypeSystem() {return("http://terminology.hl7.org/ValueSet/v3-ServiceDeliveryLocationRoleType");}
    public String getHL7LocationPhysicalTypeSystem() {return("http://hl7.org/fhir/ValueSet/location-physical-type");}

    //
    // Australian Bureau of Statistics Ontologies
    //  
    public String getASCLLanguageIdentifierSystem() {return("https://abs.gov.au/AUSSTAT/Australian_Standard_Classification_of_Languages/identifiers/language_identifiers");}
    
    // Additional common static variables
    public String getParticipantType() {return("2014-03-26");}
    public String getHL7CodingVersion() {return("4.0.1");}
    public String getHL7ParticipationModeCodingVersion() {return("2018-08-12");}
    public String getHL7ServiceDeliveryLocationRoleTypeCodingVersion() {return("2014-03-26");}
    public String getACTPASCodingVersion() {return("1.0");}
    public String getAETHERCodingVersion() {return("1.0");}
    public String getASCLCodingVersion() {return("1267.0");}
}
