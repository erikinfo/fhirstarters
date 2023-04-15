package ca.uhn.example.provider;

import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.dstu3.model.*;

public class ObservationResourceProvider implements IResourceProvider {

   @Override
   public Class<? extends IBaseResource> getResourceType() {
      return Observation.class;
   }

   @Read()
   public Observation getResourceById(@IdParam IdType theId) {
      // implement the read operation
      Observation observation = new Observation();
      observation.setId("HRDObservationExample");
      observation.setStatus(Observation.ObservationStatus.FINAL);
      observation.getCode()
         .addCoding()
         .setSystem("http://ncicb.nci.nih.gov/xml/owl/EVS/Thesaurus.owl")
         .setCode("C120465");
      observation.getSubject()
         .setReference("Patient/Beispielpatient");
      //observation.getEffective()
      //   .setValue("2020-01-01");
      //observation.getValue()
      //   .setValue(100);
      observation.getInterpretation()
         .addCoding()
         .setSystem("http://loinc.org")
         .setCode("LA9193-9")
         .setDisplay("High");
      return observation;
   }
}
