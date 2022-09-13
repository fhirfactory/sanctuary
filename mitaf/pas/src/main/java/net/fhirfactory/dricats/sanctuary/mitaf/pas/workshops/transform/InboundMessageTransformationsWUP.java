package net.fhirfactory.dricats.sanctuary.mitaf.pas.workshops.transform;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hl7.fhir.r4.model.ResourceType;

import net.fhirfactory.pegacorn.core.constants.systemwide.PegacornReferenceProperties;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelTypeDescriptor;
import net.fhirfactory.pegacorn.core.model.dataparcel.valuesets.DataParcelDirectionEnum;
import net.fhirfactory.pegacorn.core.model.dataparcel.valuesets.DataParcelNormalisationStatusEnum;
import net.fhirfactory.pegacorn.core.model.dataparcel.valuesets.DataParcelValidationStatusEnum;
import net.fhirfactory.pegacorn.core.model.dataparcel.valuesets.PolicyEnforcementPointApprovalStatusEnum;
import net.fhirfactory.pegacorn.internals.fhir.r4.resources.communication.extensions.CommunicationPayloadTypeExtensionEnricher;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.HL7v2VersionEnum;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.workshops.transform.wup.BaseHL7v2xMessageToFHIRCommunicationWUP;

@ApplicationScoped
public class InboundMessageTransformationsWUP extends BaseHL7v2xMessageToFHIRCommunicationWUP {

    @Inject
    private CommunicationPayloadTypeExtensionEnricher payloadTypeExtensionEnricher;

    @Inject
    private PegacornReferenceProperties pegacornReferenceProperties;

    //
    // Constructor(s)
    //

    public InboundMessageTransformationsWUP(){
        super();
    }

    //
    // Business Logic
    //



    //
    // Getters (and Setters and Specify's)
    //


    @Override
    protected List<DataParcelManifest> specifySubscriptionTopics() {
        List<DataParcelManifest> subscribedTopics = new ArrayList<>();

        subscribedTopics.add(createSubscriptionManifestForInteractIngressHL7v2Messages("ADT", DataParcelManifest.WILDCARD_CHARACTER, HL7v2VersionEnum.VERSION_HL7_V231));

        return(subscribedTopics);
    }

    @Override
    protected List<DataParcelManifest> declarePublishedTopics() {
        getLogger().debug(".declarePublishedTopics(): Entry");
        DataParcelManifest manifest = new DataParcelManifest();

        DataParcelTypeDescriptor parcelContainerDescriptor = getFHIRTopicIDBuilder().newTopicToken(ResourceType.Communication.name(), pegacornReferenceProperties.getPegacornDefaultFHIRVersion());

        getLogger().trace(".packageCommunicationResource(): Extracting content type (Extension) from the Communication Payload");
        DataParcelTypeDescriptor parcelContentDescriptor = this.getTopicFactory().newDataParcelDescriptor("ADT", DataParcelManifest.WILDCARD_CHARACTER, HL7v2VersionEnum.VERSION_HL7_V231.getVersionText());

        getLogger().trace(".packageCommunicationResource(): Setting the Manifest details");
        manifest.setContainerDescriptor(parcelContainerDescriptor);
        manifest.setContentDescriptor(parcelContentDescriptor);
        manifest.setDataParcelFlowDirection(DataParcelDirectionEnum.INFORMATION_FLOW_INBOUND_DATA_PARCEL);
        manifest.setEnforcementPointApprovalStatus(PolicyEnforcementPointApprovalStatusEnum.POLICY_ENFORCEMENT_POINT_APPROVAL_NEGATIVE);
        manifest.setNormalisationStatus(DataParcelNormalisationStatusEnum.DATA_PARCEL_CONTENT_NORMALISATION_TRUE);
        manifest.setValidationStatus(DataParcelValidationStatusEnum.DATA_PARCEL_CONTENT_VALIDATED_TRUE);
        manifest.setInterSubsystemDistributable(true);
        manifest.setIntendedTargetSystem(DataParcelManifest.WILDCARD_CHARACTER);
        manifest.setSourceSystem(DataParcelManifest.WILDCARD_CHARACTER);

        List<DataParcelManifest> publishedTopics = new ArrayList<>();
        publishedTopics.add(manifest);

        return (publishedTopics);
    }
}

