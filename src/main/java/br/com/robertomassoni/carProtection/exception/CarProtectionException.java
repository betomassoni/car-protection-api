package br.com.robertomassoni.carProtection.exception;

import br.com.robertomassoni.carProtection.config.PropertiesConfig;
import br.com.robertomassoni.carProtection.enumerator.EntityType;
import br.com.robertomassoni.carProtection.enumerator.ExceptionType;
import java.text.MessageFormat;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarProtectionException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public CarProtectionException(PropertiesConfig propertiesConfig) {
        CarProtectionException.propertiesConfig = propertiesConfig;
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String... args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.ENTITY_IS_EMPTY.equals(exceptionType)
                || ExceptionType.ENTITY_ALREADY_EXISTS.equals(exceptionType)
                || ExceptionType.ENTITY_EXCEPTION.equals(exceptionType)) {
            return new EntityException(format(messageTemplate, args));        
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String messageTemplate, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(messageTemplate));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), (Object[]) args);
        }
        return String.format(messageTemplate, (Object[]) args);
    }

    public static class EntityIsEmptyException extends RuntimeException {
        public EntityIsEmptyException() {
            super();
        }
        public EntityIsEmptyException(String message) {
            super(message);
        }
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException() {
            super();
        }
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class EntityException extends RuntimeException {
        public EntityException() {
            super();
        }
        public EntityException(String message) {
            super(message);
        }
    }
    
    public static class EntityAlreadyExistsException extends RuntimeException {
        public EntityAlreadyExistsException() {
            super();
        }
        public EntityAlreadyExistsException(String message) {
            super(message);
        }
    }

}
