package cn.com.pism.falcon.core.exception;

import cn.com.pism.falcon.core.enums.EndpointTypeEnum;

/**
 * @author perccyking
 * @since 24-07-06 13:12
 */
public class PmfcEndpointUndefinedException extends PmfcException {
    public PmfcEndpointUndefinedException(EndpointTypeEnum message) {
        super(message.name() + " endpoint Undefined");
    }
}
