package net.fhirfactory.dricats.sanctuary.mitaf.pas.workshops.interact;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fhirfactory.dricats.sanctuary.mitaf.pas.common.MITaFIIENames;
import net.fhirfactory.dricats.sanctuary.mitaf.pas.processingplant.configuration.MITaFIIETopologyFactory;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.HL7v2VersionEnum;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.workshops.interact.wup.BaseHL7v2xMessageIngressWUP;
import net.fhirfactory.pegacorn.sanctuary.deployment.properties.SanctuarySubSystemParticipantNames;

@ApplicationScoped
public class InteractIngresADTMessagesWUP extends BaseHL7v2xMessageIngressWUP {
    private static final Logger LOG = LoggerFactory.getLogger(InteractIngresADTMessagesWUP.class);

    @Inject
    private MITaFIIENames mitafInstanceNames;

    @Inject
    private SanctuarySubSystemParticipantNames subsystemNames;

    @Inject
    private MITaFIIETopologyFactory topologyFactory;

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
