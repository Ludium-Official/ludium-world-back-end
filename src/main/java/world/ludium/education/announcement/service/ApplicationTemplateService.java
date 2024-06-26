package world.ludium.education.announcement.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import world.ludium.education.announcement.model.ApplicationTemplate;
import world.ludium.education.announcement.repository.ApplicationTemplateRepository;

@Service
public class ApplicationTemplateService {

  private final ApplicationTemplateRepository applicationTemplateRepository;

  public ApplicationTemplateService(ApplicationTemplateRepository applicationTemplateRepository) {
    this.applicationTemplateRepository = applicationTemplateRepository;
  }

  public ApplicationTemplate getApplicationTemplate(UUID detailedAnnouncementId, String role) {
    return applicationTemplateRepository.findByDetailIdAndRole(detailedAnnouncementId, role)
        .orElseThrow();
  }

  public ApplicationTemplate createApplicationTemplate(ApplicationTemplate applicationTemplate) {
    applicationTemplate.setApplicationTemplateId(UUID.randomUUID());

    return applicationTemplateRepository.save(applicationTemplate);
  }

  public ApplicationTemplate updateApplicationTemplate(ApplicationTemplate applicationTemplate) {
    return applicationTemplateRepository.save(applicationTemplate);
  }
}
