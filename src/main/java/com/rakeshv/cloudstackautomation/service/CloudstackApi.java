package com.rakeshv.cloudstackautomation.service;

import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackClient;
import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackRequest;
import br.com.autonomiccs.apacheCloudStack.client.beans.ApacheCloudStackUser;
import com.rakeshv.cloudstackautomation.models.CloudstackHandle;
import com.rakeshv.cloudstackautomation.models.Command;
import org.springframework.stereotype.Service;

@Service
public class CloudstackApi {

    public String execute(CloudstackHandle object, Command cloudstackCommand) {
        ApacheCloudStackUser apacheCloudStackUser = new ApacheCloudStackUser(object.getSecretKey(), object.getApiKey());
        ApacheCloudStackClient apacheCloudStackClient = new ApacheCloudStackClient(object.getUrl(), apacheCloudStackUser);
        ApacheCloudStackRequest apacheCloudStackRequest = new ApacheCloudStackRequest(cloudstackCommand.getCommand());
        apacheCloudStackRequest.addParameter("response", "json");

        if (cloudstackCommand.getCommandParameters() != null) {
            for (String key : cloudstackCommand.getCommandParameters().keySet()) {
                apacheCloudStackRequest.addParameter(key, cloudstackCommand.getCommandParameters().get(key));
            }
        }

        return apacheCloudStackClient.executeRequest(apacheCloudStackRequest);
    }
}
