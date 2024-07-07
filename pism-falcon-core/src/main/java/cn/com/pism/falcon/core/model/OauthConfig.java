package cn.com.pism.falcon.core.model;

import cn.com.pism.falcon.core.enums.EndpointTypeEnum;
import cn.com.pism.falcon.core.enums.OauthParamEnum;
import cn.com.pism.falcon.core.exception.PmfcEndpointUndefinedException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author perccyking
 * @since 24-07-03 22:05
 */
@Data
@Accessors(chain = true)
public class OauthConfig {

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String scope;

    private String responseType;

    private Map<OauthParamEnum, String> paramsNameMapping = new EnumMap<>(OauthParamEnum.class);

    @Setter(AccessLevel.PRIVATE)
    private List<OauthEndpoint> endpoints = new ArrayList<>();

    private Map<EndpointTypeEnum, OauthEndpoint> endpointMap = new EnumMap<>(EndpointTypeEnum.class);

    public OauthConfig addEndpoint(OauthEndpoint endpoint) {
        endpointMap.put(endpoint.getType(), endpoint);
        return this;
    }

    public OauthEndpoint getEndpoint(EndpointTypeEnum endpointTypeEnum) {
        OauthEndpoint oauthEndpoint = endpointMap.get(endpointTypeEnum);
        if (oauthEndpoint == null) {
            throw new PmfcEndpointUndefinedException(endpointTypeEnum);
        }
        return oauthEndpoint;
    }

    public OauthConfig addParamsNameMapping(OauthParamEnum param, String mappingName) {
        paramsNameMapping.put(param, mappingName);
        return this;
    }


}
