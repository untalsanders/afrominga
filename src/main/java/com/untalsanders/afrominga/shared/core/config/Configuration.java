package com.untalsanders.afrominga.shared.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

public final class Configuration {
    public static String get(String envVarName) throws IOException {
        var propertyLoaded = load(System.getenv("profile"));
        return resolveEnvVars(propertyLoaded.getProperty(envVarName));
    }

    private static Properties load(String profile) throws IOException {
        Objects.requireNonNull(profile);

        String filePropertyToLoad = !profile.isEmpty()
            ? String.format("src/main/resources/application-%s.properties", profile)
            : "src/main/resources/application.properties";

        try (InputStream in = new FileInputStream(filePropertyToLoad)) {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        }
    }

    private static String resolveEnvVars(String input) {
        if (null == input) throw new IllegalArgumentException();
        var p = Pattern.compile("\\$\\{(\\w+)}|\\$(\\w+)");
        var m = p.matcher(input);

        var sb = new StringBuilder();
        while (m.find()) {
            var envVarName = null == m.group(1) ? m.group(2) : m.group(1);
            var envVarValue = System.getenv(envVarName);
            m.appendReplacement(sb, null == envVarValue ? "" : envVarValue);
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
