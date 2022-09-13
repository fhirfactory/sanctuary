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
package net.fhirfactory.dricats.sanctuary.ponos;

import net.fhirfactory.dricats.sanctuary.deployment.topology.map.SanctuarySolutionNodeFactory;
import net.fhirfactory.pegacorn.core.constants.systemwide.PegacornReferenceProperties;
import net.fhirfactory.pegacorn.core.interfaces.topology.PegacornTopologyFactoryInterface;
import net.fhirfactory.pegacorn.core.model.topology.nodes.SolutionTopologyNode;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.archetypes.ClusterServiceDeliverySubsystemPropertyFile;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.interfaces.SolutionNodeFactoryInterface;
import net.fhirfactory.pegacorn.internals.fhir.r4.internal.topics.FHIRElementTopicFactory;
import net.fhirfactory.pegacorn.ponos.subsystem.processingplant.PonosAcolyte;
import net.fhirfactory.pegacorn.ponos.subsystem.processingplant.configuration.PonosAcolyteConfigurationFile;
import net.fhirfactory.pegacorn.ponos.subsystem.processingplant.configuration.PonosAcolyteTopologyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PonosIM extends PonosAcolyte {
    private static final Logger LOG = LoggerFactory.getLogger(PonosIM.class);

    private boolean aetherPonosInitialised;


    @Inject
    private FHIRElementTopicFactory fhirElementTopicFactory;

    @Inject
    private PonosAcolyteTopologyFactory topologyFactory;

    @Inject
    private PegacornReferenceProperties pegacornReferenceProperties;

    @Inject
    private SanctuarySolutionNodeFactory solutionNodeFactory;

    @Override
    public SolutionTopologyNode getSolutionNode(){
        return(solutionNodeFactory.newSolutionNode());
    }

    public PonosIM(){
        super();
        aetherPonosInitialised = false;
    }

    @Override
    protected ClusterServiceDeliverySubsystemPropertyFile specifyPropertyFile() {
        topologyFactory.initialise();
        PonosAcolyteConfigurationFile propertyFile = (PonosAcolyteConfigurationFile) topologyFactory.getPropertyFile();
        return(propertyFile);
    }

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected PegacornTopologyFactoryInterface specifyTopologyFactory() {
        return (topologyFactory);
    }

    @Override
    protected SolutionNodeFactoryInterface specifySolutionNodeFactory() {
        return (solutionNodeFactory);
    }

    @Override
    protected void executePostConstructActivities() {

    }
}
