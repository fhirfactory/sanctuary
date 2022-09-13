package net.fhirfactory.dricats.sanctuary.mitaf.miis.workshops.transform;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import net.fhirfactory.pegacorn.core.model.dataparcel.DataParcelManifest;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.model.HL7v2VersionEnum;
import net.fhirfactory.pegacorn.mitaf.hl7.v2x.workshops.transform.wup.BaseHL7v2xMessageToFHIRCommunicationWUP;

@ApplicationScoped
public class HL7v24InboundMessageTransformationWUP extends BaseHL7v2xMessageToFHIRCommunicationWUP {


    @Override
    protected List<DataParcelManifest> specifySubscriptionTopics() {

        List<DataParcelManifest> subscribedTopics = new ArrayList<>();

        subscribedTopics.add(createSubscriptionManifestForInteractIngressHL7v2Messages("ORU", "R01", HL7v2VersionEnum.VERSION_HL7_V24));

        return (subscribedTopics);
    }
    
    @Override
    protected List<DataParcelManifest> declarePublishedTopics() {
        return new ArrayList<>();
    }
}
