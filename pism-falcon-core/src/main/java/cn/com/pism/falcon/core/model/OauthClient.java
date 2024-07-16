package cn.com.pism.falcon.core.model;

import cn.com.pism.falcon.core.enums.EndpointTypeEnum;
import cn.com.pism.falcon.core.enums.OauthParamEnum;
import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author perccyking
 * @since 24-07-03 22:05
 */
@Component
public class OauthClient {

    @Resource
    private RestTemplate restTemplate = new RestTemplate();

    public void userInfo(OauthConfig oauthConfig) {
        request(oauthConfig, oauthConfig.getEndpoint(EndpointTypeEnum.USER_INFO));
    }

    public void token(OauthConfig oauthConfig) {
        OauthEndpoint oauthEndpoint = oauthConfig.getEndpoint(EndpointTypeEnum.TOKEN);
        request(oauthConfig, oauthEndpoint);
    }

    public void endSession(OauthConfig oauthConfig) {

    }

    public String authorizeUrl(OauthConfig oauthConfig) {
        OauthEndpoint oauthEndpoint = oauthConfig.getEndpoint(EndpointTypeEnum.AUTHORIZATION);
        String endpoint = oauthEndpoint.getEndpoint();

        UriBuilder uriBuilder = new DefaultUriBuilderFactory(endpoint).builder();

        OauthParams params = oauthEndpoint.getParams();
        List<OauthParamEnum> query = params.getQuery();

        query.forEach(queryParam -> uriBuilder.queryParam(getParamName(oauthConfig, queryParam), getParamValue(oauthConfig, queryParam)));

        params.getAdditionalQuery().forEach(queryParam -> uriBuilder.queryParam(queryParam, oauthConfig.getCache().get(queryParam)));

        return uriBuilder.toUriString();
    }

    public void request(OauthConfig oauthConfig, OauthEndpoint oauthEndpoint) {
        OauthParams params = oauthEndpoint.getParams();

        Map<String, String> bodyParam = new HashMap<>();
        params.getBody().forEach(param -> bodyParam.put(getParamName(oauthConfig, param), getParamValue(oauthConfig, param)));
        params.getAdditionalBody().forEach(param -> bodyParam.put(param, oauthConfig.getCache().get(param)));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        params.getHeader().forEach(param -> headers.add(getParamName(oauthConfig, param), getParamValue(oauthConfig, param)));
        params.getAdditionalHeader().forEach(param -> headers.add(param, oauthConfig.getCache().get(param)));

        Map<String, String> urlParam = new HashMap<>();
        params.getQuery().forEach(param -> urlParam.put(getParamName(oauthConfig, param), getParamValue(oauthConfig, param)));

        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(bodyParam, headers);
        ResponseEntity<Object> exchange = restTemplate.exchange(oauthEndpoint.getEndpoint(),
                oauthEndpoint.getMethod(),
                objectHttpEntity,
                Object.class,
                urlParam
        );
        Object body = exchange.getBody();
        System.out.println(body);
    }

    private String getParamValue(OauthConfig oauthConfig, OauthParamEnum param) {
        return switch (param) {
            case SCOPE -> oauthConfig.getScope();
            case CLIENT_ID -> oauthConfig.getClientId();
            case CLIENT_SECRET -> oauthConfig.getClientSecret();
            case REDIRECT_URI -> oauthConfig.getRedirectUri();
            case RESPONSE_TYPE -> oauthConfig.getResponseType();
            case GRANT_TYPE -> oauthConfig.getGrantType();
        };
    }

    private String getParamName(OauthConfig oauthConfig, OauthParamEnum param) {
        String paramMapping = oauthConfig.getParamsNameMapping().get(param);
        return StringUtils.hasText(paramMapping) ? paramMapping : param.getParamsName();
    }
}
