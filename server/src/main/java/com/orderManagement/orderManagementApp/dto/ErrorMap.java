package com.orderManagement.orderManagementApp.dto;

import lombok.*;

import java.util.Map;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMap {
    private Map<String,String> invalidDataMap;
}
