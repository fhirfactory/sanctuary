/*
 * Copyright (c) 2022 Mark A. Hunter (ACT Health)
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
package net.fhirfactory.dricats.sanctuary.mitaf.lms.workshops.interact;

import java.util.ArrayList;
import java.util.List;

import net.fhirfactory.dricats.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.dricats.sanctuary.deployment.properties.capabilities.CapabilityProviderNameService;
import net.fhirfactory.dricats.sanctuary.mitaf.lms.common.MITaFLMSNames;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.core.model.topology.role.ProcessingPlantRoleEnum;
import net.fhirfactory.pegacorn.mitaf.hl7.v24.interact.wup.HL7v24MessageA19EnabledIngressWUP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InteractIngresA19QueryWUP extends HL7v24MessageA19EnabledIngressWUP  {
    private static final Logger LOG = LoggerFactory.getLogger(InteractIngresA19QueryWUP.class);

    @Inject
    private CapabilityProviderNameService capabilityProviderNameService;

    @Inject
    private SanctuarySubSystemParticipantNames subsystemNames;
    
    @Inject
    private MITaFLMSNames mitafLISNames;

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected String specifyIngresTopologyEndpointName() {
        return (mitafLISNames.getInteractQRYIngres());
    }

    @Override
    protected String specifyIngresEndpointVersion() {
        return ("1.0.0");
    }

    @Override
    protected String specifySourceSystem() {
        String capabilityProviderName = capabilityProviderNameService.resolveCapabilityServiceProvider(ProcessingPlantRoleEnum.PETASOS_SERVICE_PROVIDER_GATEWAY_A19QUERY);
        return (capabilityProviderName);
    }

    @Override
    protected String specifyIntendedTargetSystem() {
        return (null);
    }

    @Override
    protected String specifyMessageDiscriminatorType() {
        return null;
    }

    @Override
    protected String specifyMessageDiscriminatorValue() {
        return null;
    }
    
    @Override
    protected List<DataParcelManifest> declarePublishedTopics() {
        return new ArrayList<>();
    }
}
