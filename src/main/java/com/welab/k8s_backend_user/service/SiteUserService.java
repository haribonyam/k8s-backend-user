package com.welab.k8s_backend_user.service;

import com.welab.k8s_backend_user.domain.SiteUser;
import com.welab.k8s_backend_user.dto.SiteUserRegisterDto;
import com.welab.k8s_backend_user.dto.event.SiteUserInfoEvent;
import com.welab.k8s_backend_user.event.producer.KafkaMessageProducer;
import com.welab.k8s_backend_user.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteUserService {
    private final SiteUserRepository siteUserRepository;
    private final KafkaMessageProducer kafkaMessageProducer;

    @Transactional
    public void registerUser(SiteUserRegisterDto registerDto) {
        SiteUser siteUser = registerDto.toEntity();

        siteUserRepository.save(siteUser);

        SiteUserInfoEvent event = SiteUserInfoEvent.fromEntity("Create", siteUser);
        kafkaMessageProducer.send(SiteUserInfoEvent.Topic, event);
    }
}