package tobyspring.myboot.exrate;

import java.math.BigDecimal;
import tobyspring.myboot.api.ApiTemplate;
import tobyspring.myboot.api.ErApiExRateExtractor;
import tobyspring.myboot.api.SimpleApiExecutor;
import tobyspring.myboot.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }
}
