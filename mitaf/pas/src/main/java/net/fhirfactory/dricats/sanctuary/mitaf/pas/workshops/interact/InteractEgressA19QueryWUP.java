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

import net.fhirfactory.dricats.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.dricats.sanctuary.mitaf.pas.common.MITaFPASNames;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.workshops.interact.wup.BaseHL7v2QueryMessageClientWUP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InteractEgressA19QueryWUP extends BaseHL7v2QueryMessageClientWUP {
    private static final Logger LOG = LoggerFactory.getLogger(InteractEgressA19QueryWUP.class);

    @Inject
    private MITaFPASNames mitafPASNames;

    @Inject
    private SanctuarySubSystemParticipantNames subsystemNames;

    @Override
    protected String specifyEgressTopologyEndpointName() {
        return (mitafPASNames.getInteractEgressA19QueryName());
    }

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected List<DataParcelManifest> specifySubscriptionTopics() {

        List<DataParcelManifest> subscribedTopics = new ArrayList<>();

        return(subscribedTopics);
    }
    
    @Override
    protected List<DataParcelManifest> declarePublishedTopics() {
        return new ArrayList<>();
    }
}
