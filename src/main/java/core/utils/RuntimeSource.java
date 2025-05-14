package core.utils;

import core.App;

public enum RuntimeSource {

    JAR,
    CLASSES;

    private static RuntimeSource runtimeSource;

    public static RuntimeSource get() {
        if (runtimeSource == null) {
            final String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            runtimeSource = path.matches(".*Code-Snippet-Manager-\\d+\\.\\d+\\.\\d+\\.jar.*") ? JAR : CLASSES;
        }

        return runtimeSource;
    }

}
