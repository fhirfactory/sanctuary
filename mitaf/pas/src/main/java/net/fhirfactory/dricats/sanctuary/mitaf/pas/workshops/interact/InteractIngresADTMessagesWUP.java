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
package net.fhirfactory.dricats.sanctuary.mitaf.pas.workshops.interact;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fhirfactory.dricats.sanctuary.mitaf.pas.common.MITaFPASNames;
import net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration.MITaFPASTopologyFactory;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.HL7v2VersionEnum;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.workshops.interact.wup.BaseHL7v2xMessageIngressWUP;
import net.fhirfactory.dricats.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;

@ApplicationScoped
public class InteractIngresADTMessagesWUP extends BaseHL7v2xMessageIngressWUP {
    private static final Logger LOG = LoggerFactory.getLogger(InteractIngresADTMessagesWUP.class);

    @Inject
    private MITaFPASNames mitafInstanceNames;

    @Inject
    private SanctuarySubSystemParticipantNames subsystemNames;

    @Inject
    private MITaFPASTopologyFactory topologyFactory;

    //
    // Constructor(s)
    //

    public InteractIngresADTMessagesWUP(){
        super();
    }

    //
    // Business Logic
    //


    //
    // Getters (and Setters and Specify's)
    //


    @Override
    protected String specifySourceSystem() {
        return (subsystemNames.getMITaFPASParticipantName());
    }

    @Override
    protected String specifyIntendedTargetSystem() {
        return null;
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
    protected String specifyIngresTopologyEndpointName() {
        return (mitafInstanceNames.getInteractIngresADTMessages());
    }

    @Override
    protected String specifyIngresEndpointVersion() {
        return ("1.0.0");
    }

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected List<DataParcelManifest> declarePublishedTopics() {
        List<DataParcelManifest> publishedTopics = new ArrayList<>();

        DataParcelManifest publishedContentManifest = createPublishedManifestForInteractIngresHL7v2Messages("ADT", DataParcelManifest.WILDCARD_CHARACTER, HL7v2VersionEnum.VERSION_HL7_V231);
        publishedContentManifest.getContentDescriptor().setDataParcelDiscriminatorType(specifyMessageDiscriminatorType());
        publishedContentManifest.getContentDescriptor().setDataParcelDiscriminatorValue(specifyMessageDiscriminatorValue());
        publishedTopics.add(publishedContentManifest);

        return(publishedTopics);
    }
}
