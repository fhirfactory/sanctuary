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
package net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant;

import net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration.MITaFPASConfigurationFile;
import net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration.MITaFPASTopologyFactory;
import net.fhirfactory.pegacorn.core.interfaces.topology.PegacornTopologyFactoryInterface;
import net.fhirfactory.pegacorn.core.model.topology.nodes.SolutionTopologyNode;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.archetypes.ClusterServiceDeliverySubsystemPropertyFile;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.interfaces.SolutionNodeFactoryInterface;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.MITaFHL7v2xSubSystem;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.SimpleSubscriptionItem;
import net.fhirfactory.dricats.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.dricats.sanctuary.deployment.topology.map.SanctuarySolutionNodeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MITaFPASProcessingPlant extends MITaFHL7v2xSubSystem {
    private static final Logger LOG = LoggerFactory.getLogger(MITaFPASProcessingPlant.class);

    @Inject
    private SanctuarySubSystemParticipantNames subSystemNames;

    @Inject
    private SanctuarySolutionNodeFactory solutionNodeFactory;

    @Inject
    private MITaFPASTopologyFactory topologyFactory;

    //
    // Constructor(s)
    //


    //
    // Business Methods
    //


    //
    // Getters (and Setters and Specifies)
    //

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected List<SimpleSubscriptionItem> registerSubscriptionList() {
        // IIE connection is unidirectional, we only receive updates
        return (new ArrayList<>());
    }

    @Override
    protected ClusterServiceDeliverySubsystemPropertyFile specifyPropertyFile() {
        topologyFactory.initialise();
        MITaFPASConfigurationFile propertyFile = (MITaFPASConfigurationFile)topologyFactory.getPropertyFile();
        return (propertyFile);
    }

    @Override
    public SolutionTopologyNode getSolutionNode() {
        return(solutionNodeFactory.getSolutionTopologyNode());
    }

    @Override
    protected PegacornTopologyFactoryInterface specifyTopologyFactory() {
        return (topologyFactory);
    }

    @Override
    protected SolutionNodeFactoryInterface specifySolutionNodeFactory() {
        return (solutionNodeFactory);
    }
}
