package ru.tecon.integrationEISUOT.util.gson;

/**
 * @author Maksim Shchelkonogov
 */
public final class GsonUnderscoresFieldNamingStrategy extends GsonFieldNamingStrategy {

    private static final Character UNDERSCORE = '_';

    @Override
    protected char getSeparator() {
        return UNDERSCORE;
    }
}
