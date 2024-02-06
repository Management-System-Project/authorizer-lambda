package com.glacies.authorizerlambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2CustomAuthorizerEvent;
import com.amazonaws.services.lambda.runtime.events.IamPolicyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glacies.authorizerlambda.models.AuthenticationInfo;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


import java.util.Map;

public class AuthorizerHandler implements RequestHandler<APIGatewayV2CustomAuthorizerEvent,
        Map<String,Object>> {


    @Override
    public Map<String,Object> handleRequest(APIGatewayV2CustomAuthorizerEvent input, Context context) {
        String redisHost = System.getenv("REDIS_HOST");
        String redisPort = System.getenv("REDIS_PORT");

        RedisClient redisClient = RedisClient.create("redis://" + redisHost + ":" + redisPort);

        StatefulRedisConnection<String, String> connection = redisClient.connect();
        // Get a sync connection
        RedisCommands<String, String> syncCommands = connection.sync();


        String accessToken = input.getCookies().stream()
                .filter(cookie -> cookie.startsWith("accessToken="))
                .map(cookie->cookie.substring("accessToken=".length()))
                .findFirst()
                .orElse("");

        String idToken = input.getHeaders().get("idToken");

        String key = accessToken + ":" + idToken;

        String data = syncCommands.get(key);

        // Parse the JSON string
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationInfo info = null;
        try {
            info = mapper.readValue(data, AuthenticationInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Extract the UUID and storeUUID
        String UUID = info.getUUID();
        String storeUUID = info.getStoreUUID();

        IamPolicyResponse.PolicyDocument policyDocument =
                new IamPolicyResponse.PolicyDocument();
        policyDocument.setVersion("2012-10-17");

        input.getRouteArn();

        input.se(policyDocument);
        return null;
    }
}
