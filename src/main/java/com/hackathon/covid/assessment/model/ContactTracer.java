package com.hackathon.covid.assessment.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ContactTracer {
private String emailId;
private String phoneNumber;
private String contactDescription;

}