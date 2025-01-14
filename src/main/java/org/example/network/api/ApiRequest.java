package org.example.network.api;

import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

public record ApiRequest(String path, String method, Map<String, String> queries) {

    public static ApiRequest fromHttpExchange(HttpExchange exchange) {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        String queryString = exchange.getRequestURI().getQuery();
        Map<String, String> queries = new HashMap<>();
        if (queryString != null) {
            for (String query: queryString.split("&")) {
                String[] queryData = query.split("=");
                queries.put(queryData[0].toLowerCase(), queryData[1].toLowerCase());
            }
        }
        return new ApiRequest(path, method, queries);
    }
}
