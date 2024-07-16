package cn.com.pism.falcon.core.model;

import cn.com.pism.falcon.core.enums.OauthParamEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author perccyking
 * @since 24-07-06 12:13
 */
@Data
@Accessors(chain = true)
public class OauthParams {

    private List<OauthParamEnum> header = new ArrayList<>();

    private List<String> additionalHeader = new ArrayList<>();

    private List<OauthParamEnum> query = new ArrayList<>();

    private List<String> additionalQuery = new ArrayList<>();

    private List<OauthParamEnum> body = new ArrayList<>();

    private List<String> additionalBody = new ArrayList<>();

    public OauthParams addHeader(OauthParamEnum... paramEnum) {
        header.addAll(Arrays.stream(paramEnum).toList());
        return this;
    }

    public OauthParams addAdditionalHeader(String... paramEnum) {
        additionalHeader.addAll(Arrays.asList(paramEnum));
        return this;
    }

    public OauthParams addQuery(OauthParamEnum... paramEnum) {
        query.addAll(Arrays.stream(paramEnum).toList());
        return this;
    }

    public OauthParams addAdditionalQuery(String... paramEnum) {
        additionalQuery.addAll(Arrays.asList(paramEnum));
        return this;
    }

    public OauthParams addBody(OauthParamEnum... paramEnum) {
        body.addAll(Arrays.stream(paramEnum).toList());
        return this;
    }

    public OauthParams addAdditionalBody(String... param) {
        additionalBody.addAll(Arrays.stream(param).toList());
        return this;
    }
}
