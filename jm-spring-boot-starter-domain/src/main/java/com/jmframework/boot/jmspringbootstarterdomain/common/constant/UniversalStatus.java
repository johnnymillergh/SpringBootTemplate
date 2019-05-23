package com.jmframework.boot.jmspringbootstarterdomain.common.constant;

import lombok.Getter;

/**
 * Description: UniversalStatus, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 16:48
 **/
@Getter
public enum UniversalStatus implements IUniversalStatus {
    // Successful
    /**
     * Success
     */
    SUCCESS(200, "Success"),
    /**
     * Failure
     */
    FAILURE(250, "Failure"),
    /**
     * Warning
     */
    WARNING(251, "Warning"),

    // Client error
    /**
     * Unauthorized
     */
    UNAUTHORIZED(401, "Unauthorized. The requester is not authorized to access the resource."),
    /**
     * Forbidden
     */
    FORBIDDEN(403, "Forbidden. " +
            "The request was formatted correctly but the server is refusing to supply the requested resource."),
    /**
     * Not found
     */
    NOT_FOUND(404, "Not found. The resource could not be found."),
    /**
     * Method not allowed
     */
    METHOD_NOT_ALLOWED(405, "The resource was requested using a method that is not allowed."),
    /**
     * Bad request
     */
    BAD_REQUEST(400, "Bad request."),
    /**
     * Param not matched
     */
    PARAM_NOT_MATCH(460, "Param not matched. " +
            "The request could not be fulfilled due to the incorrect syntax of the request."),
    /**
     * Param not null
     */
    PARAM_NOT_NULL(461, "Param not null."),
    /**
     * Param invalid
     */
    PARAM_INVALID(462, "Param invalid."),
    /**
     * User disabled
     */
    USER_DISABLED(463, "User disabled."),

    // Server error
    /**
     * Error or failure
     */
    ERROR(500, "Error. A generic status for an error in the server itself."),
    /**
     * Role not found
     */
    ROLE_NOT_FOUND(552, "Role not found."),
    /**
     * Username or password error
     */
    USERNAME_OR_PASSWORD_ERROR(553, "Username or password error."),
    /**
     * Token expired
     */
    TOKEN_EXPIRED(554, "Token expired."),
    /**
     * Token parse error
     */
    TOKEN_PARSE_ERROR(555, "Token parse error."),
    /**
     * Token out of control
     */
    TOKEN_OUT_OF_CONTROL(556, "Token out of control. " +
            "Current user has logged in before. Please try to reset current password or sign in again."),
    /**
     * Kick out self 无法手动踢出自己，请尝试退出登录操作！
     */
    KICK_OUT_SELF(557, "Cannot kick self out. Please try to sign in again.");

    /**
     * Code of status
     */
    private Integer code;
    /**
     * Message of status
     */
    private String message;

    UniversalStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static UniversalStatus fromCode(Integer code) {
        UniversalStatus[] universalStatuses = UniversalStatus.values();
        for (UniversalStatus universalStatus : universalStatuses) {
            if (universalStatus.getCode().equals(code)) {
                return universalStatus;
            }
        }
        return SUCCESS;
    }

    @Override
    public String toString() {
        return "UniversalStatus{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}