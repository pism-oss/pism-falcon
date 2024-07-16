package cn.com.pism.falcon.core.enums;

import lombok.Getter;

/**
 * @author perccyking
 * @since 24-07-07 09:10
 */
@Getter
public enum OauthParamEnum {

    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret"),
    REDIRECT_URI("redirect_uri"),
    SCOPE("scope"),
    RESPONSE_TYPE("response_type"),
    GRANT_TYPE("grant_type")
    ;

    private final String paramsName;

    OauthParamEnum(String paramsName) {
        this.paramsName = paramsName;
    }
}
