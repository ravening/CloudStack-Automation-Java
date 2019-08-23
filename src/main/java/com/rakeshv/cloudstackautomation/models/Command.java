package com.rakeshv.cloudstackautomation.models;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    private String command;
    HashMap<String, String> commandParameters;
}
