package net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration;

import net.fhirfactory.dricats.sanctuary.mitaf.pas.common.MITaFIIENames;
import net.fhirfactory.pegacorn.core.model.topology.nodes.*;
import net.fhirfactory.pegacorn.core.model.topology.nodes.common.EndpointProviderInterface;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClientPortSegment;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClusteredServerPortSegment;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.MITaFSubsystemTopologyFactory;
import net.fhirfactory.pegacorn.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.pegacorn.util.PegacornEnvironmentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MITaFIIETopologyFactory extends MITaFSubsystemTopologyFactory {
    private static final Logger LOG = LoggerFactory.getLogger(MITaFIIETopologyFactory.class);

    @Inject
    private SanctuarySubSystemParticipantNames aetherSubSystemParticipantNames;

    @Inject
    private PegacornEnvironmentProperties pegacornEnvironmentProperties;

    @Inject
    private MITaFIIENames iieNames;

    //
    // Constructor(s)
    //

    public MITaFIIETopologyFactory(){
        super();
    }

    //
    // Business Methods
    //

    @Override
    protected ProcessingPlantSoftwareComponent buildSubsystemTopology() {
        SubsystemTopologyNode subsystemTopologyNode = addSubsystemNode(getTopologyIM().getSolutionTopology());
        BusinessServiceTopologyNode businessServiceTopologyNode = addBusinessServiceNode(subsystemTopologyNode);
        DeploymentSiteTopologyNode deploymentSiteTopologyNode = addDeploymentSiteNode(businessServiceTopologyNode);
        ClusterServiceTopologyNode clusterServiceTopologyNode = addClusterServiceNode(deploymentSiteTopologyNode);

        PlatformTopologyNode platformTopologyNode = addPlatformNode(clusterServiceTopologyNode);
        ProcessingPlantSoftwareComponent processingPlantTopologyNode = addPegacornProcessingPlant(platformTopologyNode);
        addPrometheusPort(processingPlantTopologyNode);
        addJolokiaPort(processingPlantTopologyNode);
        addKubeLivelinessPort(processingPlantTopologyNode);
        addKubeReadinessPort(processingPlantTopologyNode);
        addEdgeAnswerPort(processingPlantTopologyNode);
        addAllJGroupsEndpoints(processingPlantTopologyNode);

        // Unique to IIE
        getLogger().trace(".buildSubsystemTopology(): Add the MLLP Server port to the Cluster Topology Node");
        addMLLPServerPorts(clusterServiceTopologyNode);
        addMLLPServerPorts(processingPlantTopologyNode);
        //
        // Done!
        return (processingPlantTopologyNode);
    }

    protected void addMLLPServerPorts(EndpointProviderInterface endpointProvider) {
        getLogger().debug(".addMLLPClientPorts(): Entry, endpointProvider->{}", endpointProvider);

        getLogger().trace(".addMLLPClientPorts(): Creating the ADT MLLP Client (Used to Connect-To ARIA)");
        InteractClusteredServerPortSegment interactIngresADT = ((MITaFIIEConfigurationFile) getPropertyFile()).getInteractIngresADTMessaging();
        newMLLPServerEndpoint(endpointProvider, iieNames.getInteractIngresADTMessages(), interactIngresADT);
    }

    //
    // Getters (and Setters and Specifies)
    //

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected String specifyPropertyFileName() {
        getLogger().info(".specifyPropertyFileName(): Entry");
        String configurationFileName = pegacornEnvironmentProperties.getMandatoryProperty("DEPLOYMENT_CONFIG_FILE");
        if (configurationFileName == null) {
            throw (new RuntimeException("Cannot load configuration file!!!! (SUBSYSTEM-CONFIG_FILE=" + configurationFileName + ")"));
        }
        getLogger().info(".specifyPropertyFileName(): Exit, filename->{}", configurationFileName);
        return configurationFileName;
    }

    @Override
    protected Class specifyPropertyFileClass() {
        return (MITaFIIEConfigurationFile.class);
    }
}
