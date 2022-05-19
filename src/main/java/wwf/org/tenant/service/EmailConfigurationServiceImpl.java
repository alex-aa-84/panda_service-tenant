package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.EmailConfiguration;
import wwf.org.tenant.repository.EmailConfigurationRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailConfigurationServiceImpl implements EmailConfigurationService {

    @Autowired
    private EmailConfigurationRepository emailConfigurationRepository;

    @Override
    public List<EmailConfiguration> listAllEmailConfiguration() {
        return emailConfigurationRepository.findAll();
    }

    @Override
    public EmailConfiguration getEmailConfiguration(Long id) {
        return emailConfigurationRepository.findById(id).orElse(null);
    }

    @Override
    public EmailConfiguration createEmailConfiguration(EmailConfiguration email) {

        email.setStatus("CREATED");
        email.setCreation_date(new Date());
        email.setLast_update_date(new Date());

        return emailConfigurationRepository.save(email);
    }

    @Override
    public EmailConfiguration updateEmailConfiguration(EmailConfiguration email) {
        EmailConfiguration emailConfigurationDB = getEmailConfiguration(email.getId());
        if(null == emailConfigurationRepository){
            return null;
        }

        email.setStatus("MODIFIED");
        email.setLast_update_date(new Date());

        return emailConfigurationRepository.save(email);
    }

    @Override
    public EmailConfiguration deleteEmailConfiguration(EmailConfiguration email) {
        EmailConfiguration emailConfigurationDB = getEmailConfiguration(email.getId());
        if(null == emailConfigurationRepository){
            return null;
        }

        email.setStatus("DELETED");
        email.setLast_update_date(new Date());

        return emailConfigurationRepository.save(email);
    }
}
