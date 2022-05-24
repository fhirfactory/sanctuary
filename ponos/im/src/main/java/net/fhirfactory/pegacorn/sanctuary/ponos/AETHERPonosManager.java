package net.fhirfactory.pegacorn.sanctuary.ponos;

import au.gov.act.hd.aether.deployment.topology.map.AETHERSolutionNodeFactory;
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
public class AETHERPonosManager extends PonosAcolyte {
    private static final Logger LOG = LoggerFactory.getLogger(AETHERPonosManager.class);

    private boolean aetherPonosInitialised;


    @Inject
    private FHIRElementTopicFactory fhirElementTopicFactory;

    @Inject
    private PonosAcolyteTopologyFactory topologyFactory;

    @Inject
    private PegacornReferenceProperties pegacornReferenceProperties;

    @Inject
    private AETHERSolutionNodeFactory solutionNodeFactory;

    @Override
    public SolutionTopologyNode getSolutionNode(){
        return(solutionNodeFactory.newSolutionNode());
    }

    public AETHERPonosManager(){
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
