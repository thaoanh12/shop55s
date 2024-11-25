package com.example.shop55_be.service;

import com.example.shop55_be.dto.MailDto;

public interface MailService {
     void sendHtmlMail(MailDto mailDto, String templateName) throws Exception;
}
