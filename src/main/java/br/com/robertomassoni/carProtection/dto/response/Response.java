package br.com.robertomassoni.carProtection.dto.response;

import br.com.robertomassoni.carProtection.enumerator.StatusType;
import br.com.robertomassoni.carProtection.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    
    private StatusType status;
    private T content;
    private Object errors;
    private Object pageable;
    
    public static <T> Response<T> badRequest() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.BAD_REQUEST);
        return response;
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.OK);
        return response;
    }

    public static <T> Response<T> unauthorized() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.UNAUTHORIZED);
        return response;
    }

    public static <T> Response<T> validationException() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> Response<T> wrongCredentials() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> Response<T> accessDenied() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.ACCESS_DENIED);
        return response;
    }

    public static <T> Response<T> exception() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.EXCEPTION);
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.NOT_FOUND);
        return response;
    }

    public static <T> Response<T> duplicateEntity() {
        Response<T> response = new Response<>();
        response.setStatus(StatusType.DUPLICATE_ENTITY);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg) {
        ResponseError error = new ResponseError()
                .setMessage(errorMsg)
                .setTimestamp(DateUtil.today());
        setErrors(error);
    }
    
}
