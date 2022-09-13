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
package net.fhirfactory.dricats.sanctuary.mitaf.miis.workshops.interact;

import net.fhirfactory.dricats.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.dricats.sanctuary.mitaf.miis.common.MITaFMIISNames;
import net.fhirfactory.dricats.sanctuary.mitaf.miis.processingplant.configuration.MITaFMIISConfigurationFile;
import net.fhirfactory.dricats.sanctuary.mitaf.miis.processingplant.configuration.MITaFMIISTopologyFactory;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.workshops.interact.wup.BaseHL7v2xMessageIngressWUP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InteractIngresORUWUP extends BaseHL7v2xMessageIngressWUP {
    private static final Logger LOG = LoggerFactory.getLogger(InteractIngresORUWUP.class);

    @Inject
    private MITaFMIISNames mitafInstanceNames;

    @Inject
    private SanctuarySubSystemParticipantNames subsystemNames;

    @Inject
    private MITaFMIISTopologyFactory topologyFactory;

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected String specifyIngresTopologyEndpointName() {
        return (mitafInstanceNames.getInteractORUIngres());
    }

    @Override
    protected String specifyIngresEndpointVersion() {
        return ("1.0.0");
    }

    @Override
    protected String specifySourceSystem() {
        return (subsystemNames.getMITaFPathologyParticipantName());
    }

    @Override
    protected String specifyIntendedTargetSystem() {
        return (null);
    }

    @Override
    protected String specifyMessageDiscriminatorType() {
        return "SourcePort";
    }

    @Override
    protected String specifyMessageDiscriminatorValue() {
        MITaFMIISConfigurationFile configFile = (MITaFMIISConfigurationFile) topologyFactory.getPropertyFile();
        Integer servicePortValue = configFile.getInteractORUIngres().getServerPort();
        String servicePortValueAsString = servicePortValue.toString();
        return (servicePortValueAsString);
    }

    @Override
    protected List<DataParcelManifest> declarePublishedTopics() {
        return new ArrayList<>();
    }
}
