package ca.uhn.example.servlet;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.example.provider.ObservationResourceProvider;
import ca.uhn.example.provider.OrganizationResourceProvider;
import ca.uhn.example.provider.PatientResourceProvider;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * This servlet is the actual FHIR server itself
 */
@WebServlet("/*")
public class ExampleRestfulServlet extends RestfulServer {

   private ApplicationContext applicationContext;

   ExampleRestfulServlet(ApplicationContext context) {

      this.applicationContext = context;
   }

   @Override
   protected void initialize() throws ServletException {
      super.initialize();
      setFhirContext(FhirContext.forDstu3());
      /*
       * Two resource providers are defined. Each one handles a specific
       * type of resource.
       */
      List<IResourceProvider> providers = new ArrayList<IResourceProvider>();
      providers.add(new PatientResourceProvider());
      providers.add(new OrganizationResourceProvider());
      providers.add(new ObservationResourceProvider());
      setResourceProviders(providers);

      /*
       * Use a narrative generator. This is a completely optional step,
       * but can be useful as it causes HAPI to generate narratives for
       * resources which don't otherwise have one.
       */
      INarrativeGenerator narrativeGen = new DefaultThymeleafNarrativeGenerator();
      getFhirContext().setNarrativeGenerator(narrativeGen);

      /*
       * Use nice coloured HTML when a browser is used to request the content
       */
      registerInterceptor(new ResponseHighlighterInterceptor());
   }
}
