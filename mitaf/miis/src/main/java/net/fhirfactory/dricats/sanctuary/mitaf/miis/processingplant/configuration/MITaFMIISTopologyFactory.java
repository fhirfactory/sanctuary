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
package net.fhirfactory.dricats.sanctuary.mitaf.miis.processingplant.configuration;

import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClusteredServerPortSegment;
import net.fhirfactory.pegacorn.deployment.properties.configurationfilebased.common.segments.ports.interact.InteractClientPortSegment;
import net.fhirfactory.pegacorn.deployment.topology.factories.archetypes.MITaFSubsystemTopologyFactory;
import net.fhirfactory.dricats.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;
import net.fhirfactory.dricats.sanctuary.mitaf.miis.common.MITaFMIISNames;
import net.fhirfactory.pegacorn.core.model.topology.nodes.*;
import net.fhirfactory.pegacorn.core.model.topology.nodes.common.EndpointProviderInterface;
import net.fhirfactory.pegacorn.util.PegacornEnvironmentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MITaFMIISTopologyFactory extends MITaFSubsystemTopologyFactory {
    private static final Logger LOG = LoggerFactory.getLogger(MITaFMIISTopologyFactory.class);

    public MITaFMIISTopologyFactory(){
        super();
    }

    @Inject
    private SanctuarySubSystemParticipantNames aetherSubSystemNames;

    @Inject
    private PegacornEnvironmentProperties pegacornEnvironmentProperties;

    @Inject
    private MITaFMIISNames mitafInstanceNames;

    protected String specifyPropertyFileName() {
        LOG.info(".specifyPropertyFileName(): Entry");
        String configurationFileName = pegacornEnvironmentProperties.getMandatoryProperty("DEPLOYMENT_CONFIG_FILE");
//        String configurationFileName = System.getenv("SUBSYSTEM_CONFIG_FILE");
        if(configurationFileName == null){
            throw(new RuntimeException("Cannot load configuration file!!!! (SUBSYSTEM-CONFIG_FILE="+configurationFileName+")"));
        }
        LOG.info(".specifyPropertyFileName(): Exit, filename->{}", configurationFileName);
        return configurationFileName;
    }

    @Override
    protected Logger specifyLogger() {
        return (LOG);
    }

    @Override
    protected Class specifyPropertyFileClass() {
        return (MITaFMIISConfigurationFile.class);
    }

    @Override
    protected ProcessingPlantSoftwareComponent buildSubsystemTopology(){
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
        

        // Unique to Pathology Reference Lab - Laverty Pathology
        getLogger().trace(".buildSubsystemTopology(): Add the MLLP Server port to the Cluster Topology Node");
        addMLLPServerPorts(clusterServiceTopologyNode);
        getLogger().trace(".buildSubsystemTopology(): Add the MLLP Server port to the ProcessingPlant Topology Node");
        addMLLPServerPorts(processingPlantTopologyNode);
        getLogger().trace(".buildSubsystemTopology(): Add the MLLP Client port to the ProcessingPlant Topology Node");
        addMLLPClientPorts(processingPlantTopologyNode);
        return(processingPlantTopologyNode);
    }

    protected void addMLLPServerPorts( EndpointProviderInterface endpointProvider) {
        getLogger().debug(".addMLLPServerPorts(): Entry, endpointProvider->{}", endpointProvider);

        getLogger().trace(".addMLLPServerPorts(): Creating the MLLP Server (Laboraty Information Systems - ORU to Connect-To)");
        InteractClusteredServerPortSegment interactMLLPServerORU = ((MITaFMIISConfigurationFile) getPropertyFile()).getInteractORUIngres();
        newMLLPServerEndpoint(endpointProvider, mitafInstanceNames.getInteractORUIngres(), interactMLLPServerORU);

        getLogger().trace(".addMLLPServerPorts(): Creating the MLLP Server (Laboraty Information Systems)- QRY to Connect-To)");
        InteractClusteredServerPortSegment interactMLLPServerQRY = ((MITaFMIISConfigurationFile) getPropertyFile()).getInteractQRYIngres();
        newMLLPServerEndpoint(endpointProvider, mitafInstanceNames.getInteractQRYIngres(), interactMLLPServerQRY);
        
        getLogger().debug(".addMLLPServerPorts(): Exit");
    }

    protected void addMLLPClientPorts( EndpointProviderInterface endpointProvider) {
        getLogger().debug(".addMLLPClientPorts(): Entry, endpointProvider->{}", endpointProvider);

        getLogger().trace(".addMLLPClientPorts(): Creating the MLLP Client (Used to Connect-To Pathology Reference Lab (Laverty Pathology))");
        InteractClientPortSegment interactMLLPClient = ((MITaFMIISConfigurationFile) getPropertyFile()).getInteractAllEgress();
        newMLLPClientEndpoint(endpointProvider, mitafInstanceNames.getInteractAllEgress(),interactMLLPClient );

        getLogger().debug(".addMLLPClientPorts(): Exit");
    }

}
