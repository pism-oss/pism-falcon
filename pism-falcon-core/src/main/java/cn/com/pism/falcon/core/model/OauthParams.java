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

    private List<OauthParamEnum> header;

    private List<String> additionalHeader;

    private List<OauthParamEnum> query = new ArrayList<>();

    private List<String> additionalQuery;

    private List<OauthParamEnum> body;

    private List<String> additionalBody;

    public OauthParams addQuery(OauthParamEnum... paramEnum) {
        query.addAll(Arrays.stream(paramEnum).toList());
        return this;
    }
}
