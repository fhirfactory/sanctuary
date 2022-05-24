package au.gov.act.health.itops.im;

import au.gov.act.hd.aether.deployment.topology.map.AETHERSolutionNodeFactory;
import au.gov.act.health.aether.deployment.properties.AETHERSubSystemParticipantNames;
import net.fhirfactory.pegacorn.core.interfaces.topology.PegacornTopologyFactoryInterface;
import net.fhirfactory.pegacorn.core.model.topology.nodes.SolutionTopologyNode;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.archetypes.ClusterServiceDeliverySubsystemPropertyFile;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.interfaces.SolutionNodeFactoryInterface;
import net.fhirfactory.pegacorn.itops.im.processingplant.ITOpsInformationManagerProcessingPlant;
import net.fhirfactory.pegacorn.itops.im.processingplant.configuration.ITOpsIMConfigurationFile;
import net.fhirfactory.pegacorn.itops.im.processingplant.configuration.ITOpsIMTopologyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ITOpsIM extends ITOpsInformationManagerProcessingPlant  {
    private static final Logger LOG = LoggerFactory.getLogger(ITOpsIM.class);

    @Inject
    private AETHERSolutionNodeFactory solutionNodeFactory;

    @Inject
    private ITOpsIMTopologyFactory topologyFactory;

    @Inject
    private AETHERSubSystemParticipantNames subSystemNames;

    @Override
    public SolutionTopologyNode getSolutionNode() {
        return (solutionNodeFactory.getSolutionTopologyNode());
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
    protected ClusterServiceDeliverySubsystemPropertyFile specifyPropertyFile() {
        topologyFactory.initialise();
        ITOpsIMConfigurationFile propertyFile = (ITOpsIMConfigurationFile)topologyFactory.getPropertyFile();
        return (propertyFile);
    }
}
