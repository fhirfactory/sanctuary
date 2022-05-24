package net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant;

import net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration.MITaFIIEConfigurationFile;
import net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration.MITaFIIETopologyFactory;
import net.fhirfactory.pegacorn.core.interfaces.topology.PegacornTopologyFactoryInterface;
import net.fhirfactory.pegacorn.core.model.topology.nodes.SolutionTopologyNode;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.archetypes.ClusterServiceDeliverySubsystemPropertyFile;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.interfaces.SolutionNodeFactoryInterface;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.MITaFHL7v2xSubSystem;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.SimpleSubscriptionItem;
import net.fhirfactory.pegacorn.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.pegacorn.sanctuary.deployment.topology.map.SanctuarySolutionNodeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MITaFIIEProcessingPlant extends MITaFHL7v2xSubSystem {
    private static final Logger LOG = LoggerFactory.getLogger(MITaFIIEProcessingPlant.class);

    @Inject
    private SanctuarySubSystemParticipantNames subSystemNames;

    @Inject
    private SanctuarySolutionNodeFactory solutionNodeFactory;

    @Inject
    private MITaFIIETopologyFactory topologyFactory;

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
        MITaFIIEConfigurationFile propertyFile = (MITaFIIEConfigurationFile)topologyFactory.getPropertyFile();
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
