package cn.com.pism.falcon.core.model;

import cn.com.pism.falcon.core.enums.EndpointTypeEnum;
import cn.com.pism.falcon.core.enums.OauthParamEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author perccyking
 * @since 24-07-03 22:05
 */
@Component
public class OauthClient {

    @Resource
    private RestTemplate restTemplate;

    public void userInfo(OauthConfig oauthConfig) {

    }

    public void token(OauthConfig oauthConfig) {

    }

    public void endSession(OauthConfig oauthConfig) {

    }

    public void authorization(OauthConfig oauthConfig) {

    }

    public String authorizeUrl(OauthConfig oauthConfig) {
        OauthEndpoint oauthEndpoint = oauthConfig.getEndpoint(EndpointTypeEnum.AUTHORIZATION);
        String endpoint = oauthEndpoint.getEndpoint();

        UriBuilder uriBuilder = new DefaultUriBuilderFactory(endpoint).builder();

        OauthParams params = oauthEndpoint.getParams();
        List<OauthParamEnum> query = params.getQuery();

        Map<OauthParamEnum, String> paramsNameMapping = oauthConfig.getParamsNameMapping();

        query.forEach(queryParam -> {
            String queryParamMapping = paramsNameMapping.get(queryParam);
            String paramName = StringUtils.hasText(queryParamMapping) ? queryParamMapping : queryParam.getParamsName();
            switch (queryParam) {
                case SCOPE -> uriBuilder.queryParam(paramName, oauthConfig.getScope());
                case CLIENT_ID -> uriBuilder.queryParam(paramName, oauthConfig.getClientId());
                case REDIRECT_URI -> uriBuilder.queryParam(paramName, oauthConfig.getRedirectUri());
                case CLIENT_SECRET -> uriBuilder.queryParam(paramName, oauthConfig.getClientSecret());
                case RESPONSE_TYPE -> uriBuilder.queryParam(paramName, oauthConfig.getResponseType());
            }
        });


        return uriBuilder.toUriString();
    }

    public void doPost(OauthConfig oauthConfig) {
    }
}
