package com.rakeshv.cloudstackautomation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CloudstackHandle {
    private String apiKey;
    private String secretKey;
    private String url;
}
