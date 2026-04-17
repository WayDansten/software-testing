package org.example.utils;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public final class ArgumentSetup {
    private ArgumentSetup() {
    }

    public static Stream<BrowserType> browsers() {
        return Stream.of(BrowserType.CHROME, BrowserType.FIREFOX);
    }

    public static Stream<Arguments> withBrowsers(Stream<String> values) {
        List<String> cachedValues = values.toList();
        return browsers()
            .flatMap(browser -> cachedValues.stream().map(value -> Arguments.of(browser, value)));
    }

    public static Stream<Arguments> withBrowsersArgs(Stream<Arguments> values) {
        List<Arguments> cachedValues = values.toList();
        return browsers()
            .flatMap(browser -> cachedValues.stream().map(args -> prependBrowser(browser, args)));
    }

    private static Arguments prependBrowser(BrowserType browser, Arguments args) {
        Object[] original = args.get();
        Object[] combined = new Object[original.length + 1];
        combined[0] = browser;
        System.arraycopy(original, 0, combined, 1, original.length);
        return Arguments.of(combined);
    }
}
