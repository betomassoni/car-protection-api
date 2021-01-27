package br.com.robertomassoni.carProtection.enumerator;

public enum ExceptionType {

    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception"),
    ENTITY_IS_EMPTY("is.empty"),
    ENTITY_ALREADY_EXISTS("already.exists");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
 

