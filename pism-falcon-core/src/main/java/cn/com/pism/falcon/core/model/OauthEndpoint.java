package cn.com.pism.falcon.core.model;

import cn.com.pism.falcon.core.enums.EndpointTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * @author perccyking
 * @since 24-07-06 12:12
 */
@Data
@Accessors(chain = true)
public class OauthEndpoint {

    private String endpoint;

    private EndpointTypeEnum type;

    private HttpMethod method;

    private OauthParams params;
}
