package net.fhirfactory.dricats.sanctuary.mitaf.lms.processingplant;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fhirfactory.dricats.sanctuary.deployment.topology.map.SanctuarySolutionNodeFactory;
import net.fhirfactory.dricats.sanctuary.mitaf.lms.processingplant.configuration.MITaFLMSConfigurationFile;
import net.fhirfactory.dricats.sanctuary.mitaf.lms.processingplant.configuration.MITaFLMSTopologyFactory;
import net.fhirfactory.pegacorn.core.interfaces.topology.PegacornTopologyFactoryInterface;
import net.fhirfactory.pegacorn.core.model.topology.nodes.SolutionTopologyNode;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.archetypes.ClusterServiceDeliverySubsystemPropertyFile;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.interfaces.SolutionNodeFactoryInterface;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.MITaFHL7v2xSubSystem;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.SimpleSubscriptionItem;

@ApplicationScoped
public class MITaFLMSProcessingPlant extends MITaFHL7v2xSubSystem {
    private static final Logger LOG = LoggerFactory.getLogger(MITaFLMSProcessingPlant.class);

    @Inject
    private SanctuarySolutionNodeFactory solutionNodeFactory;

    @Inject
    private MITaFLMSTopologyFactory topologyFactory;

    @Override
    protected ClusterServiceDeliverySubsystemPropertyFile specifyPropertyFile() {
        topologyFactory.initialise();
        MITaFLMSConfigurationFile propertyFile = (MITaFLMSConfigurationFile)topologyFactory.getPropertyFile();
        return (propertyFile);
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
    public SolutionTopologyNode getSolutionNode() {
        return(solutionNodeFactory.getSolutionTopologyNode());
    }

    @Override
    protected SolutionNodeFactoryInterface specifySolutionNodeFactory() {
        return (solutionNodeFactory);
    }

    @Override
    protected List<SimpleSubscriptionItem> registerSubscriptionList(){
        getLogger().debug(".registerSubscriptionList(): Entry");

        List<SimpleSubscriptionItem> subscriptionList = new ArrayList<>();

        getLogger().debug(".registerSubscriptionList(): Exit");
        return(subscriptionList);
    }
}
