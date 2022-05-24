/*
 * Copyright (c) 2022 M. A. Hunter
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
package net.fhirfactory.pegacorn.sanctuary.deployment.datasets.deploymentinstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import net.fhirfactory.pegacorn.core.constants.systemwide.DeploymentSystemSiteIdentificationInterface;
import net.fhirfactory.pegacorn.internals.fhir.r4.internal.systems.DeploymentInstanceDetail;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

import au.gov.act.health.aether.deployment.properties.AETHERSolutionSystemNames;

@ApplicationScoped
public class SanctuaryDeploymentInstanceDetailResources extends DeploymentInstanceDetail implements DeploymentSystemSiteIdentificationInterface {
    @Inject
    AETHERSolutionSystemNames   aetherSolutionSystemNames;

    public SanctuaryDeploymentInstanceDetailResources(){
        super();
    }

    @Override
    protected String specifyOrganizationName() {
        return(aetherSolutionSystemNames.getACTPASOrganizationName());
    }

    @Override
    protected String specifySystemOwnerContactName() {
        return ("TBA");
    }

    @Override
    protected String sepcifySystemOwnerContactEmail() {
        return ("TBA");
    }

    @Override
    protected String specifySystemOwnerContactPhone() {
        return ("TBA");
    }

    @Override
    protected String specifySystemAdministratorContactName() {
        return ("TBA");
    }

    @Override
    protected String specifySystemAdministratorContactEmail() {
        return ("TBA");
    }

    @Override
    protected String specifySystemAdministratorContactPhone() {
        return ("TBA");
    }

    @Override
    protected String specifyEndpointName() {
        return (aetherSolutionSystemNames.getSolutionName());
    }

    @Override
    protected CodeableConcept specifyOrganizationType() {
        CodeableConcept orgType = new CodeableConcept();
        Coding orgTypeCoding = new Coding();
        orgTypeCoding.setSystem("https://www.hl7.org/fhir/codesystem-organization-type.html");
        orgTypeCoding.setCode("prov");
        orgTypeCoding.setDisplay("Healthcare Provider");
        orgType.setText("Healthcare Provider");
        orgType.getCoding().add(orgTypeCoding);
        return(orgType);
    }

    @Override
    protected String createSystemReference() {
        return ("http://health.act.gov.au/aether");
    }

    @Override
    public String getDeploymentSite() {
        return ("AETHER");
    }

    public String getAetherFhirBreakWebContextPath() {
        return "/aether/messaging/healthlink/v1.0";
    }
}
